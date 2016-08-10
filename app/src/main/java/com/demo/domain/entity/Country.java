package com.demo.domain.entity;

import java.math.BigDecimal;

/**
 * Created by Beka on 8/8/16.
 */
public class Country {
    public long id ;
    public String name ;
    public String countryCode ;
    public BigDecimal fee ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (id != country.id) return false;
        if (name != null ? !name.equals(country.name) : country.name != null) return false;
        return countryCode != null ? countryCode.equals(country.countryCode) : country.countryCode == null;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", fee=" + fee +
                '}';
    }
}
