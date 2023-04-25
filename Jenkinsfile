pipeline {
    agent any

    tools {
        gradle "GRADLE_7.6.1"
        jdk "JDK_17"
    }

    environment {
        BUILD_TRIGGER_BY = "${currentBuild.getBuildCauses()[0].shortDescription}"
        VERSION = "${env.BUILD_ID}-${env.GIT_COMMIT.take(6)}"
        IMAGE = "demo-ci-cd:${VERSION}"
        IMAGE_LATEST = "demo-ci-cd:latest"
    }

    stages {
        stage('SonarQube Analysis') {
            steps {
                sh "gradle sonarqube"
            }
        }

        stage("Build Project") {
            steps {
                sh "gradle clean build"
            }
        }

        stage("Build Docker Image") {
            steps {
                sh "gradle docker -PimageName=${IMAGE}"
            }
        }


    }
}