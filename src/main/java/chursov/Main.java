package chursov;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("All certificates processed!");
        List<Person> basicPeople = CsvReader.readCsv("src/main/resources/csv/basic.csv");
        List<Person> fullPeople = CsvReader.readCsv("src/main/resources/csv/full.csv");
        for (Person person : basicPeople) {
            CertificateSender.processBasicPerson(person);
        }
        for (Person person : fullPeople) {
            CertificateSender.processFullPerson(person);
        }
        System.out.println("All certificates processed!");
    }
}