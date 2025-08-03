# 📜 Certificate Generator and Email Sender (Java)

Этот проект предназначен для автоматической генерации PNG-сертификатов на основе шаблона и рассылки их по email. Сертификаты формируются на двух языках и прикладываются к письму в виде вложений.

---

## ⚙️ Требования

- Java 17+
- Gradle
- Gmail-аккаунт (с включённой двухфакторной аутентификацией и сгенерированным паролем приложения)
- Подключение к интернету (для отправки писем)

---

## 🔐 Настройка Gmail для SMTP
- Перейдите в https://myaccount.google.com/security
- Включите двухэтапную аутентификацию (если ещё не включена)
- Перейдите в раздел Пароли приложений: https://myaccount.google.com/apppasswords
- Выберите тип приложения → "Другое (наименование)" → Введите, например: Java SMTP
- Скопируйте сгенерированный 16-значный пароль

## 🔧 Конфигурация
Создайте файл:
src/main/resources/config.properties

Содержимое:
email.user=yourgmail@gmail.com
email.password=app_specific_password_here

❗ Никогда не коммить этот файл в Git!

## 📬 Шаблон письма
Создайте файл:
src/main/resources/email/email_template.md

Содержит Markdown-текст с подстановкой имени, пример:
```html
Здравствуйте, {fullName}!

Поздравляем с успешным участием в нашем мероприятии.

Ваш сертификат прикреплён к этому письму.

С уважением,  
Команда организаторов
```

## 📄 Формат CSV-файла
Создайте файлы:
- src/main/resources/csv/basic.csv
- src/main/resources/csv/full.csv

Формат:
```csv
ru;eng;email

Иван Иванов;Ivan Ivanov;ivan@example.com
```

❗ Обязательно сохраните файл в UTF-8!

## Шаблон сертификатов
Файлы изображений PNG, например:
- template_basic_en.png
- template_basic_ru.png
- template_full_en.png
- template_full_ru.png
и т.д.

Должны быть положены в папку src/main/resources/templates.

## 📁 Запуск
```bash
./gradlew run
```
Или можно запустить Main вручную из IDE.

## 🧪 Тесты
```bash
./gradlew test
```

## ❗ Возможные проблемы:
- Никогда не коммить config.properties с логином и паролем, и реальные ФИО и почты
- Не используйте обычный пароль от Gmail — только пароль приложения
- Убедитесь, что CSV в кодировке UTF-8, иначе возможны ошибки с кириллицей


