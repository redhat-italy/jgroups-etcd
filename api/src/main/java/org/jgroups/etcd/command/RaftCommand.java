package org.jgroups.etcd.command;

import org.jgroups.etcd.api.Node;
import org.jgroups.etcd.raft.api.EtcdStateMachine;

public interface RaftCommand {

  Node applyOn(EtcdStateMachine stateMachine);
}
