pipeline {
    agent any
    
    tools {
        // Configurar Java 17
        jdk 'Java17'
        gradle 'Gradle8'
    }
    
    environment {
        // Variables de entorno
        GRADLE_OPTS = '-Xmx1024m'
        SONAR_TOKEN = credentials('sonar-token')
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo '🔄 Checking out code...'
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo '🔨 Building project...'
                sh 'gradle clean build --info'
            }
        }
        
        stage('Test') {
            steps {
                echo '🧪 Running tests...'
                sh 'gradle test --info'
            }
            post {
                always {
                    // Publicar resultados de tests
                    publishTestResults testResultsPattern: 'build/test-results/test/*.xml'
                    
                    // Archivar reportes de test
                    archiveArtifacts artifacts: 'build/reports/tests/**/*', allowEmptyArchive: true
                }
            }
        }
        
        stage('Allure Report') {
            steps {
                echo '📊 Generating Allure report...'
                sh './gradlew allureReport'
            }
            post {
                always {
                    // Publicar reporte Allure
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'build/allure-results']]
                    ])
                }
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                echo '🔍 Running SonarQube analysis...'
                withSonarQubeEnv('SonarCloud') {
                    sh 'gradle sonar'
                }
            }
        }
        
        stage('Quality Gate') {
            steps {
                echo '🚪 Waiting for Quality Gate...'
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: false
                }
            }
        }
    }
    
    post {
        always {
            echo '🧹 Cleaning up workspace...'
            cleanWs()
        }
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed!'
        }
    }
}
