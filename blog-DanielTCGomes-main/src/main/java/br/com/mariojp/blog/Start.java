package br.com.mariojp.blog;


import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.WebResourceSet;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.EmptyResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.eclipse.jdt.internal.compiler.batch.Main;
public class Start {
	

	private static File getRootFolder() {

        try {
            File root;
            String runningJarPath = Start.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath().replaceAll("\\\\", "/");
            int lastIndexOf = runningJarPath.lastIndexOf("/target/");
            if (lastIndexOf < 0) {
                root = new File("");
            } else {
                root = new File(runningJarPath.substring(0, lastIndexOf));
            }
            //System.out.println("application resolved root folder: " + root.getAbsolutePath());
            return root;
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }


	public static void main(String[] args) throws Exception {

        String webPort = "8080";//ServerConstants.LOCAL_MACHINE_PORT;

        int port = Integer.parseInt(webPort);
        final Properties systemProperties = System.getProperties();

        for (Map.Entry<Object, Object> entry : systemProperties.entrySet()) {
            System.out.println("property: " + entry.getKey() + ", value: " + entry.getValue());
        }

        File root = getRootFolder();
        System.setProperty("org.apache.catalina.startup.EXIT_ON_INIT_FAILURE", "true");

        Tomcat tomcat = new Tomcat();
        tomcat.getServer().setPort(-1);

        Connector connector = tomcat.getConnector();
        connector.setProperty("protocol", "org.apache.coyote.http11.Http11Nio2Protocol");
        connector.setProperty("useAsyncIO", "true");
        connector.setProperty("maxThreads", "8");
        connector.setPort(port);

        Path tempPath = Files.createTempDirectory("tomcat-base-dir");
        tomcat.setBaseDir(tempPath.toString());

        tomcat.setPort(port);
        File webContentFolder = new File(root.getAbsolutePath(), "src/main/webapp/");
        if (!webContentFolder.exists()) {
            webContentFolder = Files.createTempDirectory("default-doc-base").toFile();
        }
        StandardContext ctx = (StandardContext) tomcat.addWebapp("/blog", webContentFolder.getAbsolutePath());
        ctx.setParentClassLoader(Main.class.getClassLoader());

        System.out.println("configuring app with basedir: " + webContentFolder.getAbsolutePath());

        File additionWebInfClassesFolder = new File(root.getAbsolutePath(), "target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);

        WebResourceSet resourceSet;
        if (additionWebInfClassesFolder.exists()) {
            resourceSet = new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClassesFolder.getAbsolutePath(), "/");
            System.out.println("loading WEB-INF resources from as '" + additionWebInfClassesFolder.getAbsolutePath() + "'");
        } else {
            resourceSet = new EmptyResourceSet(resources);
        }
        resources.addPreResources(resourceSet);
        ctx.setResources(resources);

        tomcat.start();

        tomcat.getServer().await();

                
	}
}