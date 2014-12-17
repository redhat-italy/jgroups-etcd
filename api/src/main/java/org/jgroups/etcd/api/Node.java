package org.jgroups.etcd.api;

public interface Node {

  Long getCreatedIndex();
  Long getModifiedIndex();

  String getKey();
  String getValue();

}
