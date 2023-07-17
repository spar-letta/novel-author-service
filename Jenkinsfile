pipeline {
    agent any
    stages {
        stage('clone repo and clean') {
            steps {
                sh "mvn clean"
            }
        }
        stage('Test') {
            steps {
                sh "mvn test"
            }
        }

        stage('Test') {
                    steps {
                        sh "mvn clean install"
                    }
                }
        stage("Build docker image"){
            steps{
                script{
                    sh "docker build -t javenockdocker/author-service.1.0 ."
                }
            }
        }

        stage('Deploy') {
            steps {
                sh "mvn package"
            }
        }

    }
}