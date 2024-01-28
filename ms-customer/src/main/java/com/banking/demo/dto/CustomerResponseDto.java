package com.banking.demo.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class CustomerResponseDto {

    private Long id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String gsmNumber;
    private String password;

}
