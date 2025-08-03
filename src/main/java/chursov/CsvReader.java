package chursov;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    public static List<Person> readCsv(String path) {
        List<Person> people = new ArrayList<>();
        try (
                Reader in = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)
        ) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withDelimiter(';')
                    .withHeader("ru", "eng", "email")
                    .withSkipHeaderRecord()
                    .parse(in);

            for (CSVRecord r : records) {
                people.add(new Person(
                        r.get("ru").trim(),
                        r.get("eng").trim(),
                        r.get("email").trim()
                ));
            }
        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to read CSV: " + e.getMessage(), e);
        }
        return people;
    }
}
