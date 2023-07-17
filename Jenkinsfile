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
	  post {
	        always {
		        junit "**/target/Test-reports/report.xml"
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