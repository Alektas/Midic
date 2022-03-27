pipeline {
    agent {
        docker {
            image 'androidsdk-32:0.1.2'
        }
    }
    stages {
        stage('Init') {
            steps {
                sh 'chmod +x gradlew'
                sh "./gradlew"
            }
        }
        stage('Build') {
            steps {
                sh './gradlew clean app:build'
            }
        }
        stage('Analysis') {
            steps {
                sh './gradlew app:lint'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew app:test'
                junit '*/build/test-results/**/TEST-*.xml'
            }
        }
    }
    post {
        always {
            archiveArtifacts(artifacts: '**/build/reports/**', allowEmptyArchive: true)
        }
    }
}
