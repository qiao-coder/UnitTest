package com.tufei.unittest.db;

import android.arch.persistence.room.TypeConverter;

/**
 * @author tufei
 * @date 2018/3/15.
 */
public class Converters {
    @TypeConverter
    public Sex intToSex(int value) {
        Sex sex;
        switch (value) {
            case 0:
                sex = Sex.MALE;
                break;
            case 1:
                sex = Sex.FEMALE;
                break;
            default:
                throw new IllegalArgumentException("Only 0 and 1!");
        }
        return sex;
    }

    @TypeConverter
    public int sexToInt(Sex sex) {
        int value;
        switch (sex) {
            case MALE:
                value = 0;
                break;
            case FEMALE:
                value = 1;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return value;
    }
}
