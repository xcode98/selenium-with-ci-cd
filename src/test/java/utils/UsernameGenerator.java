package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.*;

/**
 * Utilidad para generar usernames únicos con auto-incremento
 * Mantiene un contador persistente en archivo para garantizar unicidad
 */
public class UsernameGenerator {
    
    private static final String COUNTER_FILE = "username_counter.txt";
    
    /**
     * Genera un username único usando un contador incremental
     * Formato: baseUsername + 6 dígitos (ej: user000001, user000002, etc.)
     * 
     * @param baseUsername El username base (ej: "user", "admin", etc.)
     * @return Username único con formato baseUsername + 6 dígitos
     */
    public static String generateUniqueUsername(String baseUsername) {
        int counter = 1;
        
        try {
            // Leer el contador actual del archivo
            Path path = Paths.get(COUNTER_FILE);
            if (Files.exists(path)) {
                String content = Files.readString(path).trim();
                if (!content.isEmpty()) {
                    counter = Integer.parseInt(content);
                }
            }
            
            // Incrementar el contador
            counter++;
            
            // Guardar el nuevo contador
            Files.writeString(path, String.valueOf(counter));
            
            // Generar username con formato: baseUsername + 6 dígitos
            String uniqueUsername = baseUsername + String.format("%06d", counter);
            
            System.out.println("🔢 Contador actual: " + counter);
            System.out.println("🔑 Username generado: " + uniqueUsername);
            
            return uniqueUsername;
            
        } catch (Exception e) {
            // Fallback: usar timestamp si hay error
            System.out.println("⚠️ Error leyendo contador, usando timestamp como fallback");
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            return baseUsername + timestamp.substring(timestamp.length() - 6);
        }
    }
    
    /**
     * Resetea el contador a 0 (útil para testing)
     */
    public static void resetCounter() {
        try {
            Path path = Paths.get(COUNTER_FILE);
            Files.writeString(path, "0");
            System.out.println("🔄 Contador reseteado a 0");
        } catch (Exception e) {
            System.out.println("⚠️ Error reseteando contador: " + e.getMessage());
        }
    }
    
    /**
     * Obtiene el valor actual del contador sin incrementarlo
     */
    public static int getCurrentCounter() {
        try {
            Path path = Paths.get(COUNTER_FILE);
            if (Files.exists(path)) {
                String content = Files.readString(path).trim();
                if (!content.isEmpty()) {
                    return Integer.parseInt(content);
                }
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error leyendo contador: " + e.getMessage());
        }
        return 0;
    }
}
