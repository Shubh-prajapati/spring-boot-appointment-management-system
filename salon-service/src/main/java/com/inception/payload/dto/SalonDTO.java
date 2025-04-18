package com.inception.payload.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SalonDTO {


    private Long id;

    private String name;

    private List<String> images;

    private String address;

    private String phoneNumber;

    private String city;

    private Long ownerId;

    private LocalDateTime openTime;

    private LocalDateTime closeTime;
}
