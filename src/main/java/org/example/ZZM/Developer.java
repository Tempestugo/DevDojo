package org.example.ZZM;

public class Developer extends Employee {
    private String mainLanguage;

    public Developer(String name, String mainLanguage) {
        super(name);
        this.mainLanguage = mainLanguage;
    }

    public String getMainLanguage() {
        return mainLanguage;
    }
}