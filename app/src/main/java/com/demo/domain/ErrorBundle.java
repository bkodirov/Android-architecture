package com.demo.domain;

public interface ErrorBundle {
  Exception getException();

  String getErrorMessage();
}
