package com.demo.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Beka on 8/8/16.
 */
public class Country implements Serializable{
    @SerializedName("Name")
    public String name ;
    @SerializedName("Code")
    public String code ;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (!name.equals(country.name)) return false;
        return code != null ? code.equals(country.code) : country.code == null;

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
