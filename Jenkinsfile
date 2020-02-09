pipeline {
    agent any
    tools {
            maven 'Maven 3.3.9'
            jdk 'jdk8'
    }
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