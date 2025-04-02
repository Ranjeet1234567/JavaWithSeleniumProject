pipeline {
    agent any

    tools {
        maven 'Maven_3.8.1'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Ranjeet1234567/JavaWithSeleniumProject.git'
            }
        }

        stage('Setup Environment') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Generate Reports') {
            steps {
                sh 'mvn surefire-report:report'
            }
        }

        stage('Cleanup') {
            steps {
                cleanWs()
            }
        }
    }
}
