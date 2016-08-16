package com.demo.domain.entity;

import java.io.Serializable;

/**
 * Created by Beka on 8/3/16.
 */
public class Language implements Serializable{
    private String name;
    private String code;
    private int value;

    public Language(String name, String code, int value) {
        this.name = name;
        this.code = code;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Language language = (Language) o;

        if (value != language.value) return false;
        if (name != null ? !name.equals(language.name) : language.name != null) return false;
        return code.equals(language.code);

    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return "Language{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", value=" + value +
                '}';
    }
}
