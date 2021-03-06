package org.jgroups.etcd.rest;

import org.jgroups.etcd.EtcdImpl;
import org.jgroups.etcd.api.Etcd;
import org.jgroups.etcd.api.Response;
import org.jgroups.etcd.raft.api.EtcdStateMachine;
import org.jgroups.etcd.raft.api.EtcdStateMachineClient;
import org.jgroups.etcd.raft.configuration.JGroupsConfigurationBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
@Path("/v2/keys")
public class EtcdJaxRS extends Application implements Etcd {

  private Etcd etcd;
  private EtcdStateMachineClient stateMachine;

  public EtcdJaxRS() {
    stateMachine = new JGroupsConfigurationBuilder().build();
    etcd = new EtcdImpl(stateMachine);
  }

  @GET
  @Path("/{key}")
  @Produces("application/json")
  public Response get(@PathParam("key") String key) {
    return etcd.get(key);
  }

  @PUT
  @Path("/{key}")
  @Consumes("application/json")
  @Produces("application/json")
  public Response put(@PathParam("key") String key, String value) {
    return etcd.put(key, value);
  }

  @DELETE
  @Path("/{key}")
  @Produces("application/json")
  public Response delete(@PathParam("key") String key) {
    return etcd.delete(key);
  }
}
