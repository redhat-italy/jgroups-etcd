package org.jgroups.etcd.raft.command;

public class PUT implements RaftCommand {

  @Override
  public void execute() {
    System.out.println("PUTTTTT");
  }
}
