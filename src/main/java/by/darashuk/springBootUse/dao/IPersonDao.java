package by.darashuk.springBootUse.dao;

import by.darashuk.springBootUse.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPersonDao {

    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id,person);
    }
    List<Person> getAllPerson();

    Optional<Person> getById(UUID id);

    int updateById(UUID id,Person person);

    int deleteById(UUID id);


    }



