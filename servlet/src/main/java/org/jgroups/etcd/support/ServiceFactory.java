package org.jgroups.etcd.support;

import java.util.ServiceLoader;

public class ServiceFactory {

  public static <T> T loadFactory(Class<T> clazz) {
    ServiceLoader<T> factories = ServiceLoader.load(clazz);
    if (factories.iterator().hasNext()) {
      return factories.iterator().next();
    } else {
      throw new IllegalStateException("Cannot find META-INF/services/" + clazz.getName() + " on classpath");
    }
  }
}
