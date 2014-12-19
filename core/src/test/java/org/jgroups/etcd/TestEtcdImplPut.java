package org.jgroups.etcd;

import org.jgroups.etcd.api.Node;
import org.jgroups.etcd.api.Response;
import org.jgroups.etcd.raft.api.EtcdStateMachine;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestEtcdImplPut {

  @Test
  public void testPutKey() {
    String key = "message";
    String value = "value";

    Node expected = mock(Node.class);
    EtcdStateMachine stateMachine = mock(EtcdStateMachine.class);

    when(stateMachine.put(key, value)).thenReturn(expected);

    EtcdImpl etcd = new EtcdImpl(stateMachine);

    Response response = etcd.put(key, value);

    Assert.assertNotNull(response);
    Assert.assertTrue("put".equalsIgnoreCase(response.getAction()));
    Assert.assertSame(expected, response.getNode());
  }
}
