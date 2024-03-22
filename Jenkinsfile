pipeline {
    agent any

    stages{
        stage('1 - Build Back-end') {
            steps {
                echo 'Building the application...'

                // Use o comando find para localizar os arquivos build.gradle em cada subdiretório
                script {
                    def projects = sh(script: 'find . -name build.gradle -exec dirname {} \\;', returnStdout: true).trim().split('\n')

                    // Para cada projeto encontrado, execute o comando Gradle
                    for (def project in projects) {
                        sh "cd ${project} && ./gradlew clean build -x test"
                    }
                }
            }
        }
        stage('2 - Unit Tests') {
            steps {
                echo 'Testing the application...'
//                 sh "./gradlew clean test"
            }
        }
        stage('3 - Sonar Analysis') {
            steps {
                echo '....'
            }
        }
        stage('4 - Quality Gate') {
            steps {
                echo '...'
            }
        }
        stage('5 - Deploy Back-end') {
            steps {
                echo 'deploying the application...'
            }
        }
        stage('6 - API Tests') {
            steps {
                echo '...'
            }
        }
        stage('7 - Deploy Front-end') {
            steps {
                echo '...'
            }
        }
        stage('8 - Functional Tests') {
            steps {
                echo '...'
            }
        }
        stage('9 - Deploy Prod') {
            steps {
                echo '...'
            }
        }
        stage('10 - Health Check') {
            steps {
                echo '...'
            }
        }
    }
}

