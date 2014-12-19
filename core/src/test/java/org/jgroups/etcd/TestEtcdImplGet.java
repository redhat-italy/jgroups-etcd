package org.jgroups.etcd;

import org.jgroups.etcd.api.Node;
import org.jgroups.etcd.api.Response;
import org.jgroups.etcd.raft.api.EtcdStateMachine;
import org.jgroups.etcd.support.KeyNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestEtcdImplGet {

  @Test(expected = KeyNotFoundException.class)
  public void testGetKeyWithKeyNotFound() {
    EtcdImpl etcd = new EtcdImpl(mock(EtcdStateMachine.class));

    Response message = etcd.get("message");
  }

  @Test
  public void testGetKeyWithKeyNotFoundCheckMessage() {
    Long errorCode = 100L;
    String key = "message";

    EtcdImpl etcd = new EtcdImpl(mock(EtcdStateMachine.class));

    try {
      etcd.get(key);
      Assert.fail("Should have thrown a key not found exception");
    } catch (KeyNotFoundException knf) {
      Assert.assertEquals(errorCode, knf.getErrorCode());
      Assert.assertSame(key, knf.getErrorCause());
    }
  }

  @Test
  public void testGetKey() {
    String key = "message";
    Node expected = mock(Node.class);

    EtcdStateMachine stateMachine = mock(EtcdStateMachine.class);
    when(stateMachine.get(key)).thenReturn(expected);

    EtcdImpl etcd = new EtcdImpl(stateMachine);

    Response response = etcd.get(key);

    Assert.assertNotNull(response);
    Assert.assertTrue("GET".equalsIgnoreCase(response.getAction()));
    Assert.assertSame(expected, response.getNode());
  }
}
