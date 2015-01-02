package org.jgroups.etcd.client;

import org.jgroups.etcd.api.Node;
import org.jgroups.etcd.command.DELETE;
import org.jgroups.etcd.command.PUT;
import org.jgroups.etcd.raft.api.EtcdStateMachine;
import org.jgroups.etcd.raft.api.EtcdStateMachineClient;
import org.jgroups.etcd.raft.support.PrettyPrinter;
import org.jgroups.protocols.raft.Settable;
import org.jgroups.util.Streamable;
import org.jgroups.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EtcdStateMachineClientImpl implements EtcdStateMachineClient {

  private final static Logger log = LoggerFactory.getLogger(EtcdStateMachineClientImpl.class);
  private Settable raft;
  private EtcdStateMachine stateMachine;

  public EtcdStateMachineClientImpl(Settable raft, EtcdStateMachine stateMachine) {
    this.raft = raft;
    this.stateMachine = stateMachine;
  }

  @Override
  public Node get(String key) {
    return stateMachine.get(key);
  }

  @Override
  public Node put(String key, String value) {
    return execute(new PUT(key, value));
  }

  @Override
  public Node delete(String key) {
    return execute(new DELETE(key));
  }

  private Node execute(Streamable command) {
    try {
      byte[] buff = Util.objectToByteBuffer(command);
      if(log.isTraceEnabled()) {
        log.trace(String.format("Object to buffer [%s]", PrettyPrinter.printByteArray(buff)));
      }
      byte[] responseBuffer = raft.set(buff, 0, buff.length);
      if(log.isTraceEnabled()) {
        log.trace(String.format("Response buffer [%s]", PrettyPrinter.printByteArray(buff)));
      }
      return (Node) Util.objectFromByteBuffer(responseBuffer);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
