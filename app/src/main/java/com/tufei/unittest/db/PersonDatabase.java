package com.tufei.unittest.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

/**
 * @author tufei
 * @date 2018/3/15.
 */
@Database(entities = {Person.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class PersonDatabase extends RoomDatabase {

    abstract PersonsDao personDao();


    private PersonDatabase INSTANCE;

    private final Object lock = new Object();

    public PersonDatabase getInstance(Context context) {
        synchronized (lock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        PersonDatabase.class, "persons.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}