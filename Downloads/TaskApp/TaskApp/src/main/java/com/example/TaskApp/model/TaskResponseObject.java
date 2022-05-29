package com.example.TaskApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;


public class TaskResponseObject<T> {
  private T responseObject;
  private TaskStatusCode status;

  @JsonIgnore
  private HttpStatus statusCode;

  public TaskResponseObject(T responseObject, TaskStatusCode status, HttpStatus statusCode) {
    this.responseObject = responseObject;
    this.status = status;
    this.statusCode = statusCode;
  }

  public TaskResponseObject(T responseObject) {
    this.responseObject = responseObject;
  }

  public TaskResponseObject(T responseObject, TaskStatusCode status) {
    this.responseObject = responseObject;
    this.status = status;
  }

  public TaskResponseObject() {
  }

  public T getResponseObject() {
    return responseObject;
  }

  public void setResponseObject(T responseObject) {
    this.responseObject = responseObject;
  }

  public TaskStatusCode getStatus() {
    return status;
  }

  public void setStatus(TaskStatusCode status) {
    this.status = status;
  }

  public HttpStatus getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(HttpStatus statusCode) {
    this.statusCode = statusCode;
  }
}