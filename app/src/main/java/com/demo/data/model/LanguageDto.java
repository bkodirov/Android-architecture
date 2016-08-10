package com.demo.data.model;

/**
 * Created by Beka on 8/3/16.
 */
public class LanguageDto {

    private String Name;
    private String Code;

    private int Value;

    public String getName() {
        return Name;
    }

    public String getCode() {
        return Code;
    }

    public int getValue() {
        return Value;
    }

    @Override
    public String toString() {
        return "LamguageDto{" +
                "Name='" + Name + '\'' +
                ", Code='" + Code + '\'' +
                ", Value=" + Value +
                '}';
    }
}
