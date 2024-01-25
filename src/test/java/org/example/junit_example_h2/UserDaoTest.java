package org.example.junit_example_h2;

import org.example.junit_example_h2.dao.UserDao;
import org.example.junit_example_h2.entity.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDaoTest {

    private final List<User> users = Users.users;
    @Autowired
    private UserDao dao;

    @BeforeAll
    void saveUsersToDB() {
        dao.saveAll(users);
    }

    @Test
    @Order(1)
    void checkIfCanSaveUsersToDB() {
        users.stream()
                .map(User::getId)
                .forEach(id -> Assertions.assertNotNull(dao.findById(id).orElse(null)));
    }

    @Test
    @Order(2)
    void checkIfUsersEmailsAreUniq() {


        var list = dao.findAll();
        var counter = list.stream()
                .map(User::getEmail)
                .distinct()
                .count();

        Assertions.assertEquals(list.size(), counter);
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3})
    @Order(3)
    void checkDeleteUsersFromDb(long id) {
        dao.deleteById(id);
        Assertions.assertNull(dao.findById(id).orElse(null));
    }
}
