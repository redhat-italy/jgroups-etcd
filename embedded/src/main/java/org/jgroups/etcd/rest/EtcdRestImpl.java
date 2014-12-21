package org.jgroups.etcd.rest;

import org.jgroups.etcd.EtcdImpl;
import org.jgroups.etcd.api.Etcd;
import org.jgroups.etcd.api.Response;
import org.jgroups.etcd.raft.api.EtcdStateMachine;
import org.jgroups.etcd.raft.configuration.JGroupsConfigurationBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import java.util.Set;

@Path("/v2/keys")
public class EtcdRestImpl implements Etcd {

  private Etcd etcd;
  private EtcdStateMachine stateMachine;

  public EtcdRestImpl() {
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
