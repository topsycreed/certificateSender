package chursov;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class Util {
    public static Properties loadProperties(String path) {
        try (InputStream in = new FileInputStream(path)) {
            Properties props = new Properties();
            props.load(in);
            return props;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties: " + e.getMessage());
        }
    }

    public static String prepareEmailBody(String mdPath, String fullName) throws IOException {
        String content = Files.readString(Path.of(mdPath));
        return content.replace("{fullName}", fullName)
                .replace("\n", "<br>");
    }
}
