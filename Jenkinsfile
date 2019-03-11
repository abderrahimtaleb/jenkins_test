pipeline {
    agent { docker { image 'maven:3.3.3' } }
    environment {
        SUCCESS_MSG = 'This will run only if successful env'
    }
    stages {
        stage('build') {
            steps {
                timeout(time : 1, unit : 'MINUTES'){
                    retry(5){
                          sh 'mvn --version'
                          sh 'mvn install clean -DskipTests'
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
    }
    post{
         always {
                   junit "target/surefire-reports/*.xml"
                 }
         success{
                 echo SUCCESS_MSG;
                 }
         failure{
             echo mail to: "abderra.taleb@gmail.com",
                        subject: "Test Failure : ${currentBuild.fullDisplayName}",
                        body: "Test URL : ${env.BUILD_URL}"
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
