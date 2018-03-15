package com.tufei.unittest.db;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * @author tufei
 * @date 2018/3/15.
 */
public interface PersonLocalSource {
    Flowable<List<Person>> getPersons();

    Flowable<Person> getPerson(String personId);

    Completable savePerson(Person person);

    Completable deletePersons();

    Completable deletePerson(String personId);
}
