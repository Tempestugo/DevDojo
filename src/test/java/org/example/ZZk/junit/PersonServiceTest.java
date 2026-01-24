package org.example.ZZk.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {
    private Person person;
    private PersonService personService;

    // @BeforeEach: Executa esse método antes de CADA teste unitário.
    // Garante que os testes sejam independentes [7].
    @BeforeEach
    void setUp() {
        person = new Person(15);
        personService = new PersonService();
    }

    @Test
    @DisplayName("A person should be not adult when age is lower than 18")
    void isAdult_ReturnFalse_WhenAgeIsLowerThan18() {
        // Assertions.assertFalse: O teste passa se o retorno for false [8]
        assertFalse(personService.isAdult(person));
    }

    @Test
    @DisplayName("A person should be adult when age is greater or equals than 18")
    void isAdult_ReturnTrue_WhenAgeIsGreaterOrEqualsThan18() {
        person.setAge(18);
        // Assertions.assertTrue: O teste passa se o retorno for true [9]
        assertTrue(personService.isAdult(person));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when person is null")
    void isAdult_ShouldThrowException_WhenPersonIsNull() {
        // assertThrows: Verifica se o código lança a exceção esperada [10]
        assertThrows(IllegalArgumentException.class, () -> personService.isAdult(null));
    }

    @Test
    @DisplayName("Should return list with only adults")
    void filterRemovingNotAdult_ReturnListWithAdultsOnly_WhenListOfPersonWithAdultIsPassed() {
        Person person1 = new Person(17);
        Person person2 = new Person(18);
        Person person3 = new Person(21);
        List<Person> personList = List.of(person1, person2, person3);

        // O método deve filtrar quem tem menos de 18, sobrando apenas person2 e person3 (2 pessoas)
        assertEquals(2, personService.filterRemovingNotAdult(personList).size());
    }
}
