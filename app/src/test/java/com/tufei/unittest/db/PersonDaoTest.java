package com.tufei.unittest.db;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;

import com.tufei.unittest.testutil.RobolectricTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;

public class PersonDaoTest extends RobolectricTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private Person tony = new Person("1", "tony", Sex.MALE);
    private Person marry = new Person("2", "marry", Sex.FEMALE);

    private PersonDatabase database;
    private PersonsDao personsDao;

    @Before
    public void setup() {
        //使用内存数据库。数据将会在测试用例运行过程中被保存，在进程结束后消失。
        database = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application,
                PersonDatabase.class)
                //允许在主线程进行查询。注意，仅用于测试。
                .allowMainThreadQueries()
                .build();
        personsDao = database.personDao();
    }

    @After
    public void clear() {
        database.close();
    }

    @Test
    public void savePerson_getPerson() {
        personsDao.insertPersion(tony);
        personsDao.getPersonById("1")
                .test()
                .assertNoErrors()
                //不会完成。因为room是响应式的，会继续观察数据库的数据变化。
                .assertNotComplete()
                .assertValue(tony::equals);
    }

    @Test
    public void savePersons_getPersons() {
        personsDao.insertPersion(tony);
        personsDao.insertPersion(marry);

        ArrayList<Person> persons = new ArrayList<>();
        persons.add(tony);
        persons.add(marry);
        personsDao.getPersons()
                .test()
                .assertNoErrors()
                .assertNotComplete()
                .assertValue(persons::equals);
    }

    @Test
    public void savePerson_deletePerson() {
        personsDao.insertPersion(tony);
        personsDao.deletePersonById("1");

        personsDao.getPersonById("1")
                .test()
                .assertNoErrors()
                .assertNotComplete()
                .assertNoValues();
    }

    @Test
    public void savePersons_deletePersons() {
        personsDao.insertPersion(tony);
        personsDao.insertPersion(marry);

        personsDao.deletePersons();

        personsDao.getPersons()
                .test()
                .assertValue(peoples -> peoples.size() == 0);
    }
}
