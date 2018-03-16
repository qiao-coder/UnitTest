package com.tufei.unittest.db;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;

import com.tufei.unittest.testutil.RobolectricTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.util.UUID;

import io.reactivex.functions.Predicate;

public class PersonLocalSourceImplTest extends RobolectricTest {
    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    private String tony = "Tony";
    private String marry = "Marry";
    private String tom = "Tom";

    private PersonDatabase database;
    private PersonLocalSourceImpl localDataSource;

    @Before
    public void setup() {
        database = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application,
                PersonDatabase.class)
                .allowMainThreadQueries()
                .build();
        localDataSource = new PersonLocalSourceImpl(database.personDao());
    }

    @After
    public void clear() {
        database.close();
    }

    @Test
    public void savePerson_retrievesPerson() {
        String id = UUID.randomUUID().toString();
        Person person = new Person(id, tony, Sex.MALE);
        localDataSource.savePerson(person)
                .test()
                .assertNoErrors()
                .assertComplete();
        localDataSource.getPerson(id)
                .test()
                .assertNoErrors()
                .assertValue(person::equals);
    }
}
