pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK21'
    }

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Archive Reports') {
            steps {
                junit 'target/surefire-reports/*.xml'
                archiveArtifacts artifacts: 'target/ExtentReport.html', fingerprint: true
            }
        }
    }

    post {
        always {
            echo 'Pipeline Execution Completed'
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> ba8d2aa (Fixed testng.xml name)
