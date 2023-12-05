node {
  def app

    stage('detetct github changes'){
        echo 'detecting repo changes...'
        checkout scm
    }
  stage ('build docker image'){
    echo 'building the application... '
    app = docker.build('nahomtekeste/coursework2')
  }
  stage('push image to docker') {
    echo 'pushing the application to dockerhub...'
    docker.withRegistry'https://registry.hub.docker.com' , 'docker-hub-credintials'){
    app.push("${env.BUILD_NUMBER}")
      app.push('latest')
    }
     
  }
  
}
