import chursov.CertificateGenerator;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CertificateGeneratorTest {
    @Test
    void testGenerateBasicBasicRuCertificate() throws Exception {
        String path = CertificateGenerator.generateBasicRu("Гена Чурсов");
        File file = new File(path);
        assertTrue(file.exists(), "Сертификат не сгенерирован");
    }

    @Test
    void testGenerateBasicFullRuCertificate() throws Exception {
        String path = CertificateGenerator.generateFullRu("Геннадий Чурсов");
        File file = new File(path);
        assertTrue(file.exists(), "Сертификат не сгенерирован");
    }

    @Test
    void testGenerateBasicBasicEnCertificate() throws Exception {
        String path = CertificateGenerator.generateBasicEn("Gena Chursov");
        File file = new File(path);
        assertTrue(file.exists(), "Сертификат не сгенерирован");
    }

    @Test
    void testGenerateBasicFullEnCertificate() throws Exception {
        String path = CertificateGenerator.generateFullEn("Gennadii Chursov");
        File file = new File(path);
        assertTrue(file.exists(), "Сертификат не сгенерирован");
    }
}
