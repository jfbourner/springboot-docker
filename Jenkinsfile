pipeline {
   agent {
       docker {
           image 'maven:3.3-jdk-8'
           registryUrl 'http://192.168.1.110:8081/repository/docker-group/'
           registryCredentialsId 'jackannamilllie'
       }
   }
    stages {
        stage('Test') {
            steps {
                sh 'node --version'
            }
        }
    }
}