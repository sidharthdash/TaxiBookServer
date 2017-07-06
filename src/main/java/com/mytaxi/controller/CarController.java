package com.mytaxi.controller;



import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.*;
import com.mytaxi.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * All operations with a car will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
public class CarController {


    @Autowired
    CarService carService;

    @Autowired
    CarMapper carMapper;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCars(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException {
        CarDO carDO=(CarDO)carMapper.dtoToDO(carDTO);
        return (CarDTO) carMapper.doToDTO(carService.createCar(carDO));
    }

    @PutMapping("/book/{driverId}")
    @ResponseStatus(HttpStatus.OK)
    public CarDTO bookCar(@Valid @RequestBody CarDTO carDTO,@Valid @PathVariable long driverId )
            throws ConstraintsViolationException, CarRemovedFromInventory,
            CarAlreadyAssignedException, CarAlreadyInUseException, CarNotFoundException {
        CarDO carDO=(CarDO)carMapper.dtoToDO(carDTO);
        return (CarDTO) carMapper.doToDTO(carService.bookCar(driverId,carDO));
    }


    @PutMapping("/unbook/{driverId}")
    @ResponseStatus(HttpStatus.OK)
    public CarDTO unBookCar(@Valid @RequestBody CarDTO carDTO,@Valid @PathVariable long driverId )
            throws Exception {
        CarDO carDO=(CarDO)carMapper.dtoToDO(carDTO);
        return (CarDTO) carMapper.doToDTO(carService.unBookCar(driverId,carDO));
    }




    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{carId}")
    public CarDTO getCarById(@Valid @PathVariable int carId){
       return (CarDTO)carMapper.doToDTO(carService.findCar(carId));
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/available/car")
    public List<CarDTO> getAllAvailableCar(){
        return  carMapper.doToDTOList(carService.findAllAvailableCars());
    }



    @PutMapping("/reStore/{licenceNumber}")
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO restoreCars(@Valid @PathVariable String licenceNumber) throws
            ConstraintsViolationException, CarNotFoundException, CarAlreadyInUseException {
        return (CarDTO) carMapper.doToDTO(carService.restoreCar(licenceNumber));
    }


    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public CarDTO deleteCar(@Valid @RequestBody CarDTO carDTO)
            throws Exception {
        CarDO carDO=(CarDO)carMapper.dtoToDO(carDTO);
        return (CarDTO) carMapper.doToDTO(carService.deleteCar(carDO));
    }


}
