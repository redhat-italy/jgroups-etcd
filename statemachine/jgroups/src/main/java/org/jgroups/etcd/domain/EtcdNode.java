package org.jgroups.etcd.domain;

import org.jgroups.etcd.api.Node;

public class EtcdNode implements Node {

  private String key;
  private EtcdNodeValue value;

  public EtcdNode() {
  }

  public EtcdNode(String key, EtcdNodeValue value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public Long getCreatedIndex() {
    return value.getCreatedIndex();
  }

  @Override
  public Long getModifiedIndex() {
    return value.getModifiedIndex();
  }

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public String getValue() {
    return value.getValue();
  }
}
