#!/usr/bin/groovy
/**
 * Need to look like you're busy? Update grails plugins!
 * Still needs to be cleaned up a little. (When I get to it)
 *
 * @author Micah Breedlove <druid628@gmail.com>
 */

import java.util.Random

def ranDumb     = new Random();
def scriptDir   = new File(getClass().protectionDomain.codeSource.location.path).parent;
def displayDir  = "/mnt/servers"
def pluginsList = [
          "spring-security-core", 
          "dojo", 
          "hibernate", 
          "tomcat", 
          "searchable", 
          "spring-social-core", 
          "uploadr"
        ];
def grailsHeading = "Using Java at: /usr/lib/jvm/java-6-openjdk\n" +
      "Welcome to Grails 1.3.7 - http://grails.org/\nLicensed under Apache Standard License 2.0\n" +
      "Grails home is set to: /usr/share/grails/1.3.7\nBase Directory:  ${scriptDir}\nResolving dependencies...\n" +
      "Dependencies resolved in ${(ranDumb.nextInt(2222-1+1)+1)}ms.\nRunning script /usr/share/grails/1.3.7/scripts/InstallPlugin.groovy\n" +
      "Environment set to development\nResolving plugin spring-security-core. Please wait...\n";


println grailsHeading;

while(true)
{
  pluginsList.each() {
    sleep(5000)

    major = ranDumb.nextInt(5-1+1)+1
    minor = ranDumb.nextInt(8-1+1)+1
    rev = ranDumb.nextInt(15-1+1)+1
    build = ranDumb.nextInt(6-1+1)+1

    def pluginTxt1 = "Downloading: http://svn.codehaus.org/grails-plugins/grails-${it}/tags/RELEASE_${major}_${minor}_${rev}_${build}/grails-${it}-${major}.${minor}.${rev}.${build}.zip ...\n"+
          "Download complete.\n"+
          "Installing zip ~/.ivy2/cache/org.grails.plugins/${it}/zips/${it}-${major}.${minor}.${rev}.${build}.zip... ...\n" +
          "[mkdir] Created dir: ${scriptDir}/.grails/1.3.7/projects/prodweb02/plugins/${it}-${major}.${minor}.${rev}.${build}\n" +
          "[unzip] Expanding: ${scriptDir}/.ivy2/cache/org.grails.plugins/${it}/zips/${it}-${major}.${minor}.${rev}.${build}.zip into ${scriptDir}/.grails/1.3.7/projects/prodweb02/plugins/${it}-${major}.${minor}.${rev}.${build}\n" +
          "Installed plugin ${it}-${major}.${minor}.${rev}.${build} to location ${scriptDir}/.grails/1.3.7/projects/prodweb02/plugins/${it}-${major}.${minor}.${rev}.${build}. ...\n" +
          "Resolving plugin JAR dependencies ...\n" +
          "Executing ${it}-${major}.${minor}.${rev}.${build} plugin post-install script ...\n";
    def pluginTxt2 = "Copying ${it}.js file into the application ...\n" +
          "[mkdir] Created dir: ${displayDir}/prodweb02/web-app/js/${it}/${major}.${minor}.${rev}/${it}\n" +
          "[copy] Copying 2 files to ${displayDir}/prodweb02/web-app/js/${it}/${major}.${minor}.${rev}/${it}\n" +
          "Done.'/web-app/js/${it}/${major}.${minor}.${rev}/${it}/${it}.js' has been copied into the application.\n" +
          "\n" +
          "Plugin ${it}-${major}.${minor}.${rev}.${build} installed\n" +
          "Plugin provides the following new scripts:\n" +
          "------------------------------------------\n" +
          "grails install-custom-${it}\n"; 

    def pluginTxt3 = "[delete] Deleting: ${scriptDir}/.grails/1.3.7/projects/prodweb02/resources/web.xml\n" +
          "[delete] Deleting directory ${displayDir}/prodweb02/target/classes\n" +
          "[delete] Deleting directory ${scriptDir}/.grails/1.3.7/projects/prodweb02/plugin-classes\n" +
          "[delete] Deleting directory ${scriptDir}/.grails/1.3.7/projects/prodweb02/resources\n";
          
    println pluginTxt1;
    sleep(ranDumb.nextInt(3000-1000+1)+1000)
    println pluginTxt2;
    sleep(ranDumb.nextInt(2000-1000+1)+1000)
    println pluginTxt3;
  }
}
  
