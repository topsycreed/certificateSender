package chursov;

public class CertificateSender {
    public static void processBasicPerson(Person person) {
        try {
            System.out.printf("📌 Processing: %s %s (%s)%n", person.ru(), person.eng(), person.email());
            String certPathRu = CertificateGenerator.generateBasicRu(person.ru());
            String certPathEn = CertificateGenerator.generateBasicEn(person.eng());
            EmailService.send(person.email(), person.ru(), certPathRu, certPathEn);
        } catch (Exception e) {
            System.err.printf("❌ Error for %s: %s%n", person.ru(), e.getMessage());
        }
    }

    public static void processFullPerson(Person person) {
        try {
            System.out.printf("📌 Processing: %s %s (%s)%n", person.ru(), person.eng(), person.email());
            String certPathRu = CertificateGenerator.generateFullRu(person.ru());
            String certPathEn = CertificateGenerator.generateFullEn(person.eng());
            EmailService.send(person.email(), person.ru(), certPathRu, certPathEn);
        } catch (Exception e) {
            System.err.printf("❌ Error for %s: %s%n", person.ru(), e.getMessage());
        }
    }
}
