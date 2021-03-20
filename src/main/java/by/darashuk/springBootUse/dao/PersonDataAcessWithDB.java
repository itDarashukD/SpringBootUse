package by.darashuk.springBootUse.dao;

import by.darashuk.springBootUse.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
//здесь создали второй экземляр репозитория, с тем же интерфейсом, но с другим названием. и другой реализацией методов
// этот репо будет работать с БД, а не со списком МАР
//просто в сервисах подменяем одну строку с название РЕПО и все
// прога работает с БД, а не со списком
@Repository(value = "postgres")
public class PersonDataAcessWithDB implements IPersonDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired()
    public PersonDataAcessWithDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        return 0;
    }

    @Override
    public List<Person> getAllPerson() {
       final String sql = "SELECT id, name FROM Person";
       return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        });
    }
    @Override
    public Optional<Person> getById(UUID id) {
        final String sql = "SELECT id, name FROM Person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(sql,new Object[]{id},
                (resultSet,i )->{
            UUID personId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(personId,name);
                });
        return Optional.ofNullable(person);
    }

    @Override
    public int updateById(UUID id, Person person) {
        return 0;
    }

    @Override
    public int deleteById(UUID id) {
        return 0;
    }
}
