package org.jgroups.etcd.command;

import org.jgroups.etcd.raft.api.EtcdStateMachine;
import org.jgroups.util.Util;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

public class TestPUT {

  @Test
  public void testPutValue() {
    EtcdStateMachine stateMachine = mock(EtcdStateMachine.class);

    String key = "key";
    String value = "value";

    PUT put = new PUT(key, value);
    put.applyOn(stateMachine);

    verify(stateMachine, only()).put(eq(key), eq(value));
  }

  @Test
  public void testSerialization() throws Exception {
    String key = "key";
    String value = "value";

    PUT expected = new PUT(key, value);
    byte[] buffer = Util.objectToByteBuffer(expected);
    Object command = Util.objectFromByteBuffer(buffer);
    Assert.assertEquals(expected, command);
  }
}
