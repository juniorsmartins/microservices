pipeline {
    agent {
        docker { image 'jdk'}
//         jdk 'amazoncorretto:21.0.1-al2023-headful'
//         gradle 'jdk21-alpine'
    }

    stages{
        stage('1 - Build') {
            steps {
                echo 'Building the application...'
                sh "./gradlew build -x test"
            }
        }
        stage('2 - Test') {
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

