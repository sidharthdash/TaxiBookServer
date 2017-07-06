package com.mytaxi.controller.mapper;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Sidharth on 7/5/17.
 */
@Service
public class CarMapper implements Mapper{


    @Override
    public Object dtoToDO(Object dtoObject) {
        if (dtoObject==null){
            return null;
        }
        CarDTO carDTO = (CarDTO) dtoObject;
        return new CarDO(carDTO.getLicensePlate(),
                -1,false,true, (Integer) carDTO.getSeatCount(),carDTO.getConvertible(),0,carDTO.getEngineType());

    }

    @Override
    public Object doToDTO(Object doObject) {
        if(doObject==null){
            return null;
        }
        CarDO carDO = (CarDO) doObject;
        CarDTO carDTO = new CarDTO.CarDTOBuilder().setLicensePlate(carDO.getLicensePlate())
                .setConvertable(carDO.isConvertible())
                .setAvailable(carDO.isAvailable())
                .setEngineType(carDO.getEngineType())
                .setSeatCount(carDO.getSeatCount())
                .createCarDTO();

        return carDTO;
    }

    public ArrayList<CarDTO> doToDTOList(ArrayList<CarDO> carDOArrayList){
        ArrayList<CarDTO> carDTOArrayList = new ArrayList<>();
        for (CarDO carDO:carDOArrayList) {
            carDTOArrayList.add((CarDTO)doToDTO(carDO));
        }
        return carDTOArrayList;
    }
}
