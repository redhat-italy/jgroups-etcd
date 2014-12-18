package org.jgroups.etcd.api;

public interface Etcd {

  Response get(String key);

  Response put(String key, String value);

  Response delete(String key);
}
