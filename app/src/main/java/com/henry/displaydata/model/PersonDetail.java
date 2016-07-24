package com.henry.displaydata.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Henry.Oforeh on 2016/07/24.
 */
public class PersonDetail implements Parcelable
{
    private Integer id;
    private Integer personId;
    private Integer age;
    private String favouriteColour;

    public PersonDetail(Integer id,Integer personId ,Integer age,String favouriteColour)
    {
        this.id = id;
        this.personId = personId;
        this.age = age;
        this.favouriteColour = favouriteColour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFavouriteColour() {
        return favouriteColour;
    }

    public void setFavouriteColour(String favouriteColour) {
        this.favouriteColour = favouriteColour;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }



    // "De-parcel object
    public PersonDetail(Parcel in)
    {
        favouriteColour = in.readString();
        id = in.readInt();
        age = in.readInt();
        personId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(favouriteColour);
        dest.writeInt(id);
        dest.writeInt(age);
        dest.writeInt(personId);
    }

    // Creator
    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public PersonDetail createFromParcel(Parcel in) {
            return new PersonDetail(in);
        }

        public PersonDetail[] newArray(int size) {
            return new PersonDetail[size];
        }
    };



}
