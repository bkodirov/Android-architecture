package com.demo.data.cache.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class LanguageDbModel extends BaseModel {

    @Column
    private String name;

    @Column
    private String code;

    @Column
    @PrimaryKey
    private int value;

    public LanguageDbModel() {
    }

    public LanguageDbModel(String name, String code, int value) {
        this.name = name;
        this.code = code;
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setValue(int value) {
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
}