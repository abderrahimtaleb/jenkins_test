pipeline {
    agent any
    environment {
        SUCCESS_MSG = 'This will run only if successful env'
        DOCKER_HOST = 'tcp://54.185.193.176:4243'
    }
    stages {
        stage('build') {
            steps {
                timeout(time : 1, unit : 'MINUTES'){
                    retry(5){
                          sh 'mvn install clean -DskipTests'
                          sh 'mvn compile'
                      }
                    }

            }
        }
        stage('Test') {
                    steps {
                        sh 'mvn clean test'
                        sh 'mvn surefire-report:report'
                    }
                }
        stage('package'){
                    steps {
                           sh 'mvn clean package'
                    }
        }
        stage('Check before deploy') {
                    steps {
                        input 'Can i deploy to prod ?'
                    }
              }
        stage('create docker image') {
                    steps {
                        sh 'mvn docker:build'
                    }
              }
        stage('run image') {
                    steps {
                        script{
                                docker.withServer("$DOCKER_HOST") {
                                    docker.image('jenkins-test').run('-p 80:8080')
                               }
                            }
                    }
              }
       
    }
    post{
                 always {
                           junit "target/surefire-reports/*.xml"
                         }
                 success{
                         echo SUCCESS_MSG
                         }
                 failure{
                        mail to: 'sc.abderrahim.taleb@gmail.com',
                             subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                             body: "Something is wrong with ${env.BUILD_URL}"
                        }
                 unstable{
                         echo 'This will run only if the run was marked as unstable'
                         }
                 changed{
                         echo 'This will run only if the state of the Pipeline has changed'
                         echo 'For example, if the Pipeline was previously failing but is now successful'
                         }
         }
}
