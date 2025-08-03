import chursov.CsvReader;
import chursov.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvReaderTest {
    @TempDir
    Path tempDir;

    private Path tempCsv;

    @Test
    void testReadCsvReturnsCorrectData() throws IOException {
        // Arrange
        String content = "ru;en;email\n" +
                "Алиса Смит;Alice Smith;alice@example.com\n" +
                "Боб Джонсон;Bob Johnson;bob@example.com\n";

        tempCsv = tempDir.resolve("test.csv");
        Files.writeString(tempCsv, content);

        // Act
        List<Person> people = CsvReader.readCsv(tempCsv.toString());

        // Assert
        assertEquals(2, people.size());
        Person first = people.get(0);
        Person second = people.get(1);

        assertAll("First person",
                () -> assertEquals("Алиса Смит", first.ru()),
                () -> assertEquals("Alice Smith", first.eng()),
                () -> assertEquals("alice@example.com", first.email())
        );

        assertAll("Second person",
                () -> assertEquals("Боб Джонсон", second.ru()),
                () -> assertEquals("Bob Johnson", second.eng()),
                () -> assertEquals("bob@example.com", second.email())
        );
    }

    @Test
    void testMissingFileThrowsException() {
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                CsvReader.readCsv("non_existing_file.csv")
        );
        assertTrue(ex.getMessage().contains("Failed to read CSV"));
    }
}
