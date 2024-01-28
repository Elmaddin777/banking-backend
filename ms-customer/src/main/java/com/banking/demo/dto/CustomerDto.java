package com.banking.demo.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerDto {

    private String name;
    private String surname;
    private LocalDate birthDate;
    private String gsmNumber;
    private String password;

}
