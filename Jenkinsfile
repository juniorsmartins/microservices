pipeline {
    agent any

    stages{
        stage('1 - Build Back-end') {
            steps {
                echo 'Limpar e construir os arquivos .jar'

                script {
                    // Comando find para localizar os arquivos build.gradle e pom.xml em cada subdiretório
                    def gradleProjects = sh(script: 'find . -name build.gradle -exec dirname {} \\;', returnStdout: true).trim().split('\n')

                    // Para cada projeto encontrado, execute o comando Gradle ou Maven, dependendo do que for encontrado
                    for (def project in gradleProjects) {
                        sh "cd ${project} && ./gradlew clean build -x test"
                    }
                }
            }
        }

        stage('2 - Unit Tests') {
            steps {
                echo 'Rodar os testes automatizados'

                script {
                    // Comando find para localizar os arquivos build.gradle e pom.xml em cada subdiretório
                    def gradleProjects = sh(script: 'find . -name build.gradle -exec dirname {} \\;', returnStdout: true).trim().split('\n')

                    // Para cada projeto encontrado, execute o comando Gradle ou Maven, dependendo do que for encontrado
                    for (def project in gradleProjects) {
                        sh "cd ${project} && ./gradlew test"
                    }
                }
            }
        }

        stage('3 - Sonar Analysis') {
            environment {
                sonarqubeScanner = tool 'SONARQUBE_JENKINS'
            }
            steps {
                echo 'Sonarqube roda análise estática do código'

                script {
                    // Comando find para localizar os arquivos build.gradle e pom.xml em cada subdiretório
                    def gradleProjects = sh(script: 'find . -name build.gradle -exec dirname {} \\;', returnStdout: true).trim().split('\n')

                    // Para cada projeto encontrado, execute o comando Gradle ou Maven, dependendo do que for encontrado
                    for (def project in gradleProjects) {

                        withSonarQubeEnv() {
                            sh "cd ${project} && ./gradlew sonar"
                        }
//                         sh "cd ${project} && ./gradlew sonar"
//                         withSonarQubeEnv('sonarqube') {
//                             sh "cd ${project} && ${sonarqubeScanner}/bin/sonar-scanner sonar.projectKey=jenkins_mercado_financeiro sonar.host.url=http://127.0.0.1:9000 sonar.login=squ_35c655e2667a2228326356b315a3cfec80a8c92d sonar.java.binaries=build"
//                         }
                    }
                }
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

