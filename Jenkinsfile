pipeline {
    agent any
    environment {
        APP_NAME = 'My-App'
        APP_VERSION = '1.0.0'
        PACKAGE_NAME = 'My-App'
    }
    stages {
        // 构建 jar
        stage('Build') {
            agent {
                docker {
                    image 'maven:3.6.3-slim'
                }
            }
            steps {
                sh 'sh ./jenkins/scripts/build.sh'
                // 暂存 Jar 包，避免不同 agent 下取不到文件
                stash includes: '**/target/*.jar', name: 'jar'
            }
        }

        // 单元测试
        stage('Test') {
            steps {
                sh 'sh ./jenkins/scripts/test.sh'
            }
        }

        // 部署容器
        stage('Deploy') {
            environment {
                IMAGE_NAME = 'my-app'
                IMAGE_VERSION = '1.0.0'
                SERVER_PORT = '7072'
            }
            steps {
                unstash 'jar'
                sh 'sh ./jenkins/scripts/deploy.sh'
            }
            post {
                failure {
                    echo "部署失败"
                }
            }
        }
    }
    //    全局post
    post {
        always {
            echo "Always"
        }
        success {
            echo "Success"
        }
        failure {
            echo "Failure"
        }
    }
}