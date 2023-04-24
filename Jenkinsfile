pipeline {
    agent any

    tools {
        gradle "GRADLE_7.6.1"
        jdk "JDK_17"
    }

    stages {
        stage('SonarQube Analysis') {
            withSonarQubeEnv() {
                sh "./gradlew sonar"
            }
        }

//        stage("Build") {
//            steps {
//                sh "gradle clean build"
//            }
//        }
    }
}