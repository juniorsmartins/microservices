pipeline {
    agent none

    stages{
        stage('1 - Build') {
            agent {
                docker { image 'gradle:jdk21-alpine'}
            }
            steps {
                echo 'Building the application...'
                sh "./gradlew build -x test"
//                 sh "mvn package -Dmaven.test.skip=true"
            }
        }
        stage('2 - Test') {
            agent {
                docker { image 'gradle:jdk21-alpine'}
            }
            steps {
                echo 'Building the application...'
                sh "./gradlew clean build"
            }
        }
        stage('3 - Deploy') {
            steps {
                echo 'deploying the application...'
            }
        }
    }
}

