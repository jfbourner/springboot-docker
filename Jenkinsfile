pipeline {
    agent none
    def customImage
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
             customImage = docker.build("my-image:${env.BUILD_ID}")
            }
        }
    }
}