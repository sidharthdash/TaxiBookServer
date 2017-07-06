package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "licensePlate",
        "available",
        "seatCount",
        "convertible",
        "engineType"
})
public class CarDTO {

    @JsonProperty("licensePlate")
    private String licensePlate;
    @JsonProperty("available")
    private Boolean available;
    @JsonProperty("seatCount")
    private Object seatCount;
    @JsonProperty("convertible")
    private Boolean convertible;
    @JsonProperty("engineType")
    private String engineType;

    @JsonProperty("licensePlate")
    public String getLicensePlate() {
        return licensePlate;
    }

    @JsonProperty("licensePlate")
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @JsonProperty("available")
    public Boolean getAvailable() {
        return available;
    }

    @JsonProperty("available")
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @JsonProperty("seatCount")
    public Object getSeatCount() {
        return seatCount;
    }

    @JsonProperty("seatCount")
    public void setSeatCount(Object seatCount) {
        this.seatCount = seatCount;
    }

    @JsonProperty("convertible")
    public Boolean getConvertible() {
        return convertible;
    }

    @JsonProperty("convertible")
    public void setConvertible(Boolean convertible) {
        this.convertible = convertible;
    }

    @JsonProperty("engineType")
    public String getEngineType() {
        return engineType;
    }

    @JsonProperty("engineType")
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toStringExclude(this, new String[] {""});
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(licensePlate).append(available).append(seatCount).append(convertible).append(engineType).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CarDTO) == false) {
            return false;
        }
        CarDTO rhs = ((CarDTO) other);
        return new EqualsBuilder().append(licensePlate, rhs.licensePlate).append(available, rhs.available).append(seatCount, rhs.seatCount).append(convertible, rhs.convertible).append(engineType, rhs.engineType).isEquals();
    }

    private CarDTO(){

    }

    private CarDTO(String licensePlate, boolean available, int seatCount, boolean convertible, String engineType) {
        this.licensePlate = licensePlate;
        this.available = available;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.engineType = engineType;
    }
    public static CarDTO.CarDTOBuilder newBuilder()
    {
        return new CarDTO.CarDTOBuilder();
    }

        public static class CarDTOBuilder {
        private String licensePlate ;
        private boolean deleted ;
        private boolean available ;
        private int seatCount ;
        private boolean convertible;
        private String engineType;


        public CarDTO.CarDTOBuilder setLicensePlate(String licensePlate)
        {
            this.licensePlate = licensePlate;
            return this;
        }

            public CarDTO.CarDTOBuilder setAvailable(boolean available) {
                this.available = available;
                return this;
            }
        public CarDTO.CarDTOBuilder setSeatCount(int seatCount)
        {
            this.seatCount = seatCount;
            return this;
        }

        public CarDTO.CarDTOBuilder setConvertable(boolean convertable) {
            this.convertible = convertable;
            return this;
        }

        public CarDTO.CarDTOBuilder setEngineType(String engineType) {
            this.engineType = engineType;
            return this;
        }


        public CarDTO createCarDTO()
        {
            return new CarDTO(licensePlate,available,seatCount,convertible,engineType);
        }

    }

}








//public class CarDTO {
//
//    String licensePlate ;
//    boolean deleted ;
//    boolean available ;
//    int seatCount ;
//    boolean convertible;
//    String engineType;
//
//    private CarDTO(){
//
//    }
//
//    public CarDTO(String licensePlate, boolean deleted, boolean available, int seatCount, boolean convertible, String engineType) {
//        this.licensePlate = licensePlate;
//        this.deleted = deleted;
//        this.available = available;
//        this.seatCount = seatCount;
//        this.convertible = convertible;
//        this.engineType = engineType;
//    }
//    public static CarDTO.CarDTOBuilder newBuilder()
//    {
//        return new CarDTO.CarDTOBuilder();
//    }
//
//
//    public static class CarDTOBuilder {
//        private String licensePlate ;
//        private boolean deleted ;
//        private boolean available ;
//        private int seatCount ;
//        private boolean convertible;
//        private String engineType;
//
//
//        public CarDTO.CarDTOBuilder setLicensePlate(String licensePlate)
//        {
//            this.licensePlate = licensePlate;
//            return this;
//        }
//
//
//
//
//        public CarDTO.CarDTOBuilder setSeatCount(int seatCount)
//        {
//            this.seatCount = seatCount;
//            return this;
//        }
//
//        public CarDTO.CarDTOBuilder setConvertable(boolean convertable) {
//            this.convertible = convertable;
//            return this;
//        }
//
//        public CarDTO.CarDTOBuilder setEngineType(String engineType) {
//            this.engineType = engineType;
//            return this;
//        }
//
//
//        public CarDTO createCarDTO()
//        {
//            return new CarDTO(licensePlate,false,true,seatCount,convertible,engineType);
//        }
//
//    }
//}
