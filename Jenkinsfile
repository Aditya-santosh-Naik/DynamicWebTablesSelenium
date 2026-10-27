pipeline {
  agent any
  tools { jdk 'JDK21' }
  stages {
    stage('Checkout') { steps { checkout scm } }
    stage('Test') { steps { bat 'mvn -B clean test' } }
  }
  post {
    always { junit 'target/surefire-reports/*.xml' }
  }
}
