package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sidharth on 7/4/17.
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The car is is removed from inventory; Inconvenience is regretted")
public class CarRemovedFromInventory extends Exception {
    public CarRemovedFromInventory(String message) {
        super(message);
    }
}
