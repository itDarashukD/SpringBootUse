package by.darashuk.springBootUse.service;

import by.darashuk.springBootUse.dao.IPersonDao;
import by.darashuk.springBootUse.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final IPersonDao personDao;

    @Autowired()//сдесь подняем название РЕПО, и теперь работаем с БД, а не со списком
    public PersonService(@Qualifier("postgres") IPersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }
    public List<Person> getAllPerson(){
        return personDao.getAllPerson();
    }
    public Optional<Person>getById(UUID id){
        return personDao.getById(id);
    }
    public int updateById(UUID id,Person person){

        return personDao.updateById(id,person);
    }
    public int deleteById(UUID id){
        return personDao.deleteById(id);
    }

}
