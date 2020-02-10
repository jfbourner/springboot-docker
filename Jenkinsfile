pipeline {
    agent {
        docker { image 'maven:3.3-jdk-8'}
    }
    stages {
        stage('Test') {
                steps {
                    sh 'mvn clean test'
                }
            }
        stage('Build') {
            steps {
                sh 'mvn clean package -Dmaven.test.skip=true'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    def customImage = docker.build("my-image:${env.BUILD_ID}")
               }
            }
        }
    }
}