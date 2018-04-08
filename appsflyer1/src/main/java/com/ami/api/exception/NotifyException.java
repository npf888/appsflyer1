package com.ami.api.exception;

public class NotifyException extends Exception
{
  private static final long serialVersionUID = 819168275L;
  private String errorID;
  private String exceptionMessage;

  public NotifyException(Exception ex)
  {
    super(ex);
  }

  public NotifyException(String id, Exception ex)
  {
    super(ex);
    this.errorID = id;
  }

  public NotifyException(String id)
  {
    this.errorID = id;
  }

  public NotifyException(String id, String exceptionMessage)
  {
    this.errorID = id;
    this.exceptionMessage = exceptionMessage;
  }

  public void setExceptionMessage(String exceptionMessage)
  {
    this.exceptionMessage = exceptionMessage;
  }

  public String getErrorID()
  {
    return this.errorID;
  }

  public String getExceptionMessage()
  {
    return this.exceptionMessage;
  }
}