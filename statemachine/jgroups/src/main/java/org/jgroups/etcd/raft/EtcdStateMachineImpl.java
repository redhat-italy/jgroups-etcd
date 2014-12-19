package org.jgroups.etcd.raft;

import org.jgroups.etcd.api.Node;
import org.jgroups.etcd.raft.api.EtcdStateMachine;
import org.jgroups.protocols.raft.RAFT;
import org.jgroups.protocols.raft.StateMachine;

import java.io.InputStream;
import java.io.OutputStream;

public class EtcdStateMachineImpl implements EtcdStateMachine, StateMachine {

  private RAFT raft;

  public EtcdStateMachineImpl(RAFT raft) {
    this.raft = raft;
  }

  @Override
  public Node get(String key) {
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  @Override
  public Node put(String key, String value) {
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  @Override
  public Node delete(String key) {
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  @Override
  public byte[] apply(byte[] data, int offset, int length) throws Exception {
    return new byte[0];
  }

  @Override
  public void readContentFrom(InputStream in) {
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  @Override
  public void writeContentTo(OutputStream out) {
    throw new UnsupportedOperationException("Not yet implemented.");
  }
}
