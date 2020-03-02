library identifier: 'custom-lib@master', retriever: modernSCM(
  [$class: 'GitSCMSource',
   remote: 'https://github.com/jfbourner/groovy-helloworld.git',
   credentialsId: 'github-pat'])

pipeline {
    agent any
    environment {
        build = "${env.BUILD_ID}"
    }
    stages {
        stage('jenkins') {
            steps {
                sh 'echo $HOME'
                }
            }
        stage('Unit Test') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v $HOME/.m2:/root/.m2'
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
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn clean package -Dmaven.test.skip=true'
                sayHello 'JACK'
                sleep 500
            }
        }
          stage('buildha test') {
            agent {
              docker {
                  image 'quay.io/buildah/stable'
                  args '--privileged -u 0'
              }
          }
          steps {
              sh 'buildah bud -t jackimage . && buildah push --creds=jfbourner:Pieisgood1 jackimage jfbourner/jackimage:latest'

          }
        }

        stage('Build Docker Image') {
        steps {
               // sh 'docker build1 . --tag my-image:${build}'
               // sh 'docker image build -v /var/run/docker.sock:/var/run/docker.sock .'
                script {
                    def testImage = docker.build("my-image:${build}")
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
    post {
        always {
            echo 'Stop container. Dont forget to prune!'
            // cleanWs()
           // sh 'docker stop my
        }
    }
}