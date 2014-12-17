package org.jgroups.etcd.api;

import javax.ws.rs.*;

@Path("/v2/keys")
public interface Etcd {

  @GET
  @Path("/{key}")
  @Produces("application/json")
  Response get(@PathParam("key") String key);

  @PUT
  @Path("/{key}")
  @Consumes("application/json")
  @Produces("application/json")
  Response put(@PathParam("key") String key, String value);

  @DELETE
  @Path("/{key}")
  @Produces("application/json")
  Response delete(@PathParam("key") String key);

}
