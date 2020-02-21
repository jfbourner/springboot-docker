pipeline {
    agent any
    environment {
        build = "${env.BUILD_ID}"
        def testImage
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
                sh 'sleep 50000'
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
                script {
                  testImage = docker.build("my-image:${build}")
                }
            }
        }
        stage('Run Image and BDD') {
            steps {
                script {
                    docker.image('my-image:${build}').withRun('-p 8089:9090 --name my-image') { c ->
                        /* Wait until my-image service is up */
                        sh 'sleep 5'
                        sh 'curl --request GET http://localhost:8089/get'
                    }
                }
            }
        }
        stage('publish image') {
            steps {
                script {
                    testImage.push('latest')
                    }
                }
            }
        }
    }
    post {
            always {
                echo 'Stop container. Dont forget to prune!'
                // cleanWs()
               // sh 'docker stop my-image'

            }
        }
}