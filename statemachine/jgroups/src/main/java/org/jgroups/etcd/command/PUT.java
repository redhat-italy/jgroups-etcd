package org.jgroups.etcd.command;

import org.jgroups.etcd.api.Node;
import org.jgroups.etcd.raft.api.EtcdStateMachine;
import org.jgroups.util.Streamable;

import java.io.DataInput;
import java.io.DataOutput;

public class PUT implements RaftCommand, Streamable {

  private String key;
  private String value;

  public PUT() {
  }

  public PUT(String key, String value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public Node applyOn(EtcdStateMachine stateMachine) {
    return stateMachine.put(key, value);
  }

  @Override
  public void writeTo(DataOutput output) throws Exception {
    output.writeUTF(key);
    output.writeUTF(value);
  }

  @Override
  public void readFrom(DataInput input) throws Exception {
    key = input.readUTF();
    value = input.readUTF();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PUT)) return false;

    PUT put = (PUT) o;

    if (key != null ? !key.equals(put.key) : put.key != null) return false;
    if (value != null ? !value.equals(put.value) : put.value != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = key != null ? key.hashCode() : 0;
    result = 31 * result + (value != null ? value.hashCode() : 0);
    return result;
  }
}
