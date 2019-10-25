package com.tufei.unittest.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author tufei
 * @date 2018/3/15.
 */
@Dao
public interface PersonsDao {

    /**
     * Select all persons from the persons table.
     *
     * @return all persons.
     */
    @Query("SELECT * FROM Persons")
    Flowable<List<Person>> getPersons();

    /**
     * Select a person by id.
     *
     * @param personId the person id.
     * @return the person with personId.
     */
    @Query("SELECT * FROM Persons WHERE entryid = :personId")
    Flowable<Person> getPersonById(String personId);

    /**
     * Insert a person in the database. If the person already exists, replace it.
     *
     * @param person the person to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPersion(Person person);

    /**
     * Update a person.
     *
     * @param person person to be updated
     * @return the number of persons updated. This should always be 1.
     */
    @Update
    int updatePerson(Person person);

    /**
     * Update the sex status of a person
     *
     * @param personId id of the person
     * @param sex      status to be updated
     */
    @Query("UPDATE persons SET sex = :sex WHERE entryid = :personId")
    void updateSex(String personId, String sex);

    /**
     * Delete a person by id.
     *
     * @return the number of persons deleted. This should always be 1.
     */
    @Query("DELETE FROM persons WHERE entryid = :personId")
    int deletePersonById(String personId);


    /**
     * Delete all persons.
     */
    @Query("DELETE FROM persons")
    void deletePersons();
}
