package com.station.admin.user;

import com.station.common.entity.Role;
import com.station.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void TestCreateRole() {

        Role admin = entityManager.find(Role.class, 1);
        User sangay = new User("stenzin@rma.org.bt", "123", "Sangay", "Tenzin");
        sangay.addRole(admin);
        User savedUser = userRepository.save(sangay);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    @Rollback(value = false)
    public void TestCreateTwoRole() {

        User noko = new User("noko@rma.org.bt", "123", "Sangay", "Tenzin");
        Role Editor = new Role(3);
        Role Assistant = new Role(4);
        noko.addRole(Editor);
        noko.addRole(Assistant);
        User savedUser = userRepository.save(noko);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "nok@gmail.com";
        User userByEmail = userRepository.getUserByEmail(email);
        assertThat(userByEmail).isNotNull();
    }

    @Test
    public void testPaging() {
        int pageNumber = 1;
        int pageSize = 4;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> page = userRepository.findAll(pageable);
        List<User> content = page.getContent();
        content.forEach(user -> System.out.println(user));
    }

    @Test
    public void TestSearchUser(){
        String keyword="sangay";
        int pageNumber = 1;
        int pageSize = 4;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        Page<User> page = userRepository.findAll(keyword,pageable);
//        List<User> content = page.getContent();
//        content.forEach(user -> System.out.println(user));
    }

    @Test
    public void encodePassword(){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("Pass12!@"));

    }
}