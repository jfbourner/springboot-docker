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
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("my-image:${env.BUILD_ID}")
                }
            }
        }
        stage('Run Image and BDD') {
            agent {
                docker { image 'my-image:${env.BUILD_ID}' }
            }
            steps {
                sh 'pwd'
            }
        }
    }
}