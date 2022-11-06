pipeline {
  agent any
  stages {
    stage('Tests') {
      steps {
        sh 'mvn compile'
        sh 'mvn test'
        sh 'mvn verify'
      }
    }

  }
}