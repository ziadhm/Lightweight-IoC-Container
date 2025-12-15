package ioc;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class IoCContainer {
    // Maps interfaces to their implementations and priorities
    private final Map<Class<?>, List<Class<?>>> componentMap = new HashMap<>();
    private final Map<Class<?>, Integer> priorities = new HashMap<>();

    // Register components with optional priority
    @SuppressWarnings("unused")
    public void register(Class<?> iface, Class<?> impl, int priority) {
        componentMap.computeIfAbsent(iface, k -> new ArrayList<>()).add(impl);
        priorities.put(impl, priority);
    }

    // Overloaded method to register without priority (default = 1)
    public void register(Class<?> iface, Class<?> impl) {
        register(iface, impl, 1);
    }

    // Load configuration from an XML file
    public void loadFromConfig(String filePath) {
        try {
            // Parse XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(filePath));
            NodeList components = doc.getElementsByTagName("component");

            // Register components from XML
            for (int i = 0; i < components.getLength(); i++) {
                Element component = (Element) components.item(i);
                Class<?> iface = Class.forName(component.getAttribute("interface"));
                Class<?> impl = Class.forName(component.getAttribute("implementation"));
                int priority = component.hasAttribute("priority")
                        ? Integer.parseInt(component.getAttribute("priority"))
                        : 1; // Default priority
                register(iface, impl, priority);
            }
        } catch (IOException | ClassNotFoundException | NumberFormatException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException("Failed to load configuration from " + filePath, e);
        }
    }

    // Resolve dependencies using constructor injection
    public <T> T resolve(Class<T> iface) {
        // Get all implementations for the given interface
        List<Class<?>> implementations = componentMap.getOrDefault(iface, Collections.emptyList());
        if (implementations.isEmpty()) {
            throw new RuntimeException("No implementation found for " + iface.getName());
        }

        // Select implementation with the highest priority
        Class<?> selectedImpl = implementations.stream()
                .max(Comparator.comparingInt(priorities::get))
                .orElseThrow(() -> new RuntimeException("No suitable implementation found"));

        try {
            // Resolve constructor parameters recursively
            Constructor<?> constructor = selectedImpl.getConstructors()[0];
            Object[] params = Arrays.stream(constructor.getParameterTypes())
                                    .map(this::resolve)
                                    .toArray();

            // Create and return the instance
            return iface.cast(constructor.newInstance(params));
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | SecurityException | InvocationTargetException e) {
            throw new RuntimeException("Failed to create instance for " + iface.getName(), e);
        }
    }
}
