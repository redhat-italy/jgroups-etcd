package org.jgroups.etcd;

import org.jgroups.etcd.api.Node;
import org.jgroups.etcd.raft.api.EtcdStateMachine;
import org.jgroups.etcd.command.RaftCommand;
import org.jgroups.etcd.domain.EtcdNode;
import org.jgroups.etcd.domain.EtcdNodeValue;
import org.jgroups.protocols.raft.StateMachine;
import org.jgroups.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class EtcdStateMachineImpl implements EtcdStateMachine, StateMachine {

  private final static Logger log = LoggerFactory.getLogger(EtcdStateMachineImpl.class);

  private AtomicLong currIndex = new AtomicLong(0);
  private ConcurrentMap<String, EtcdNodeValue> values = new ConcurrentHashMap<>();

  public EtcdStateMachineImpl() {
  }

  @Override
  public Node get(String key) {
    EtcdNodeValue value = values.getOrDefault(key, EtcdNodeValue.empty);
    return new EtcdNode(key, value);
  }

  @Override
  public Node put(String key, String value) {
    Long index = currIndex.incrementAndGet();
    EtcdNodeValue newValue = values.compute(key, (k, o) -> {
      if (o == null) {
        return new EtcdNodeValue(index, index, value);
      } else {
        return o.modifyValue(index, value);
      }
    });
    return new EtcdNode(key, newValue);
  }

  @Override
  public Node delete(String key) {
    return new EtcdNode(key, values.remove(key));
  }

  @Override
  public byte[] apply(byte[] data, int offset, int length) throws Exception {
    Object object = Util.objectFromByteBuffer(data, offset, length);
    if(object==null) {
      throw new IllegalArgumentException("Input parameter cannot be a null object");
    }
    if(!RaftCommand.class.isAssignableFrom(object.getClass())) {
      throw new IllegalArgumentException("Input parameter has to be an instance of RaftCommand class");
    }

    RaftCommand command = (RaftCommand) object;
    Node node = command.applyOn(this);
    return Util.objectToByteBuffer(node);
  }

  @Override
  public void readContentFrom(InputStream in) {
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  @Override
  public void writeContentTo(OutputStream out) {
    throw new UnsupportedOperationException("Not yet implemented.");
  }

}
