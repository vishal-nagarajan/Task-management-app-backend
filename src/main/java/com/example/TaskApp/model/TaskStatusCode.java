package com.example.TaskApp.model;

public enum TaskStatusCode {
  SUCCESS(1000,"SUCCESS"),
  PROJECT_ALREADY_EXISTS(1001,"PROJECT_ALREADY_EXISTS"),
  CLIENT_ALREADY_EXISTS(1002,"CLIENT_ALREADY_EXISTS");
  private final int code;
  private final String desc;

  TaskStatusCode(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public int getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }
}
