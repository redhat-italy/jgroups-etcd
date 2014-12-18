package org.jgroups.etcd.domain;

import org.jgroups.etcd.api.Node;
import org.jgroups.etcd.api.Response;

public class ResponseBuilder implements Response {

  private final String action;
  private final Node node;

  private ResponseBuilder(String action, Node node) {
    this.action = action;
    this.node = node;
  }

  @Override
  public String getAction() {
    return action;
  }

  @Override
  public final Node getNode() {
    return node;
  }

  public static Response actionGet(Node node) {
    return new ResponseBuilder("get", node);
  }

  public static Response actionPut(Node node) {
    return new ResponseBuilder("put", node);
  }

  public static Response actionDelete(Node node) {
    return new ResponseBuilder("delete", node);
  }
}
