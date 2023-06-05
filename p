[INFO] Scanning for projects...
[WARNING] 
[WARNING] Some problems were encountered while building the effective model for org.apache.isis.tutorials:miCiudad-webapp:jar:1.0.0-SNAPSHOT
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: org.apache.isis.tutorials:miCiudad-module-miCiudad:jar -> version 1.0.0-SNAPSHOT vs (?) @ line 117, column 21
[WARNING] 
[WARNING] It is highly recommended to fix these problems because they threaten the stability of your build.
[WARNING] 
[WARNING] For this reason, future Maven versions might no longer support building such malformed projects.
[WARNING] 
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO] 
[INFO] miCiudad - Parent                                                  [pom]
[INFO] miCiudad - miCiudad Module                                         [jar]
[INFO] miCiudad - Webapp                                                  [jar]
[INFO] 
[INFO] -----------------< org.apache.isis.tutorials:miCiudad >-----------------
[INFO] Building miCiudad - Parent 1.0.0-SNAPSHOT                          [1/3]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary for miCiudad - Parent 1.0.0-SNAPSHOT:
[INFO] 
[INFO] miCiudad - Parent .................................. FAILURE [  0.004 s]
[INFO] miCiudad - miCiudad Module ......................... SKIPPED
[INFO] miCiudad - Webapp .................................. SKIPPED
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.562 s
[INFO] Finished at: 2023-05-28T16:46:51-03:00
[INFO] ------------------------------------------------------------------------
[ERROR] Unknown lifecycle phase "webapp". You must specify a valid lifecycle phase or a goal in the format <plugin-prefix>:<goal> or <plugin-group-id>:<plugin-artifact-id>[:<plugin-version>]:<goal>. Available lifecycle phases are: validate, initialize, generate-sources, process-sources, generate-resources, process-resources, compile, process-classes, generate-test-sources, process-test-sources, generate-test-resources, process-test-resources, test-compile, process-test-classes, test, prepare-package, package, pre-integration-test, integration-test, post-integration-test, verify, install, deploy, pre-clean, clean, post-clean, pre-site, site, post-site, site-deploy. -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/LifecyclePhaseNotFoundException
