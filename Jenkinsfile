pipeline {
    agent any
  
    stages {
        stage('Build') {
            steps {
               sh '''
                  mvn --version
               '''
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..2'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}