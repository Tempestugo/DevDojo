package org.example.ZZk.junit;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonService {
    public boolean isAdult(Person person) {
        // O teste exige que lance IllegalArgumentException se for null
        if (person == null) {
            throw new IllegalArgumentException();
        }
        return person.getAge() >= 18;
    }

    public List<Person> filterRemovingNotAdult(List<Person> personList) {
        return personList.stream()
                .filter(this::isAdult)
                .collect(Collectors.toList());
    }
}