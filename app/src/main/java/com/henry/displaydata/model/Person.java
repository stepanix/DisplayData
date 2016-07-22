package com.henry.displaydata.model;

/**
 * Created by Henry.Oforeh on 2016/07/22.
 */
public class Person
{
   private  Integer id;
   private String  firstName;
   private String lastName;

   public Person(Integer id, String  firstName,String lastName)
   {
      this.id = id;
      this.firstName = firstName;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
}
