package org.jgroups.etcd.raft.command;

public interface RaftCommand {

  void execute();

}
