package org.jgroups.etcd;

import org.jgroups.etcd.api.Node;
import org.jgroups.etcd.api.Response;
import org.jgroups.etcd.raft.api.EtcdStateMachine;
import org.jgroups.etcd.raft.api.EtcdStateMachineClient;
import org.jgroups.etcd.support.KeyNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestEtcdImplDelete {


  @Test(expected = KeyNotFoundException.class)
  public void testGetKeyWithKeyNotFound() {
    String key = "message";

    EtcdImpl etcd = new EtcdImpl(mock(EtcdStateMachineClient.class));

    Response message = etcd.delete(key);
  }

  @Test
  public void testDeleteKey() {
    String key = "message";

    Node expected = mock(Node.class);
    EtcdStateMachineClient stateMachine = mock(EtcdStateMachineClient.class);

    when(stateMachine.delete(key)).thenReturn(expected);

    EtcdImpl etcd = new EtcdImpl(stateMachine);

    Response response = etcd.delete(key);

    Assert.assertNotNull(response);
    Assert.assertTrue("delete".equalsIgnoreCase(response.getAction()));
    Assert.assertSame(expected, response.getNode());
  }
}
