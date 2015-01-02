package org.jgroups.etcd.raft.support;

import org.junit.Assert;
import org.junit.Test;

public class TestPrettyPrinter {

  @Test
  public void printBytes() {
    byte[] buffer = {10,21,32,43,54};
    String expected = "10 21 32 43 54";

    String value = PrettyPrinter.printByteArray(buffer);

    Assert.assertEquals(expected, value);
  }

  @Test
  public void printEmptyArray() {
    byte[] buffer = {};
    String expected = "";

    String value = PrettyPrinter.printByteArray(buffer);

    Assert.assertEquals(expected, value);
  }

  @Test
  public void printNullArray() {
    byte[] buffer = null;
    String expected = "";

    String value = PrettyPrinter.printByteArray(buffer);

    Assert.assertEquals(expected, value);
  }
}
