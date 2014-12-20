package org.jgroups.etcd.raft;

import org.jgroups.etcd.api.Node;
import org.jgroups.etcd.raft.api.EtcdStateMachine;
import org.jgroups.etcd.raft.command.GET;
import org.jgroups.protocols.raft.RAFT;
import org.jgroups.protocols.raft.StateMachine;
import org.jgroups.util.Util;

import java.io.*;

public class EtcdStateMachineImpl implements EtcdStateMachine, StateMachine {

  private RAFT raft;

  public EtcdStateMachineImpl(RAFT raft) {
    this.raft = raft;
  }

  @Override
  public Node get(String key) {
    GET get = new GET();
    try {
      byte[] buff = Util.objectToByteBuffer(get);
      byte[] apply = apply(buff, 0, buff.length);
      return new Node() {
        @Override
        public Long getCreatedIndex() {
          return 0L;
        }

        @Override
        public Long getModifiedIndex() {
          return 0L;
        }

        @Override
        public String getKey() {
          return key;
        }

        @Override
        public String getValue() {
          return "GET";
        }
      };
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
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
