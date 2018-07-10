pipeline {
  agent any
  stages {
    stage('Pull latest source') {
      steps {
        echo 'Hello World'
        sh '''cd /var/www/hanriver;
git stash;
git checkout origin/dev;
git pull origin dev;'''
      }
    }
  }
}