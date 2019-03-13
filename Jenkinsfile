node {
    environment {
        SUCCESS_MSG = 'This will run only if successful env'
        //DOCKER_HOST = 'tcp://34.219.140.176:4243'
    }
        stage('init mvn'){
                docker.image('maven:3.3.3').pull()
        }
        stage('build') {
                timeout(time : 1, unit : 'MINUTES'){
                    retry(5){
                          sh 'mvn --version'
                          sh 'mvn install clean -DskipTests'
                      }
                    }    
        }
        stage('Test') {
                        sh 'mvn clean test'
                        sh 'mvn surefire-report:report'
                    
                }
        stage('package'){
                           sh 'mvn clean package'
                    
        }
        stage('Check before deploy') {
                        input 'Can i deploy to prod ?'
                    
              }
        stage('create docker image') {
                        sh 'mvn docker:build'
                    
              }
        stage('run image') {
                        //sh 'docker -H tcp://34.219.140.176:4243 run -d -p 80:8080 jenkins-test'
                        docker.withServer('tcp://54.185.3.48:4243', 'swarm-certs') {
                              docker.image('jenkins-test').run('-p 80:8080') 
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
