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
                          sh "./gradlew sonar"
                        }
//                         sh """
//                             cd ${project} &&
//                             ${sonarqubeScanner}/bin/sonar-scanner
//                             -e -Dsonar.projectKey=jenkins_mercado_financeiro
//                             -Dsonar.host.url=http://localhost:9000/
//                             -Dsonar.login=squ_35c655e2667a2228326356b315a3cfec80a8c92d
//                             -Dsonar.java.binaries=/build/libs
//                             -Dsonar.coverage.exclusions=**/build/**,**/src/test/**,**/model/**,**Application.java
//                         """
                    }
                }
//                 withSonarQubeEnv('') {
//                     sh """
//                         ${sonarqubeScanner}/bin/sonar-scanner
//                         -e -Dsonar.projectKey=jenkins_mercado_financeiro
//                         -Dsonar.host.url=http://localhost:9000
//                         -Dsonar.login=
//                         -Dsonar.java.binaries=target
//                         -Dsonar.coverage.exclusions=**/build/**,**/src/test/**,**/model/**,**Application.java
//                     """
//                 }
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

