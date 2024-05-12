package com.batch.service;

import com.batch.entity.Person;

import java.util.List;

public interface IPersonService {

     void saveAll(List<Person> personList);
}
