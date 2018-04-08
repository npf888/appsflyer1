package com.ami.api.exception;

public class APIException extends Exception
{
  private static final long serialVersionUID = 1L;
  private String errorID;
  private String exceptionMessage;

  public APIException(Exception ex)
  {
    super(ex);
  }

  public APIException(String id, Exception ex)
  {
    super(ex);
 
    this.errorID = id;
  }

  public APIException(String id)
  {
    this.errorID = id;
  }

  public APIException(String id, String exceptionMessage)
  {
    this.errorID = id;
    this.exceptionMessage = exceptionMessage;
  }

  @Override
  public String getMessage() {
	 
	  return super.getMessage();
	  
  };
  public String getErrorID()
  {
    return this.errorID;
  }

  public String getExceptionMessage()
  {
    return this.exceptionMessage;
  }

  public void setExceptionMessage(String exceptionMessage)
  {
    this.exceptionMessage = exceptionMessage;
  }
}