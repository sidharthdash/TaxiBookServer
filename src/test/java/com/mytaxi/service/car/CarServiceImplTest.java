package com.mytaxi.service.car;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.CarAlreadyAssignedException;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.CarNotFoundException;
import com.mytaxi.exception.CarRemovedFromInventory;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

/**
 * Created by Sidharth on 7/6/17.
 */
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class CarServiceImplTest {

    @Autowired
    CarService carService;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    String licencePlate1 ="abcdef1";
    String licencePlate2 ="abcdef2";
    String licencePlate3 ="abcdef3";
    String licencePlate4 ="abcdef4";
    String licencePlate5 ="abcdef5";


    @Test
    public void Test1createCar() throws Exception {

        CarDO carDO=new CarDO(licencePlate1, -1, false,true,
                4,true,0, "petrol");
        CarDO returnedCarDo=carService.createCar(carDO);
        assertNotNull("The car cannot be created ; Test failed",returnedCarDo);
        assertTrue(carDO.getLicensePlate().equals(returnedCarDo.getLicensePlate()));

    }

    @Test
    public void Test2findCar() throws Exception {
        CarDO returnedCarDo=carService.findCar(1);
        assertNotNull("The car cannot be found ; Find Car Test failed",returnedCarDo);
        assertTrue(licencePlate1.equals(returnedCarDo.getLicensePlate()));

    }

    @Test
    public void Test3findAllAvailableCars() throws Exception {
        carService.createCar(new CarDO(licencePlate2, -1, false,true,
                4,true,0, "petrol"));
        carService.createCar(new CarDO(licencePlate3, -1, false,true,
                4,true,0, "petrol"));
        carService.createCar(new CarDO(licencePlate4, -1, false,true,
                4,true,0, "petrol"));
        carService.createCar(new CarDO(licencePlate5, -1, false,true,
                4,true,0, "petrol"));

        List<CarDO> carList = carService.findAllAvailableCars();
        assertNotNull("Null returned form the car list test",carList);
        assertEquals(5,carList.size());
    }

    @Test
    public void Test4bookCar1() throws Exception {
        CarDO currentCar=new CarDO(licencePlate1, -1, false,true,
                4,true,0, "petrol");
        carService.bookCar(1,currentCar);
        CarDO carDO=carService.findCar(1);
        List<CarDO> carList = carService.findAllAvailableCars();
        assertTrue(!carDO.isAvailable());
        assertEquals(1,carDO.getDriverId());
        assertEquals(4,carList.size());

    }

    @Test
    public void Test4bookCar2() throws Exception {
        CarDO currentCar=new CarDO(licencePlate1, -1, false,true,
                4,true,0, "petrol");
        exception.expect(CarAlreadyInUseException.class);
        exception.expectMessage(containsString("The car is already begin used by a driver"));
        carService.bookCar(2,currentCar);


    }

    @Test
    public void Test4bookCar3() throws Exception {
        CarDO currentCar=new CarDO(licencePlate2, -1, false,true,
                4,true,0, "petrol");
        exception.expect(CarAlreadyAssignedException.class);
        exception.expectMessage(containsString("A car has already  assigned to you"));
        carService.bookCar(1,currentCar);


    }

    @Test
    public void Test4bookCar4() throws Exception {
        CarDO currentCar=new CarDO("abcdef10", -1, false,true,
                4,true,0, "petrol");
        exception.expect(CarNotFoundException.class);
        exception.expectMessage(containsString("The car is not found in the inventory"));
        carService.bookCar(1,currentCar);


    }
    @Test
    public void Test5unBookCar1() throws Exception {
        carService.unBookCar(1,new CarDO(licencePlate1, -1, false,true,
                4,true,0, "petrol"));
        CarDO carDO=carService.findCar(1);
        List<CarDO> carList = carService.findAllAvailableCars();
        assertTrue(carDO.isAvailable());
        assertEquals(5,carList.size());
    }

    @Test
    public void Test5unBookCar2() throws Exception {
        CarDO currentCar=new CarDO(licencePlate1, -1, false,true,
                4,true,0, "petrol");
        exception.expect(Exception.class);
        exception.expectMessage(containsString("Car cannot be Un_Registered"));
        carService.unBookCar(1,currentCar);
    }

    @Test
    public void Test6deleteCar1() throws Exception {
        CarDO currentCar=new CarDO(licencePlate1, -1, false,true,
                4,true,0, "petrol");
        assertEquals(5, carService.findAllAvailableCars().size());
        CarDO returnedCar=carService.deleteCar(currentCar);
        assertTrue(returnedCar.isDeleted());
        assertEquals(4, carService.findAllAvailableCars().size());

    }

    @Test
    public void Test6deleteCar2() throws Exception {
        CarDO currentCar=new CarDO(licencePlate1, -1, false,true,
                4,true,0, "petrol");
        exception.expect(Exception.class);
        exception.expectMessage(containsString("Car Already Deleted"));
        carService.deleteCar(currentCar);

    }

    @Test
    public void Test6deleteCar3() throws Exception {
        CarDO currentCar=new CarDO("abcdefg123", -1, false,true,
                4,true,0, "petrol");
        exception.expect(CarNotFoundException.class);
        exception.expectMessage(containsString("The car is not found in the inventory"));
        carService.deleteCar(currentCar);

    }


    @Test
    public void Test7restoreCar1() throws Exception {
        assertEquals(4, carService.findAllAvailableCars().size());
        CarDO returnedCar=carService.restoreCar(licencePlate1);
        assertTrue(!returnedCar.isDeleted());
        assertEquals(5, carService.findAllAvailableCars().size());
    }

    @Test
    public void Test7restoreCar2() throws Exception {
        exception.expect(CarNotFoundException.class);
        exception.expectMessage(containsString("The car you want to restore is not found in the inventory"));
        carService.restoreCar("abcdefg123");
    }

    @Test
    public void Test7restoreCar3() throws Exception {
        exception.expect(CarAlreadyInUseException.class);
        exception.expectMessage(containsString("The car you want to restore is already in use"));
        carService.restoreCar(licencePlate1);
    }

}