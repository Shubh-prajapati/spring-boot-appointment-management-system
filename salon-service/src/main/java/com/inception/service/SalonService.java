package com.inception.service;

import com.inception.model.Salon;
import com.inception.payload.dto.SalonDTO;
import com.inception.payload.dto.UserDTO;

import java.util.List;

public interface SalonService {
    Salon createSalon(SalonDTO salon, UserDTO user);
    Salon updateSalon(SalonDTO salon, UserDTO user , Long salonId) throws Exception;
   // List<Salon> getAllSalon(Long salonId);
    Salon getSalonId(Long salonId) throws Exception;
    Salon getSalonByOwnerId(Long ownerId);

    List<Salon> searchSalonByCity(String city);

    List<Salon> getAllSalon();
}
