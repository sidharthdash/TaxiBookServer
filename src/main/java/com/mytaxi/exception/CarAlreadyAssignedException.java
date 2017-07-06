package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sidharth on 7/4/17.
 */


@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "You have already assigned a car.Please unregister the existing car before registering a new car")
public class CarAlreadyAssignedException extends Exception {
    public CarAlreadyAssignedException(String message) {
        super(message);
    }
}
