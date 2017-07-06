package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sidharth on 7/4/17.
 */


@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The car is already begin used by a driver")
public class CarAlreadyInUseException  extends Exception{
    public CarAlreadyInUseException(String message) {
        super(message);
    }
}
