package org.jgroups.etcd;

import org.jgroups.etcd.api.Node;
import org.junit.Assert;
import org.junit.Test;

public class TestEtcdStateMachineImpl {

  @Test
  public void testGetKey() {
    EtcdStateMachineImpl stateMachine = new EtcdStateMachineImpl();

    String key = "key";
    Node node = stateMachine.get(key);

    Assert.assertNotNull(node);
    Assert.assertEquals(key, node.getKey());
    Assert.assertEquals(new Long(-1L), node.getCreatedIndex());
    Assert.assertEquals(new Long(-1L), node.getModifiedIndex());
    Assert.assertEquals("", node.getValue());
  }

  @Test
  public void testPutKeyValue() {
    EtcdStateMachineImpl stateMachine = new EtcdStateMachineImpl();

    String key = "key";
    String value = "value";
    Node node = stateMachine.put(key, value);

    Assert.assertNotNull(node);
    Assert.assertEquals(key, node.getKey());
    Assert.assertEquals(new Long(1L), node.getCreatedIndex());
    Assert.assertEquals(new Long(1L), node.getModifiedIndex());
    Assert.assertEquals(value, node.getValue());
  }

  @Test
  public void testComplexOperation() {
    EtcdStateMachineImpl stateMachine = new EtcdStateMachineImpl();

    String key = "key";
    Node node = stateMachine.get(key);

    Assert.assertNotNull(node);
    Assert.assertEquals(key, node.getKey());
    Assert.assertEquals(new Long(-1L), node.getCreatedIndex());
    Assert.assertEquals(new Long(-1L), node.getModifiedIndex());
    Assert.assertEquals("", node.getValue());

    String value = "value";
    node = stateMachine.put(key, value);

    Assert.assertNotNull(node);
    Assert.assertEquals(key, node.getKey());
    Assert.assertEquals(new Long(1L), node.getCreatedIndex());
    Assert.assertEquals(new Long(1L), node.getModifiedIndex());
    Assert.assertEquals(value, node.getValue());

    node = stateMachine.get(key);

    Assert.assertNotNull(node);
    Assert.assertEquals(key, node.getKey());
    Assert.assertEquals(new Long(1L), node.getCreatedIndex());
    Assert.assertEquals(new Long(1L), node.getModifiedIndex());
    Assert.assertEquals(value, node.getValue());

    String newValue = "new_value";
    node = stateMachine.put(key, newValue);

    Assert.assertNotNull(node);
    Assert.assertEquals(key, node.getKey());
    Assert.assertEquals(new Long(1L), node.getCreatedIndex());
    Assert.assertEquals(new Long(2L), node.getModifiedIndex());
    Assert.assertEquals(newValue, node.getValue());

    node = stateMachine.delete(key);

    Assert.assertNotNull(node);
    Assert.assertEquals(key, node.getKey());
    Assert.assertEquals(new Long(1L), node.getCreatedIndex());
    Assert.assertEquals(new Long(2L), node.getModifiedIndex());
    Assert.assertEquals(newValue, node.getValue());

    node = stateMachine.get(key);

    Assert.assertNotNull(node);
    Assert.assertEquals(key, node.getKey());
    Assert.assertEquals(new Long(-1L), node.getCreatedIndex());
    Assert.assertEquals(new Long(-1L), node.getModifiedIndex());
    Assert.assertEquals("", node.getValue());
  }
}
