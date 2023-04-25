pipeline {
    agent any

    tools {
        gradle "GRADLE_7.6.1"
        jdk "JDK_17"
    }

    stages {
        stage('SonarQube Analysis') {
            steps {
                sh "gradle sonarqube"
            }
        }

        stage("Build") {
            steps {
                sh "gradle clean build"
            }
        }
    }
}