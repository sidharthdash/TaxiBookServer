package com.mytaxi.service.car;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.*;

import java.util.ArrayList;

/**
 * Created by Sidharth on 7/4/17.
 */
public interface CarService {

    CarDO findCar(int id);
    ArrayList<CarDO> findAllAvailableCars();
    CarDO bookCar(long driverId,CarDO carDO) throws CarRemovedFromInventory, CarAlreadyInUseException, CarAlreadyAssignedException, CarNotFoundException;
    CarDO unBookCar(long driverId,CarDO carDO) throws Exception;
    CarDO deleteCar(CarDO carDO) throws Exception;
    CarDO createCar(CarDO carDO) throws ConstraintsViolationException;
    CarDO restoreCar(String licencePlate) throws CarNotFoundException, CarAlreadyInUseException;
}
