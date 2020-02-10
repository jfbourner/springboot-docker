pipeline {
    agent none
    stages {
        stage('Test') {
            agent {
                docker { image 'maven:3-alpine' }
            }
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Build') {
            agent {
                docker { image 'maven:3-alpine' }
            }
            steps {
                sh 'mvn clean package -Dmaven.test.skip=true'
            }
        }
        stage('Front-end') {
            steps {
               def customImage = docker.build("my-image:${env.BUILD_ID}")
               customImage.inside {
                       sh 'pwd'
               }
            }
        }
    }
}