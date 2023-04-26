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
        SERVER_USER = "root"
        SERVER_HOST = "45.76.158.74"
        SERVER_ACCOUNT = "vultr-server-account"
        CONTAINER_NAME = "ci-cd-demo"
    }


    post {
        always {
            discordSend description: "${BUILD_TRIGGER_BY} build on branch: ${env.GIT_BRANCH}",
                    footer: "Amaris Jenkins",
                    link: env.BUILD_URL,
                    result: currentBuild.currentResult,
                    unstable: false,
                    title: JOB_NAME,
                    webhookURL: "${DISCORD_WEBHOOK_URL}"
        }
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

        stage("Cleaning up") {
            steps{
                sh "docker rmi \$(docker images --format '{{.Repository}}:{{.Tag}}' | grep '${IMAGE_LATEST}')"
            }
        }

        stage("Deploy to Dev") {
            steps {
                echo "Deploying..."
                script {
                    def remote = [:]
                    remote.name = "${SERVER_USER}"
                    remote.host = "${SERVER_HOST}"
                    remote.allowAnyHosts = true
                    withCredentials([usernamePassword(credentialsId: "${SERVER_ACCOUNT}",
                            passwordVariable: "password",
                            usernameVariable: "userName"
                    )]) {
                        remote.user = userName
                        remote.password = password
                        sshCommand remote: remote, command: "docker stop ${CONTAINER_NAME} || true && docker rm ${CONTAINER_NAME} || true"
                        sshCommand remote: remote, command: "docker rmi \$(docker images --format '{{.Repository}}:{{.Tag}}' | grep '${IMAGE_LATEST}') || true"
                        sshCommand remote: remote, command: "docker run -d --name ${CONTAINER_NAME} \
                              -p 8080:8080 -v ~/demo-logs:/usr/src/app/logs ${IMAGE_LATEST}"
                    }
                }
                echo "Docker Image: ${NEXUS_DOCKER_HUB}/${IMAGE}"
            }
        }
    }
}