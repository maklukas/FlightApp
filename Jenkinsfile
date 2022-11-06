pipeline {
  agent any
  stages {
    stage('Tests') {
      steps {
        sh 'chmod +x gradlew'
        sh './gradlew clean build'
      }
    }

  }
}