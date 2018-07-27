pipeline {
  agent any
  stages {
    stage('Stop jar') {
      steps {
        sh 'sudo pkill -f \\\'java -jar\\\' || true;'
      }
    }
    stage('Build using maven') {
      steps {
        sh '''pwd;
cp /var/www/application.properties ./src/main/resources/application.properties;
mvn clean package;'''
      }
    }
    stage('Run application') {
      steps {
        sh 'sudo nohup java -jar ./target/HangangRiver-0.0.1-SNAPSHOT.jar & '
      }
    }
  }
}