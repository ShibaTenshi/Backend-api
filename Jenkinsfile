pipeline {
     agent any
     environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-hub')
        IMAGE = 'oyashiz/shibaqueue-api'
     }

     stages {
          stage('Mvn Build') {
               steps {
                    sh 'mvn clean'
                    sh 'mvn package -DskipTests'
               }
          }
          stage('Docker Build') {
                         steps {
                              sh 'docker image rm ${IMAGE} -f'
                              sh 'docker build -t ${IMAGE} .'
                         }
                    }
          stage('Docker Login') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
          }
          stage('Docker Push') {
                      steps {
                          sh 'docker push ${IMAGE}'
                          sh 'docker image rm ${IMAGE} -f'
                      }
          }
     }
     post {
        always {
            sh 'docker logout'
            cleanWs(cleanWhenNotBuilt: false,
                                deleteDirs: true,
                                disableDeferredWipeout: true,
                                notFailBuild: true,
                                patterns: [[pattern: '.gitignore', type: 'INCLUDE'],
                                           [pattern: '.propsfile', type: 'EXCLUDE']])
        }
     }
}