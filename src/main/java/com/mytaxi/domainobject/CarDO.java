package com.mytaxi.domainobject;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Created by Sidharth on 7/4/17.
 */
@Entity
@Table(name = "car")
public class CarDO {
    @Id
    @GeneratedValue
    private Long carId;


    @Column(name = "license_plate",nullable = false,unique=true)
    private String licensePlate;

    @Column(name = "driver_id",nullable = false,unique=true)
    private long driverId;

    @Column(name = "date_created",nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(name="deleted",nullable = false)
    private boolean deleted=false;

    @Column(name="available",nullable = false)
    private boolean available=true;

    @Column(name="seat_count")
    private int seatCount;

    @Column(name = "convertible")
    private boolean convertible;

    @Column(name = "rating")
    private int rating;

    @Column(name="engine_type")
    private String engineType;

    public CarDO(String licensePlate, long driverId, boolean deleted,
                 boolean available, int seatCount, boolean convertible, int rating, String engineType) {
        this.licensePlate = licensePlate;
        this.driverId = driverId;
        this.deleted = deleted;
        this.available = available;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
    }

    public CarDO(){

    }

    public Long getCarId() {
        return carId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public long getDriverId() {
        return driverId;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public boolean isConvertible() {
        return convertible;
    }

    public int getRating() {
        return rating;
    }

    public String getEngineType() {
        return engineType;
    }
}
