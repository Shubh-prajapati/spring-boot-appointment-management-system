package com.inception.service.impl;
import com.inception.model.Salon;
import com.inception.payload.dto.SalonDTO;
import com.inception.payload.dto.UserDTO;
import com.inception.repository.SalonRepository;
import com.inception.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SalonServiceImpl implements SalonService {

    @Autowired
    private SalonRepository salonRepository;
    @Override
    public Salon createSalon(SalonDTO req, UserDTO user) {
        Salon salon =new Salon();
        salon.setName(req.getName());
        salon.setAddress(req.getAddress());
        salon.setEmail(req.getEmail());
        salon.setCity(req.getCity());
        salon.setImages(req.getImages());
        salon.setOwnerId(req.getOwnerId());
        salon.setOpenTime(req.getOpenTime());
        salon.setCloseTime(req.getCloseTime());
        salon.setPhoneNumber(req.getPhoneNumber());

        return salonRepository.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) throws Exception {

        Salon existingSalon =salonRepository.findById(salonId).orElse(null);
        if(!salon.getOwnerId().equals(user.getId())){
            throw new Exception("You don't have permission to update this salon");
        }
        if(existingSalon != null && salon.getOwnerId().equals(user.getId())){
            existingSalon.setCity(salon.getCity());
            existingSalon.setName(salon.getName());
            existingSalon.setAddress(salon.getAddress());
            existingSalon.setEmail(salon.getEmail());
            existingSalon.setImages(salon.getImages());
            existingSalon.setOpenTime(salon.getOpenTime());
            existingSalon.setCloseTime(salon.getCloseTime());
            existingSalon.setOwnerId(user.getId());
            existingSalon.setPhoneNumber(salon.getPhoneNumber());

                return salonRepository.save(existingSalon);
        }
        throw new Exception("Salon not exits");

    }

//    @Override
//    public List<Salon> getAllSalon(Long salonId) {
//        return salonRepository.findAll();
//    }

    @Override
    public List<Salon> getAllSalon() {

        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonId(Long salonId) throws Exception {

   Salon salon=salonRepository.findById(salonId).orElse(null);
    if(salon == null){
        throw new Exception("Salon not Exits");
    }
    return salon;

    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {

        return salonRepository.findByOwnerId(ownerId);

    }

    @Override
    public List<Salon> searchSalonByCity(String city) {

         return salonRepository.searchSalon(city);
    }
}
