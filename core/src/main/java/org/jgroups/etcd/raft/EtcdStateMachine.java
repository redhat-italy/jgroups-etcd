package org.jgroups.etcd.raft;

import org.jgroups.etcd.api.Node;
import org.jgroups.protocols.raft.StateMachine;

public interface EtcdStateMachine extends StateMachine {

  Node get(String key);

  Node put(String key, String value);

  Node delete(String key);
}
