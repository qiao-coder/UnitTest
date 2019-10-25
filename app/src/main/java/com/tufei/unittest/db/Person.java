package com.tufei.unittest.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import com.tufei.unittest.util.Objects;

import java.util.UUID;

/**
 * @author tufei
 * @date 2018/3/15.
 */
@Entity(tableName = "persons")
public class Person {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "entryid")
    private final String mId;

    @NonNull
    @ColumnInfo(name = "name")
    private final String mName;

    @NonNull
    @ColumnInfo(name = "sex")
    private final Sex mSex;

    @Ignore
    public Person(@NonNull String name, @NonNull Sex sex) {
        mId = UUID.randomUUID().toString();
        mName = name;
        mSex = sex;
    }

    public Person(@NonNull String id, @NonNull String name, @NonNull Sex sex) {
        mId = id;
        mName = name;
        mSex = sex;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public Sex getSex() {
        return mSex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equal(mId, person.mId) &&
                Objects.equal(mName, person.mName) &&
                Objects.equal(mSex, person.mSex);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mId, mName, mSex);
    }
}
