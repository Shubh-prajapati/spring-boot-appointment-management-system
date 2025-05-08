package com.inception.controller;
import com.inception.mapper.SalonMapper;
import com.inception.model.Salon;
import com.inception.payload.dto.SalonDTO;
import com.inception.payload.dto.UserDTO;
import com.inception.service.impl.SalonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/salons")
@RequiredArgsConstructor
public class SalonController {

     @Autowired
    private final SalonServiceImpl salonService;

    //https://localhost:5002/api/salons
    @PostMapping
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO){
        UserDTO userDTO=new UserDTO();
        userDTO.setId(1L);
        Salon salon=salonService.createSalon(salonDTO, userDTO);
        SalonDTO salonDTO1= SalonMapper.mapToDTO(salon);

        return ResponseEntity.ok(salonDTO1);
    }

    //https://localhost:5002/api/salons/2
    @PatchMapping("/{id}")
    public ResponseEntity<SalonDTO> updateSalon(
            @PathVariable("id") Long salonId,
            @RequestBody SalonDTO salonDTO) throws Exception {
        UserDTO userDTO=new UserDTO();
        userDTO.setId(1L);

        Salon salon=salonService.updateSalon(salonDTO, userDTO, salonId);
        SalonDTO salonDTO1= SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO1);
    }

    @GetMapping()

    public ResponseEntity<List<SalonDTO>> getSalon() throws Exception {
        UserDTO userDTO=new UserDTO();
        userDTO.setId(1L);
       List<Salon> salon=salonService.getAllSalon();

       List<SalonDTO> salonDTOS= salon.stream().map((salon1)->
               {
                    SalonDTO salonDTO=SalonMapper.mapToDTO(salon1);
                    return salonDTO;
                }
       ).toList();

        return  ResponseEntity.ok(salonDTOS);
    }

    //https://localhost:5002/api/salons/2
    @GetMapping("/{salonId}")
    public ResponseEntity<SalonDTO> getSalonById(
            @PathVariable Long salonId) throws Exception {

        Salon salon=salonService.getSalonId(salonId);

        SalonDTO salonDTO=SalonMapper.mapToDTO(salon);
        return ResponseEntity.ok(salonDTO);

    }

    // http://localhost:5002/api/salons/search?city=mum
    @GetMapping("/search")
    public ResponseEntity<List<SalonDTO>> searchSalon(
            @RequestParam("city") String city
    ) throws Exception {

        List<Salon> salon=salonService.searchSalonByCity(city);

        List<SalonDTO> salonDTOS= salon.stream().map((salon1)->
                {
                    SalonDTO salonDTO=SalonMapper.mapToDTO(salon1);
                    return salonDTO;
                }
        ).toList();

        return  ResponseEntity.ok(salonDTOS);
    }

    @GetMapping("/owner")
    public ResponseEntity<SalonDTO> getSalonByOwnerId(
            @PathVariable Long salonId) throws Exception{
        UserDTO userDTO= new UserDTO();
        userDTO.setId(1L);
        Salon salon=salonService.getSalonByOwnerId(userDTO.getId());

        SalonDTO salonDTO=SalonMapper.mapToDTO(salon);

        return ResponseEntity.ok(salonDTO);

    }
}
