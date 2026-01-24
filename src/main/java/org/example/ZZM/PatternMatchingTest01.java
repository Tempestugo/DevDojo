package org.example.ZZM;

public class PatternMatchingTest01 {
    public static void main(String[] args) {
        Employee employee = new Developer("William", "Java");

        // Jeito Antigo (necessário fazer o cast manual)
        /*
        if (employee instanceof Developer) {
            Developer developer = (Developer) employee;
            System.out.println(developer.getMainLanguage());
        }
        */

        // NOVO JEITO (Java 16 Pattern Matching)
        // Se employee for Developer, já cria a variável 'developer' automaticamente
        if (employee instanceof Developer developer) {
            System.out.println(developer.getMainLanguage());
        }
    }
}
