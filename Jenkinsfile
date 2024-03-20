pipeline {

    stages{
        stage('1 - Build') {
            agent {
                docker { image 'gradle:jdk21-alpine'}
            }
            steps {
                echo 'Building the application...'
                sh "./gradlew build -x test"
            }
        }
        stage('2 - Test') {
            agent {
                docker { image 'gradle:jdk21-alpine'}
            }
            steps {
                echo 'Testing the application...'
                sh "./gradlew clean test"
            }
        }
        stage('3 - Deploy') {
            steps {
                echo 'deploying the application...'
            }
        }
    }
}

