package org.jgroups.etcd.api;

public interface Response {

  String getAction();

  Node getNode();
}
