pipeline {
    agent any

    stages{
        stage('1 - Build Back-end') {
            steps {
                echo 'Building the application...'
                sh "./gradlew build -x test"
            }
        }
        stage('2 - Unit Tests') {
            steps {
                echo 'Testing the application...'
                sh "./gradlew clean test"
            }
        }
        stage('3 - Sonar Analysis') {
            steps {

            }
        }
        stage('4 - Quality Gate') {
            steps {

            }
        }
        stage('5 - Deploy Back-end') {
            steps {
                echo 'deploying the application...'
            }
        }
        stage('6 - API Tests') {
            steps {

            }
        }
        stage('7 - Deploy Front-end') {
            steps {

            }
        }
        stage('8 - Functional Tests') {
            steps {

            }
        }
        stage('9 - Deploy Prod') {
            steps {

            }
        }
        stage('10 - Health Check') {
            steps {

            }
        }
    }
}

