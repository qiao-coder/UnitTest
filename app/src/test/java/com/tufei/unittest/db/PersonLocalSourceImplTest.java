package com.tufei.unittest.db;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;

import com.tufei.unittest.testutil.RobolectricTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;

public class PersonLocalSourceImplTest extends RobolectricTest {
    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    private String tony = "Tony";
    private String marry = "Marry";

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
        Person person = new Person("1", tony, Sex.MALE);
        localDataSource.savePerson(person)
                .test()
                .assertNoErrors()
                .assertComplete();
        localDataSource.getPerson("1")
                .test()
                .assertNoErrors()
                .assertValue(person::equals);
    }

    @Test
    public void savePersons_retrievesPersons() {
        Person tony = new Person(this.tony, Sex.MALE);
        Person marry = new Person(this.marry, Sex.FEMALE);

        localDataSource.savePerson(tony)
                .test()
                .assertNoErrors()
                .assertComplete();
        localDataSource.savePerson(marry)
                .test()
                .assertNoErrors()
                .assertComplete();

        ArrayList<Person> persons = new ArrayList<>();
        persons.add(tony);
        persons.add(marry);
        localDataSource.getPersons()
                .test()
                .assertNoErrors()
                .assertValue(persons::equals);
    }

    @Test
    public void savePerson_deletePerson() {
        Person person = new Person("1", tony, Sex.MALE);

        localDataSource.savePerson(person)
                .test()
                .assertNoErrors()
                .assertComplete();

        localDataSource.getPerson("1")
                .test()
                .assertNoErrors()
                .assertValue(person::equals);

        localDataSource.deletePerson("1")
                .test()
                .assertNoErrors()
                .assertComplete();

        localDataSource.getPerson("1")
                .test()
                .assertNoErrors()
                .assertNotComplete();
    }

    @Test
    public void savePersons_deletePersons() {
        Person tony = new Person(this.tony, Sex.MALE);
        Person marry = new Person(this.marry, Sex.FEMALE);

        localDataSource.savePerson(tony)
                .test()
                .assertNoErrors()
                .assertComplete();
        localDataSource.savePerson(marry)
                .test()
                .assertNoErrors()
                .assertComplete();

        localDataSource.deletePersons()
                .test()
                .assertComplete();

        localDataSource.getPersons()
                .test()
                .assertNoErrors()
                .assertNotComplete();
    }
}
