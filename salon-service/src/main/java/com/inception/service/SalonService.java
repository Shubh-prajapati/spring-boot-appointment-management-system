package com.inception.service;

import com.inception.model.Salon;
import com.inception.payload.dto.SalonDTO;
import com.inception.payload.dto.UserDTO;

public interface SalonService {
    Salon createSalon(SalonDTO salon, UserDTO user);
}
