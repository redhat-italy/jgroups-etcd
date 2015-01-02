package org.jgroups.etcd.command;

import org.jgroups.etcd.api.Node;
import org.jgroups.etcd.raft.api.EtcdStateMachine;
import org.jgroups.util.Streamable;

import java.io.DataInput;
import java.io.DataOutput;

public class DELETE implements RaftCommand, Streamable {

  private String key;

  public DELETE() {
  }

  public DELETE(String key) {
    this.key = key;
  }

  @Override
  public Node applyOn(EtcdStateMachine stateMachine) {
    return stateMachine.delete(key);
  }

  @Override
  public void writeTo(DataOutput output) throws Exception {
    output.writeUTF(key);
  }

  @Override
  public void readFrom(DataInput input) throws Exception {
    key = input.readUTF();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DELETE)) return false;

    DELETE delete = (DELETE) o;

    if (key != null ? !key.equals(delete.key) : delete.key != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return key != null ? key.hashCode() : 0;
  }
}
