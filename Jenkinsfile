pipeline {
    agent { docker { image 'maven:3.3.3' } }
    environment {
        SUCCESS_MSG = 'This will run only if successful'
    }
    stages {
        stage('build') {
            steps {
                timeout(time : 1, unit : 'MINUTES'){
                    retry(5){
                          sh 'mvn --version'
                          sh 'mvn install clean'
                      }
                    }

            }
        }
    }
                post{
                            always {
                                echo 'Always'
                            }
                            success{
                                 echo '';
                            }
                            failure{
                                 echo 'This will run only if failed'
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
