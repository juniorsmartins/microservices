pipeline {
    agent any

    stages{
        stage('1 - Build Back-end') {
            steps {
                echo 'Limpar e construir os arquivos .jar'

                script {
                    // Comando find para localizar os arquivos build.gradle e pom.xml em cada subdiretório
                    def gradleProjects = sh(script: 'find . -name build.gradle -exec dirname {} \\;', returnStdout: true).trim().split('\n')
                    def mavenProjects = sh(script: 'find . -name pom.xml -exec dirname {} \\;', returnStdout: true).trim().split('\n')

                    // Para cada projeto encontrado, execute o comando Gradle ou Maven, dependendo do que for encontrado
                    for (def project in gradleProjects) {
                        sh "cd ${project} && ./gradlew clean build -x test"
                    }

                    for (def project in mavenProjects) {
                        sh "cd ${project} && mvn clean package -DskipTests=true"
                    }
                }
            }
        }
        stage('2 - Unit Tests') {
            steps {
                echo 'Testing the application...'
//                 sh "./gradlew test"
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

