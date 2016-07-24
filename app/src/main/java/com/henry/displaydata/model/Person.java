package com.henry.displaydata.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Henry.Oforeh on 2016/07/22.
 */
public class Person implements Parcelable
{
   private int id;
   private String  firstName;
   private String lastName;


   public Person(int id, String  firstName,String lastName)
   {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
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

   @Override
   public int describeContents()
   {
      return 0;
   }

   @Override
   public void writeToParcel(Parcel dest, int flags)
   {
      dest.writeString(firstName);
      dest.writeString(lastName);
      dest.writeInt(id);
   }

   // Creator
   public static final Parcelable.Creator CREATOR
           = new Parcelable.Creator() {
      public Person createFromParcel(Parcel in) {
         return new Person(in);
      }

      public Person[] newArray(int size) {
         return new Person[size];
      }
   };

   // "De-parcel object
   public Person(Parcel in)
   {
      firstName = in.readString();
      lastName = in.readString();
      id = in.readInt();
   }

}
