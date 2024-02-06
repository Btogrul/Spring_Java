package com.ltc.lombok_06.dto;

import lombok.*;

//DATA TRANSFER OBJECT

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PersonDTO {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
}
