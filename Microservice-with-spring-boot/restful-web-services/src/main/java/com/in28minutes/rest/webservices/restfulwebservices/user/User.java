package com.in28minutes.rest.webservices.restfulwebservices.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@AllArgsConstructor
//@NoArgsConstructor
@Setter
public class User {
    Integer id;
    String name;
    Date birthDate;
}
