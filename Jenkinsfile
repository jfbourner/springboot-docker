pipeline {
    agent any
    environment {
        build = "${env.BUILD_ID}"
    }
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
               sh 'docker run --name my-image -d -ti -v /var/run/docker.sock:/var/run/docker.sock \
                                                     -v $(which docker):/usr/bin/docker -p 8089:8089 my-image:${build}'
               sh 'curl --request GET http://localhost:8089/get'
            }
        }
    }
    post {
            always {
                echo 'I will always say Hello again!'
                sh 'docker stop my-image'

            }
        }
}