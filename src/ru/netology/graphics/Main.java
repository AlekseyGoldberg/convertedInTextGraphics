package ru.netology.graphics;

import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;

import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverters converter = new TextGraphicsConverters(); // Создайте тут объект вашего класса конвертера
        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

        // Или то же, но с сохранением в файл:
//        PrintWriter fileWriter = new PrintWriter(new File("converted-image.txt"));
//        converter.setMaxRatio(3);
//        converter.setMaxWidth(100);
//        converter.setMaxHeight(150);
//        fileWriter.write(converter.convert("https://w-dog.ru/wallpapers/2/90/308182690823166/tropicheskij-plyazh-izumrud-poberezhe-goluboj-more-avstraliya-okean-letom.jpg"));
//        fileWriter.close();

    }
}
