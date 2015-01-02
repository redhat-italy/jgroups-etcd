package org.jgroups.etcd.raft.api;

import org.jgroups.etcd.api.Node;

public interface EtcdStateMachineClient {

  Node get(String key);

  Node put(String key, String value);

  Node delete(String key);
}
