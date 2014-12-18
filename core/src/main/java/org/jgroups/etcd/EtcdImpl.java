package org.jgroups.etcd;

import org.jgroups.etcd.api.Etcd;
import org.jgroups.etcd.api.Response;
import org.jgroups.etcd.domain.ResponseBuilder;
import org.jgroups.etcd.api.raft.EtcdStateMachine;
import org.jgroups.etcd.support.KeyNotFoundException;

import java.util.Optional;

public class EtcdImpl implements Etcd {

  private final EtcdStateMachine stateMachine;

  public EtcdImpl(EtcdStateMachine stateMachine) {
    this.stateMachine = stateMachine;
  }

  @Override
  public Response get(String key) {
    return ResponseBuilder.actionGet(Optional
        .ofNullable(stateMachine.get(key))
        .orElseThrow(() -> new KeyNotFoundException(key, 100L, "Key not found", -1L)));
  }

  @Override
  public Response put(String key, String value) {
    return ResponseBuilder.actionPut(stateMachine.put(key, value));
  }

  @Override
  public Response delete(String key) {
    return ResponseBuilder.actionDelete(stateMachine.delete(key));
  }

}
