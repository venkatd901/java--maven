pipeline{

      agent {
                docker {
                image 'maven'
                args '-u root -v $HOME/.m2:/root/.m2'
                }
            }
        
        stages{

              stage('deploy'){
                  steps{
                      script{
                            sh "cd demo"
		    	    sh "mvn clean deploy"
		  
                 	}
               	 }  
              }	
		
            }	       	     	         
}
