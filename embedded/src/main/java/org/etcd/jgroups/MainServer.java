package org.etcd.jgroups;

import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.jboss.resteasy.test.TestPortProvider;
import org.jgroups.etcd.rest.EtcdJaxRS;

public class MainServer {

    public static void main(String[] args) {

        System.out.println("Starting Embedded Server");
        TJWSEmbeddedJaxrsServer tjws = new TJWSEmbeddedJaxrsServer();
        ResteasyDeployment deployment = new ResteasyDeployment();
        deployment.setApplication(new EtcdJaxRS());
        tjws.setDeployment(deployment);
        tjws.setBindAddress(TestPortProvider.getHost());
        tjws.setPort(TestPortProvider.getPort());
        //tjws.setRootResourcePath("");
        tjws.setSecurityDomain(null);
        tjws.start();
    }

}
