package com.ami.api.exception;

import org.springframework.dao.DataAccessException;



public class DaoException extends DataAccessException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DaoException(String message) {
        super(message);
    }

    public DaoException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
