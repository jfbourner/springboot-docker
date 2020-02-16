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
        stage('buildha test') {
            agent {
                docker {
                    image 'tomkukral/buildah'
                }
            }
            steps {
                sh 'buildah bud -f Dockerfile -t fedora-httpd .'
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
                    docker.build("my-image:${build}")
                }
            }
        }
        stage('Run Image and BDD') {
            steps {
               sh 'docker run -d -it -p8089:8080 --name my-image my-image:${build}'
               sh 'netstat -an|grep LISTEN'
               sh 'curl --request GET http://localhost:8089/get'
            }
        }
    }
    post {
            always {
                echo 'Stop container. Dont forget to prune!'
                //sh 'docker stop my-image'

            }
        }
}