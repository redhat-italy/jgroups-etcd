package org.jgroups.etcd.api;


public interface ResponseError {

  String getMessage();

  String getErrorCause();

  Long getErrorCode();

  Long getIndex();
}
