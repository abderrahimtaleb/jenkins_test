pipeline {
    agent { docker { image 'maven:3.3.3' } }
    stages {
        stage('build') {
            steps {
            timeout(time : 1, unit : 'SECONDS'){
                      sh 'mvn --version'
                      sh 'mvn install clean'
            }
            }
        }
    }
}
