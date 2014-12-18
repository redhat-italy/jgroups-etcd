package org.jgroups.etcd.rest;

import org.jgroups.etcd.EtcdImpl;
import org.jgroups.etcd.api.Etcd;
import org.jgroups.etcd.api.Response;
import org.jgroups.etcd.api.raft.EtcdStateMachine;
import org.jgroups.etcd.support.ServiceFactory;

import javax.ws.rs.*;

@Path("/v2/keys")
public class EtcdJaxRS implements Etcd {

  private final Etcd etcd;

  public EtcdJaxRS() {
    EtcdStateMachine stateMachine = ServiceFactory.<EtcdStateMachine>loadFactory(EtcdStateMachine.class);
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
