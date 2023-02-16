package com.tufei.unittest.db;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.internal.operators.completable.CompletableFromAction;

/**
 * @author tufei
 * @date 2018/3/15.
 */
public class PersonLocalSourceImpl implements PersonLocalSource {

    private PersonsDao mPersonsDao;

    public PersonLocalSourceImpl(PersonsDao personsDao) {
        mPersonsDao = personsDao;
    }

    @Override
    public Flowable<List<Person>> getPersons() {
        return mPersonsDao.getPersons();
    }

    @Override
    public Flowable<Person> getPerson(String personId) {
        return mPersonsDao.getPersonById(personId);
    }

    @Override
    public Completable savePerson(Person person) {
        return new CompletableFromAction(() -> mPersonsDao.insertPerson(person));
    }

    @Override
    public Completable deletePerson(String personId) {
        return new CompletableFromAction(() -> mPersonsDao.deletePersonById(personId));
    }

    @Override
    public Completable deletePersons() {
        return new CompletableFromAction(() -> mPersonsDao.deletePersons());
    }

}
