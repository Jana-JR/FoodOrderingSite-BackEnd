package com.jana.model;

import lombok.Data;

// @Entity --> we wont create entity for this
@Data // getter stter

public class ContactInformation {

   private String email;
   private String mobile;
   private String twitter;
   private String instagram;

}
