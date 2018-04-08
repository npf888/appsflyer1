package com.ami.api.exception;

public class ServiceException extends Exception
{
  private static final long serialVersionUID = 1L;
  private String errorID;
  private String exceptionMessage;

  public ServiceException(Exception ex)
  {
    super(ex);
  }

  public ServiceException(String id, Exception ex)
  {
    super(ex);
    this.errorID = id;
  }

  public ServiceException(String id)
  {
    this.errorID = id;
  }

  public ServiceException(String id, String exceptionMessage)
  {
    this.errorID = id;
    this.exceptionMessage = exceptionMessage;
  }

  public String getErrorID()
  {
    return this.errorID;
  }

  @Override
  public String getMessage() {
	  
	  return this.exceptionMessage;
	  
  };
  public String getExceptionMessage()
  {
    return this.exceptionMessage;
  }

  public void setExceptionMessage(String exceptionMessage)
  {
    this.exceptionMessage = exceptionMessage;
  }

  public void setErrorID(String errorID)
  {
    this.errorID = errorID;
  }
}