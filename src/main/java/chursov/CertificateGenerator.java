package chursov;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CertificateGenerator {
    private static final String TEMPLATE_BASIC_RU_PATH = "src/main/resources/templates/template_basic_ru.png";
    private static final String TEMPLATE_FULL_RU_PATH = "src/main/resources/templates/template_full_ru.png";
    private static final String TEMPLATE_BASIC_EN_PATH = "src/main/resources/templates/template_basic_en.png";
    private static final String TEMPLATE_FULL_EN_PATH = "src/main/resources/templates/template_full_en.png";
    private static final String OUTPUT_DIR = "certificates";
    private static final int TEXT_Y_POSITION = 320;
    private static final int FONT_SIZE = 16;
    private static final Color FONT_COLOR = Color.BLACK;
    // Если используешь кастомный шрифт — укажи путь здесь (иначе будет стандартный Arial)
    private static final String CUSTOM_FONT_PATH = "src/main/resources/fonts/RussoOne-Regular.ttf"; // Пример: "src/main/resources/RussoOne-Regular.ttf";

    public static String generateBasicRu(String fullName) throws IOException {
        BufferedImage image = ImageIO.read(new File(TEMPLATE_BASIC_RU_PATH));
        Graphics2D g2d = image.createGraphics();

        // Включаем сглаживание текста
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Загружаем шрифт
        Font font = getFont();
        g2d.setFont(font);
        g2d.setColor(FONT_COLOR);

        // Текст и центрирование
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(fullName);
        int x = (image.getWidth() - textWidth) / 2;

        // Отрисовка текста
        g2d.drawString(fullName, x, TEXT_Y_POSITION);
        g2d.dispose();

        // Создание директории и сохранение файла
        File outputDir = new File(OUTPUT_DIR);
        outputDir.mkdirs();

        String outputPath = String.format("%s/%s_basic_ru.png", OUTPUT_DIR, fullName);
        ImageIO.write(image, "png", new File(outputPath));

        return outputPath;
    }

    public static String generateFullRu(String fullName) throws IOException {
        BufferedImage image = ImageIO.read(new File(TEMPLATE_FULL_RU_PATH));
        Graphics2D g2d = image.createGraphics();

        // Включаем сглаживание текста
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Загружаем шрифт
        Font font = getFont();
        g2d.setFont(font);
        g2d.setColor(FONT_COLOR);

        // Текст и центрирование
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(fullName);
        int x = (image.getWidth() - textWidth) / 2;

        // Отрисовка текста
        g2d.drawString(fullName, x, TEXT_Y_POSITION);
        g2d.dispose();

        // Создание директории и сохранение файла
        File outputDir = new File(OUTPUT_DIR);
        outputDir.mkdirs();

        String outputPath = String.format("%s/%s_full_ru.png", OUTPUT_DIR, fullName);
        ImageIO.write(image, "png", new File(outputPath));

        return outputPath;
    }

    public static String generateBasicEn(String fullName) throws IOException {
        BufferedImage image = ImageIO.read(new File(TEMPLATE_BASIC_EN_PATH));
        Graphics2D g2d = image.createGraphics();

        // Включаем сглаживание текста
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Загружаем шрифт
        Font font = getFont();
        g2d.setFont(font);
        g2d.setColor(FONT_COLOR);

        // Текст и центрирование
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(fullName);
        int x = (image.getWidth() - textWidth) / 2;

        // Отрисовка текста
        g2d.drawString(fullName, x, TEXT_Y_POSITION);
        g2d.dispose();

        // Создание директории и сохранение файла
        File outputDir = new File(OUTPUT_DIR);
        outputDir.mkdirs();

        String outputPath = String.format("%s/%s_basic_en.png", OUTPUT_DIR, fullName);
        ImageIO.write(image, "png", new File(outputPath));

        return outputPath;
    }

    public static String generateFullEn(String fullName) throws IOException {
        BufferedImage image = ImageIO.read(new File(TEMPLATE_FULL_EN_PATH));
        Graphics2D g2d = image.createGraphics();

        // Включаем сглаживание текста
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Загружаем шрифт
        Font font = getFont();
        g2d.setFont(font);
        g2d.setColor(FONT_COLOR);

        // Текст и центрирование
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(fullName);
        int x = (image.getWidth() - textWidth) / 2;

        // Отрисовка текста
        g2d.drawString(fullName, x, TEXT_Y_POSITION);
        g2d.dispose();

        // Создание директории и сохранение файла
        File outputDir = new File(OUTPUT_DIR);
        outputDir.mkdirs();

        String outputPath = String.format("%s/%s_full_en.png", OUTPUT_DIR, fullName);
        ImageIO.write(image, "png", new File(outputPath));

        return outputPath;
    }

    private static Font getFont() {
        try {
            if (CUSTOM_FONT_PATH != null) {
                return Font.createFont(Font.TRUETYPE_FONT, new File(CUSTOM_FONT_PATH)).deriveFont((float) FONT_SIZE);
            }
        } catch (Exception e) {
            System.err.println("Не удалось загрузить кастомный шрифт. Используется системный.");
        }
        return new Font("Arial", Font.BOLD, FONT_SIZE);
    }
}
