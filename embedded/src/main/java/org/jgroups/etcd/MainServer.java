package org.jgroups.etcd;

import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.jboss.resteasy.test.TestPortProvider;
import org.jgroups.etcd.rest.EtcdRestImpl;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class MainServer extends Application {

  private final Set<Class<?>> resourcesClass;
  private final Set<Object> resources;

  public MainServer() {
    resourcesClass = new HashSet<>();
    resourcesClass.add(EtcdRestImpl.class);

    resources = new HashSet<>();
    resources.add(new EtcdRestImpl());
  }

  @Override
  public Set<Class<?>> getClasses() {
    return resourcesClass;
  }

  @Override
  public Set<Object> getSingletons() {
    return resources;
  }


  public static void main(String[] args) {

    System.out.println("Starting Embedded Server");
    TJWSEmbeddedJaxrsServer tjws = new TJWSEmbeddedJaxrsServer();
    ResteasyDeployment deployment = new ResteasyDeployment();
    deployment.setApplication(new MainServer());
    tjws.setDeployment(deployment);
    tjws.setBindAddress(TestPortProvider.getHost());
    tjws.setPort(9000);
    tjws.setSecurityDomain(null);
    tjws.start();
  }

}
