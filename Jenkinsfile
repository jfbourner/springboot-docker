pipeline {
    agent any
    environment {
        build = "${env.BUILD_ID}"
    }
    stages {
        stage('Unit Test') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    reuseNode true
                }
            }
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Package') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    reuseNode true
                }
            }
            steps {
                sh 'mvn clean package -Dmaven.test.skip=true'
            }
        }
       /*  stage('buildha test') {
            steps {
                docker.withServer('tcp://swarm.example.com:2376', 'swarm-certs') {
                    docker.image('maven:3-alpinex').withRun('-p 3306:3306') {
                             *//* do things *//*
                 }
             }
        } */

        stage('Build Docker Image') {
            steps {
               // sh 'docker build1 . --tag my-image:${build}'
               // sh 'docker image build -v /var/run/docker.sock:/var/run/docker.sock .'
                sh 'pwd'
                sh 'cd target && ls -la '
                sh 'cd .. ls -la '
                script {
                    docker.build("my-image:${build}")
                }
            }
        }
        stage('Run Image and BDD') {
            steps {
               sh 'docker run -d -it -p8089:9090 --name my-image my-image:${build}'
               sh 'sleep 5'
               sh 'curl --request GET http://localhost:8089/get'
            }
        }
    }
    post {
            always {
                echo 'Stop container. Dont forget to prune!'
                // cleanWs()
                sh 'docker stop my-image'

            }
        }
}