package com.repository;

import com.config.EnvironmentManager;
import org.openqa.selenium.By;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ObjectRepositoryReader {

    private final Map<String, By> locatorMap = new HashMap<>();

    public ObjectRepositoryReader(String pageName) {
        try {
            String env = EnvironmentManager.getEnv();
            String path = "objectrepository/" + env + "/" + pageName + ".xml";

            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream(path);

            if (is == null) {
                throw new RuntimeException("OR file not found at: " + path);
            }

            Document doc = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(is);

            NodeList elements = doc.getElementsByTagName("Element");

            for (int i = 0; i < elements.getLength(); i++) {
                Element el = (Element) elements.item(i);

                String name = el.getElementsByTagName("NameOfElement")
                        .item(0).getTextContent();

                String type = el.getElementsByTagName("Type")
                        .item(0).getTextContent();

                String value = el.getElementsByTagName("Value")
                        .item(0).getTextContent();

                locatorMap.put(name, buildBy(type, value));
            }

        } catch (Exception e) {
            throw new RuntimeException("Unable to load OR file", e);
        }
    }

    public By getLocator(String elementName) {
        return locatorMap.get(elementName);
    }

    private By buildBy(String type, String value) {
        return switch (type.toLowerCase()) {
            case "id" -> By.id(value);
            case "name" -> By.name(value);
            case "xpath" -> By.xpath(value);
            case "css" -> By.cssSelector(value);
            default -> throw new IllegalArgumentException("Invalid locator type: " + type);
        };
    }
}
