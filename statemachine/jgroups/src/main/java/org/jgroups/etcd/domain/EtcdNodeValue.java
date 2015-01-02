package org.jgroups.etcd.domain;

import org.jgroups.etcd.api.Node;

public class EtcdNodeValue implements Node {

  public static final EtcdNodeValue empty = new EtcdNodeValue(-1L, -1L, "");

  private Long createdIndex;
  private Long modifiedIndex;
  private String value;

  public EtcdNodeValue() {
  }

  public EtcdNodeValue(Long createdIndex, Long modifiedIndex, String value) {
    this.createdIndex = createdIndex;
    this.modifiedIndex = modifiedIndex;
    this.value = value;
  }

  @Override
  public Long getCreatedIndex() {
    return createdIndex;
  }

  @Override
  public Long getModifiedIndex() {
    return modifiedIndex;
  }

  @Override
  public String getKey() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getValue() {
    return value;
  }

  public EtcdNodeValue modifyValue(Long currentIndex, String newValue) {
    return new EtcdNodeValue(createdIndex, currentIndex, newValue);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof EtcdNodeValue)) return false;

    EtcdNodeValue that = (EtcdNodeValue) o;

    if (!createdIndex.equals(that.createdIndex)) return false;
    if (!modifiedIndex.equals(that.modifiedIndex)) return false;
    if (value != null ? !value.equals(that.value) : that.value != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = createdIndex.hashCode();
    result = 31 * result + modifiedIndex.hashCode();
    result = 31 * result + (value != null ? value.hashCode() : 0);
    return result;
  }
}
