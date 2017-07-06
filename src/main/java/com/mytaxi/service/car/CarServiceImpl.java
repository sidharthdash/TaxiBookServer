package com.mytaxi.service.car;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by Sidharth on 7/4/17.
 */

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepository carRepository;

    @Override
    public CarDO findCar(int id) {
        return carRepository.findOne((long) id);
    }

    @Override
    public ArrayList<CarDO> findAllAvailableCars() {
        return carRepository.findAllByAvailableCars(true);
    }


    @Transactional
    @Override
    public CarDO bookCar(long driverId, CarDO carDO) throws CarRemovedFromInventory, CarAlreadyInUseException,
            CarAlreadyAssignedException, CarNotFoundException {

        CarDO searchedCar= carRepository.findCarByLicence(carDO.getLicensePlate());
        if(searchedCar!=null) {

            if (searchedCar.isDeleted()) {
                throw new CarRemovedFromInventory("The car is removed from Inventory");
            }
            if (!searchedCar.isAvailable()) {
                throw new CarAlreadyInUseException("The car is already begin used by a driver");
            }
            if(null!=carRepository.findCarAssignedToDriverId(driverId)){
                throw  new CarAlreadyAssignedException("A car has already  assigned to you");
            }
            carRepository.registerDriver(carDO.getLicensePlate(), driverId);
            return carRepository.findCarByLicence(carDO.getLicensePlate());
        }
        else {
            throw  new CarNotFoundException("The car is not found in the inventory");
        }
    }

    @Transactional
    @Override
    public CarDO unBookCar(long driverId, CarDO carDO) throws Exception {
        int unregistered =carRepository.unRegisterDriver(carDO.getLicensePlate(),driverId);
        if(unregistered==0){
            throw  new Exception("Car cannot be Un_Registered");
        }
        return carRepository.findCarByLicence(carDO.getLicensePlate());
    }

    @Transactional
    @Override
    public CarDO deleteCar(CarDO carDO) throws Exception {
        CarDO searchedCar= carRepository.findCarByLicence(carDO.getLicensePlate());
        if(searchedCar==null) {
            throw  new CarNotFoundException("The car is not found in the inventory");
        }
        if(searchedCar.isDeleted()){
            throw new Exception("Car Already Deleted");
        }
        carRepository.deleteCarByLicence(carDO.getLicensePlate());
        return carRepository.findCarByLicence(carDO.getLicensePlate());
    }

    @Override
    public CarDO createCar(CarDO carDO) throws ConstraintsViolationException{
        if((carRepository.findCarByLicence(carDO.getLicensePlate())!=null) &&
                carRepository.findCarByLicence(carDO.getLicensePlate()).isDeleted()!=true  ){
            throw  new ConstraintsViolationException("The car is already register with us");
        }
        carRepository.save(carDO);
        return carRepository.findCarByLicence(carDO.getLicensePlate());
    }

    public CarDO findCarByLicencePlate(String licencePlate) {
        return carRepository.findCarByLicence(licencePlate);
    }

    @Transactional
    @Override
    public CarDO restoreCar(String licencePlate) throws CarNotFoundException, CarAlreadyInUseException {
        CarDO carDO = carRepository.findCarByLicence(licencePlate);
        if(carDO==null){
            throw new CarNotFoundException("The car you want to restore is not found in the inventory");
        }
        if(carDO.isDeleted()==false){
            throw new CarAlreadyInUseException("The car you want to restore is already in use");
        }
         carRepository.restoreCar(licencePlate);
        return carRepository.findCarByLicence(licencePlate);
    }


}
