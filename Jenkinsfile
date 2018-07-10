pipeline {
  agent any
  stages {
    stage('Pull latest source') {
      steps {
        echo 'Hello World'
        sh '''cd /var/www/hanriver;
git clone https://github.com/HanRiverMeetup/HanRiverMeetup-server.git .
git stash;
git checkout origin/dev;
git pull origin dev;'''
      }
    }
  }
}