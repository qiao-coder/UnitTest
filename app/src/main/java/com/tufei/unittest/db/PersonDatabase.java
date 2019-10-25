package com.tufei.unittest.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
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