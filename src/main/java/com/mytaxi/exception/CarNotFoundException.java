package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sidharth on 7/5/17.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The car not found in inventory")
public class CarNotFoundException  extends Exception{
    public CarNotFoundException(String message) {
        super(message);
    }
}