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
def grailsHeading = """
Using Java at: /usr/lib/jvm/java-6-openjdk
Welcome to Grails 1.3.7 - http://grails.org/
Licensed under Apache Standard License 2.0
Grails home is set to: /usr/share/grails/1.3.7
Base Directory:  ${scriptDir}
Resolving dependencies...
Dependencies resolved in ${(ranDumb.nextInt(2222-1+1)+1)}ms.
Running script /usr/share/grails/1.3.7/scripts/InstallPlugin.groovy
Environment set to development
Resolving plugin spring-security-core. Please wait...
"""


println grailsHeading;

while(true)
{
  pluginsList.each() {
    sleep(5000)

    major = ranDumb.nextInt(5-1+1)+1
    minor = ranDumb.nextInt(8-1+1)+1
    rev = ranDumb.nextInt(15-1+1)+1
    build = ranDumb.nextInt(6-1+1)+1

    def pluginTxt1 = """Downloading: http://svn.codehaus.org/grails-plugins/grails-${it}/tags/RELEASE_${major}_${minor}_${rev}_${build}/grails-${it}-${major}.${minor}.${rev}.${build}.zip ...
Download complete.
Installing zip ~/.ivy2/cache/org.grails.plugins/${it}/zips/${it}-${major}.${minor}.${rev}.${build}.zip... ...
[mkdir] Created dir: ${scriptDir}/.grails/1.3.7/projects/prodweb02/plugins/${it}-${major}.${minor}.${rev}.${build}
[unzip] Expanding: ${scriptDir}/.ivy2/cache/org.grails.plugins/${it}/zips/${it}-${major}.${minor}.${rev}.${build}.zip into ${scriptDir}/.grails/1.3.7/projects/prodweb02/plugins/${it}-${major}.${minor}.${rev}.${build}
Installed plugin ${it}-${major}.${minor}.${rev}.${build} to location ${scriptDir}/.grails/1.3.7/projects/prodweb02/plugins/${it}-${major}.${minor}.${rev}.${build}. ...
Resolving plugin JAR dependencies ...
Executing ${it}-${major}.${minor}.${rev}.${build} plugin post-install script ...
"""
    def pluginTxt2 = """Copying ${it}.js file into the application ...
[mkdir] Created dir: ${displayDir}/prodweb02/web-app/js/${it}/${major}.${minor}.${rev}/${it}
[copy] Copying 2 files to ${displayDir}/prodweb02/web-app/js/${it}/${major}.${minor}.${rev}/${it}
Done.'/web-app/js/${it}/${major}.${minor}.${rev}/${it}/${it}.js' has been copied into the application.

Plugin ${it}-${major}.${minor}.${rev}.${build} installed
Plugin provides the following new scripts:
------------------------------------------
grails install-custom-${it}
"""

    def pluginTxt3 = """[delete] Deleting: ${scriptDir}/.grails/1.3.7/projects/prodweb02/resources/web.xml
[delete] Deleting directory ${displayDir}/prodweb02/target/classes
[delete] Deleting directory ${scriptDir}/.grails/1.3.7/projects/prodweb02/plugin-classes
[delete] Deleting directory ${scriptDir}/.grails/1.3.7/projects/prodweb02/resources
"""
          
    println pluginTxt1;
    sleep(ranDumb.nextInt(3000-1000+1)+1000)
    println pluginTxt2;
    sleep(ranDumb.nextInt(2000-1000+1)+1000)
    println pluginTxt3;
  }
}
  
