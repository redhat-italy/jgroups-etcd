package org.jgroups.etcd;

import org.jgroups.etcd.api.Node;
import org.jgroups.util.Streamable;

import java.io.DataInput;
import java.io.DataOutput;

public class DummyNode implements Node, Streamable {

  public DummyNode() {
  }

  @Override
  public Long getCreatedIndex() {
    return -1L;
  }

  @Override
  public Long getModifiedIndex() {
    return -1L;
  }

  @Override
  public String getKey() {
    return "dummyKey";
  }

  @Override
  public String getValue() {
    return "dummyValue";
  }

  @Override
  public void writeTo(DataOutput dataOutput) throws Exception {
    dataOutput.write(1);
  }

  @Override
  public void readFrom(DataInput dataInput) throws Exception {
    assert dataInput.readByte() == 1;
  }

  @Override
  public int hashCode() {
    return "DummyNode".hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof DummyNode;
  }
}
