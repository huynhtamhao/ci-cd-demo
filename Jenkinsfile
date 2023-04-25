pipeline {
    agent any

    tools {
        gradle "GRADLE_7.6.1"
        jdk "JDK_17"
    }

    environment {
        BUILD_TRIGGER_BY = "${currentBuild.getBuildCauses()[0].shortDescription}"
        VERSION = "${env.BUILD_ID}-${env.GIT_COMMIT.take(6)}"
        IMAGE_LATEST = "huynhtamhao/cicd-demo:latest"
        DOCKER_HUB_LOGIN = credentials("docker-hub-account")
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
                sh "gradle docker -PimageName=${IMAGE_LATEST}"
            }
        }

        stage("Push Docker Image to Registry") {
            steps {
                sh "docker login \
                      --username=${DOCKER_HUB_LOGIN_USR} \
                      --password=${DOCKER_HUB_LOGIN_PSW} \
                      && docker push ${IMAGE_LATEST}"
            }
        }
    }
}