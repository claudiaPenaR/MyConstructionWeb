pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // Clonar el código fuente de GitHub
                git branch: 'master', url: 'https://github.com/claudiaPenaR/MyConstructionWeb.git'
            }
        }
        stage('Build') {
            steps {
                // Ejecutar el build usando Maven
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                // Ejecutar pruebas con Maven
                bat 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                // Aquí iría la lógica de despliegue, puede variar según el entorno
                echo 'Realizando el despliegue...'
            }
        }
    }
}
