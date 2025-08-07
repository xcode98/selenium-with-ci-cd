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
     * Genera un username único usando timestamp + contador
     * Formato: baseUsername + timestamp + contador (ej: user240807001, user240807002, etc.)
     * 
     * @param baseUsername El username base (ej: "user", "admin", etc.)
     * @return Username único con formato baseUsername + timestamp + contador
     */
    public static String generateUniqueUsername(String baseUsername) {
        try {
            // Usar timestamp para mayor unicidad
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmm"));
            
            // Leer contador del archivo
            Path path = Paths.get(COUNTER_FILE);
            int counter = 1;
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
            
            // Generar username híbrido: base + timestamp + contador
            String uniqueUsername = baseUsername + timestamp + String.format("%03d", counter);
            
            System.out.println("🔢 Contador: " + counter + ", Timestamp: " + timestamp);
            System.out.println("🔑 Username generado: " + uniqueUsername);
            
            return uniqueUsername;
            
        } catch (Exception e) {
            // Fallback: solo timestamp
            System.out.println("⚠️ Error leyendo contador, usando solo timestamp");
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
            return baseUsername + timestamp;
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
