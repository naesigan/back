# VAR_KILL_ARGS=$(ps -ef | grep dev_api | grep -v "grep" | awk '{print $2}')
# if [ -n "$VAR_KILL_ARGS" ]; then sudo kill $VAR_KILL_ARGS; fi
# sudo nohup java -javaagent:/home/ec2-user/agent.java/scouter.agent.jar -Dscouter.config=/home/ec2-user/agent.java/conf/scouter.conf -Djdk.attach.allowAttachSelf=true -jar /var/lib/jenkins/workspace/dev_api/build/libs/main-0.0.1-SNAPSHOT.jar &



#!/bin/bash
BASE_PATH=/var/lib/jenkins/workspace/dev_api/build/libs
BUILD_PATH=$(ls $BASE_PATH/*.jar)
JAR_NAME=$(basename $BUILD_PATH)
echo "> build 파일명: $JAR_NAME"

DEPLOY_PATH=$BASE_PATH/

# 쉬고 있는 port 찾기: 8081이 사용중이면 8082가 쉬고 있음
PORT=8081
process_count=$(netstat -tuln | grep $PORT | wc -l)

if [ "$process_count" -gt 0 ]; 
then
  echo "Port $PORT is in use." 
  IDLE_PROFILE=dev
  IDLE_PORT=8082
  PORT=8081
else
  echo "Port $PORT is not in use."
  IDLE_PROFILE=dev
  IDLE_PORT=8081
  PORT=8082
fi


echo "> application.jar 교체"
IDLE_APPLICATION=main-0.0.1-SNAPSHOT.jar
IDLE_APPLICATION_PATH=$DEPLOY_PATH$JAR_NAME

echo "> $IDLE_PROFILE 에서 구동중인 애플리케이션 pid 확인"
#PID=$(ps -ef | grep dev_api | grep "port=$IDLE_PORT" | awk '{print $2}')
PID=$(pgrep -f "port=$IDLE_PORT.*dev_api")
echo $PIS
if [ -z $PIS ]; then
  echo "포트 $IDLE_PORT 를 사용하는 프로세스를 찾을 수 없습니다."
else
  # 해당 PID로 프로세스를 종료합니다.
  echo "포트 $IDLE_PORT 를 사용하는 프로세스를 종료합니다. (PID: $PID)"
  sudo kill $PID
fi

echo "> $IDLE_PROFILE : $IDLE_PORT 배포"
echo "> sudo nohup java -Dserver.port=$IDLE_PORT -javaagent:/home/ec2-user/agent.java/scouter.agent.jar -Dscouter.config=/home/ec2-user/agent.java/conf/scouter.conf -Djdk.attach.allowAttachSelf=true -jar /var/lib/jenkins/workspace/dev_api/build/libs/main-0.0.1-SNAPSHOT.jar &"
sudo nohup java -Dserver.port=$IDLE_PORT -javaagent:/home/ec2-user/agent.java/scouter.agent.jar -Dscouter.config=/home/ec2-user/agent.java/conf/scouter.conf -Djdk.attach.allowAttachSelf=true -jar /var/lib/jenkins/workspace/dev_api/build/libs/main-0.0.1-SNAPSHOT.jar &

echo "> $IDLE_PROFILE 10초 후 Health check 시작"
sleep 10

for retry_count in {1..10}
do
  
  if curl -s http://localhost:$IDLE_PORT/api/login 
  then 
      echo "> Health check 성공"
      break
  else
      echo "> Health check의 응답을 알 수 없거나 혹은 status가 UP이 아닙니다."
  fi

  if [ $retry_count -eq 10 ]
  then
    echo "> Health check 실패. "
    echo "> Nginx에 연결하지 않고 배포를 종료합니다."
    exit 1
  fi

  echo "> Health check 연결 실패. 재시도..."
  sleep 10
done

echo "> 전환할 Port : $IDLE_PORT"
echo "> Port 전환"
echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/servers/service-url.inc

echo "> Nginx Reload"
# service nginx reload
sudo nginx -s reload

echo "> 기존 사용 포트 $PORT 종료"
# fuser -k -n tcp $PORT
echo "> $PORT 에서 구동중인 애플리케이션 pid 확인"
#PID=$(ps -ef | grep dev_api | grep "port=$PORT" | awk '{print $2}')
PID=$(pgrep -f "port=$PORT.*dev_api")

if [ -z "$PID" ]; then
  echo "포트 $PORT 사용하는 프로세스를 찾을 수 없습니다."
else
  # 해당 PID로 프로세스를 종료합니다.
  echo "포트 $PORT 사용하는 프로세스를 종료합니다. (PID: $PID)"
  sudo kill $PID
fi
``
