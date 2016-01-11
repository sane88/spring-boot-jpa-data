package org.vr.spring;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.vr.spring.data.App;
import org.vr.spring.data.domain.Person;
import org.vr.spring.data.repository.PersonRepository;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class AppTest {

    private static final List<Person> PERSONS = new LinkedList<Person>(){{
        add(new Person("Jack", "Bauer"));
        add(new Person("Chloe", "O'Brian"));
        add(new Person("Kim", "Bauer"));
        add(new Person("David", "Palmer"));
        add(new Person("Michelle", "Dessler"));
    }};

    @Autowired
    private PersonRepository repository;

    @Before
    public void setUp() throws Exception {
        repository.save(PERSONS);
    }


    @After
    public void tearDown() throws Exception {
        repository.deleteAll();

    }

    @Test
    public void shouldFetchAll() throws Exception {
        Iterable<Person> persons = repository.findAll();
        Assert.assertTrue(((Collection<Person>)persons).containsAll(PERSONS));
    }

    @Test
    public void shouldFetchPersonByLastName() throws Exception {
        List<Person> persons = repository.findByLastName("Palmer");
        Assert.assertEquals(persons.size(), 1);
        Assert.assertEquals(new Person("David", "Palmer"), persons.get(0));
    }

    @Test
    public void shouldFetchPersonById() throws Exception {
        Person person = repository.findOne(2L);
        Assert.assertEquals(new Person("Chloe", "O'Brian"), person);
    }
}
