package com.gwpublic.demo.client.customer.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Customer {

    private Long id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String gsmNumber;
    private String password;

}
