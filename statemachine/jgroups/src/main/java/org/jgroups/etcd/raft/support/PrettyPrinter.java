package org.jgroups.etcd.raft.support;

public class PrettyPrinter {

  public static String printByteArray(byte[] buff) {
    if (buff == null || buff.length == 0) {
      return "";
    }
    StringBuilder builder = new StringBuilder();
    builder.append(Byte.toString(buff[0]));
    for (int i = 1; i < buff.length; i++) {
      builder.append(" ");
      builder.append(Byte.toString(buff[i]));
    }
    return builder.toString();
  }
}
