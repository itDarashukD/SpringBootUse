package by.darashuk.springBootUse.dao;

import by.darashuk.springBootUse.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDAO")
public class FakePersonDataAcessService implements IPersonDao {

    private final List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> getAllPerson() {
        return DB;
    }

    @Override
    public Optional<Person> getById(UUID id) {
        return DB.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public int updateById(UUID id, Person person) {
        return getById(id)
                .map(x -> {
                    int indexOfPerson = DB.indexOf(x);
                    if (indexOfPerson >= 0) {
                        DB.set(indexOfPerson, new Person(id, person.getName()));
                        return 1;
                    } else return 0;
                }).orElse(0);

    }

    @Override
    public int deleteById(UUID id) {
        Optional<Person> person = getById(id);
        if (person.isEmpty()) {
            return 0;
        }else
        DB.remove(person.get());
        return 1;
    }
}
