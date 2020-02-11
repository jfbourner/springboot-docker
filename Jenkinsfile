pipeline {
    agent any
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
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("my-image:${env.BUILD_ID}")
                }
            }
        }
        stage('Run Image and BDD') {
            steps {
               sh 'docker run -p 8089:8089 -t "my-image:${env.BUILD_ID}"'
            }
        }
    }
}