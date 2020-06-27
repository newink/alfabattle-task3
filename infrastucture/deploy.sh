./gradlew clean bootJar && scp build/libs/contest.jar ubuntu@178.154.247.212:~/contest/ && ssh ubuntu@178.154.247.212 "sudo systemctl restart contest"
