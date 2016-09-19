###Intalling jcsp-1.1-rc4.jar to a local Maven Repo

1. Download jcsp library from here: https://www.cs.kent.ac.uk/projects/ofa/jcsp/

2. Unzip the downloaded jcsp-1.1-rc4.jar to E:\source\csp-experiments\jcsp-experiments\dependencies

3. In git shell, run the following command to install jcsp.jar to local Maven repo:

    	Simeon@EXCELSIOR /e/source/csp-experiments/jcsp-experiments/dependencies (master)
    	$ mvn -X install:install-file -Dfile=E:\\source\\csp-experiments\\jcsp-experiments\\dependencies\\jcsp-1.1-rc4\\jcsp.jar -D groupId=org.jcsp -DartifactId=jscp -Dversion=1.1-rc4 -Dpackaging=jar