package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Sidharth on 7/4/17.
 */
public interface CarRepository   extends CrudRepository<CarDO, Long> {

    @Query( value="SELECT * from Car car where car.available=:available AND car.deleted=FALSE ",nativeQuery=true)
    ArrayList<CarDO> findAllByAvailableCars(@Param("available")boolean available);


    @Query( value="SELECT * from Car car WHERE car.license_plate=:license_plate",nativeQuery=true)
    CarDO findCarByLicence(@Param("license_plate")String license_plate);

    @Modifying(clearAutomatically = true)
    @Query( value="UPDATE Car car set car.deleted=true  where car.license_plate=:licensePlate",nativeQuery=true)
    int deleteCarByLicence(@Param("licensePlate")String licensePlate);

    @Modifying(clearAutomatically = true)
    @Query( value="UPDATE Car car set car.driver_id=:driverId,car.available=false  where car.license_plate=:licensePlate",nativeQuery=true)
    int registerDriver(@Param("licensePlate")String licensePlate,@Param("driverId")long driverId);

    @Modifying(clearAutomatically = true)
    @Query( value="UPDATE Car car set car.available=TRUE  where car.available=FALSE AND car.license_plate=:licensePlate AND car.driver_id=:driverId",nativeQuery=true)
    int unRegisterDriver(@Param("licensePlate")String licensePlate,@Param("driverId")long driverId);

    @Query( value="SELECT *  from Car car where car.driver_id=:driverId and car.available=false",nativeQuery=true)
    CarDO findCarAssignedToDriverId(@Param("driverId")long driverId);

    @Modifying(clearAutomatically = true)
    @Query( value="UPDATE Car car set car.deleted=FALSE  where car.license_plate=:licensePlate ",nativeQuery=true)
    void restoreCar(@Param("licensePlate")String licensePlate);




}
