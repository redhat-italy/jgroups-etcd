package org.jgroups.etcd.client;

import org.jgroups.etcd.DummyNode;
import org.jgroups.etcd.api.Node;
import org.jgroups.etcd.raft.api.EtcdStateMachine;
import org.jgroups.protocols.raft.Settable;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TestEtcdStateMachineClientImpl {

  @Test
  public void testExecuteGet() {
    String key = "KEY_GET";

    Settable raft = mock(Settable.class);
    EtcdStateMachine stateMachine = mock(EtcdStateMachine.class);
    EtcdStateMachineClientImpl machine = new EtcdStateMachineClientImpl(raft, stateMachine);

    try {
      DummyNode expected = new DummyNode();

      when(stateMachine.get(anyString())).thenReturn(expected);

      Node node = machine.get(key);

      verify(stateMachine, only()).get(eq(key));
      verifyZeroInteractions(raft);

      Assert.assertEquals(expected, node);
    } catch (Exception e) {
      // cannot happen, since the RAFT protocol is a mock
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void testExecutePut() {
    String key = "KEY_PUT";
    String value = "VALUE_PUT";

    Settable raft = mock(Settable.class);
    EtcdStateMachine stateMachine = mock(EtcdStateMachine.class);
    EtcdStateMachineClientImpl machine = new EtcdStateMachineClientImpl(raft, stateMachine);

    try {
      DummyNode expected = new DummyNode();

      when(stateMachine.put(anyString(), anyString())).thenReturn(expected);

      Node node = machine.put(key, value);

      verify(stateMachine, only()).put(eq(key), eq(value));
      verify(raft, only()).set(anyObject(), eq(0), anyInt());

      Assert.assertEquals(expected, node);
    } catch (Exception e) {
      // cannot happen, since the RAFT protocol is a mock
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void testExecuteDelete() {
    String key = "KEY_PUT";

    Settable raft = mock(Settable.class);
    EtcdStateMachine stateMachine = mock(EtcdStateMachine.class);
    EtcdStateMachineClientImpl machine = new EtcdStateMachineClientImpl(raft, stateMachine);

    try {
      DummyNode expected = new DummyNode();

      when(stateMachine.delete(anyString())).thenReturn(expected);

      Node node = machine.delete(key);

      verify(stateMachine, only()).delete(eq(key));
      verify(raft, only()).set(anyObject(), eq(0), anyInt());

      Assert.assertEquals(expected, node);
    } catch (Exception e) {
      // cannot happen, since the RAFT protocol is a mock
      Assert.fail(e.getMessage());
    }
  }

}
