pipeline {
    agent any
    stages {

        stage('Build Interface') {
            steps {
                build job: 'jogayjoga-sport', wait: true
            }
        }

        stage('Build') { 
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Image') {
            steps {
                script {
                    sport = docker.build("eriksoaress/sport:${env.BUILD_ID}", "-f Dockerfile .")
                }
            }
        }

        stage('Push Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credential') {
                        sport.push("${env.BUILD_ID}")
                        sport.push("latest")
                    }
                }
            }
        }
    }
}