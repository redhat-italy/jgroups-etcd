package org.jgroups.etcd.command;

import org.jgroups.etcd.raft.api.EtcdStateMachine;
import org.jgroups.util.Util;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class TestDELETE {

  @Test
  public void testPutValue() {
    EtcdStateMachine stateMachine = mock(EtcdStateMachine.class);

    String key = "key";

    DELETE delete = new DELETE(key);
    delete.applyOn(stateMachine);

    verify(stateMachine, only()).delete(eq(key));
  }

  @Test
  public void testSerialization() throws Exception {
    String key = "key";

    DELETE expected = new DELETE(key);
    byte[] buffer = Util.objectToByteBuffer(expected);
    Object command = Util.objectFromByteBuffer(buffer);
    Assert.assertEquals(expected, command);
  }

}
