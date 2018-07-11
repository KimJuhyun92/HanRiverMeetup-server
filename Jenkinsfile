pipeline {
  agent any
  stages {
    stage('Pull latest source') {
      steps {
        sh '''cd /var/www/hanriver;
git stash;
git checkout origin/dev;
git pull origin dev;'''
      }
    }
    stage('Stop jar') {
      steps {
        sh 'pkill -f \'java -jar\' || true;'
      }
    }
    stage('Build using maven') {
      steps {
        sh '''pwd;
rm -rf target;
cp /var/www/application.properties ./src/main/resources/application.properties;
mvn package;'''
      }
    }
    stage('Run application') {
      steps {
        sh '''cd target;
nohup java -jar HangangRiver-0.0.1-SNAPSHOT.jar &;'''
      }
    }
  }
}