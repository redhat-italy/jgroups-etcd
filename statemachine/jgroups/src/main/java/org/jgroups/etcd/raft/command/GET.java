package org.jgroups.etcd.raft.command;

import org.jgroups.util.Streamable;

import java.io.*;

public class GET implements RaftCommand, Streamable {

  @Override
  public void execute() {
    System.out.println("GETTTT");
  }

  @Override
  public void writeTo(DataOutput dataOutput) throws Exception {
    dataOutput.writeByte(1);
  }

  @Override
  public void readFrom(DataInput dataInput) throws Exception {
    int read = dataInput.readByte();
    System.out.println("Ho letto : " + read);
  }
}
