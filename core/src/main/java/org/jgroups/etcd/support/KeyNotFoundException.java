package org.jgroups.etcd.support;

import org.jgroups.etcd.api.ResponseError;

public class KeyNotFoundException extends RuntimeException implements ResponseError {

  private final String errorCause;
  private final Long errorCode;
  private final Long index;

  public KeyNotFoundException(String errorCause, Long errorCode, String message, Long index) {
    super(message);
    this.errorCause = errorCause;
    this.errorCode = errorCode;
    this.index = index;
  }

  @Override
  public String getErrorCause() {
    return errorCause;
  }

  @Override
  public Long getErrorCode() {
    return errorCode;
  }

  @Override
  public Long getIndex() {
    return index;
  }
}
