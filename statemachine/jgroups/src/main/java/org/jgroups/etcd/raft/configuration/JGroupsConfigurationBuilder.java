package org.jgroups.etcd.raft.configuration;

import org.jgroups.JChannel;
import org.jgroups.etcd.raft.EtcdStateMachineImpl;
import org.jgroups.etcd.raft.api.EtcdStateMachine;
import org.jgroups.protocols.raft.RAFT;

public class JGroupsConfigurationBuilder {

  private JChannel ch;
  private RAFT raft;
  private EtcdStateMachineImpl stateMachine;

  public EtcdStateMachine build() {
    try {
      this.ch = new JChannel("raft.xml");
      if ((raft = RAFT.findProtocol(RAFT.class, ch.getProtocolStack().getTopProtocol(), true)) == null) {
        throw new IllegalStateException("RAFT protocol must be present in configuration");
      }
      stateMachine = new EtcdStateMachineImpl(raft);
      raft.stateMachine(stateMachine);

      return stateMachine;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
