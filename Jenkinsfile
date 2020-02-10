pipeline {
    agent none
    stages {
        stage('Back-end') {
            agent {
                docker { image 'maven:3-alpine' }
            }
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Front-end') {
            steps {
                script {
                    def customImage = docker.build("my-image:${env.BUILD_ID}")
               }
            }
        }
    }
}