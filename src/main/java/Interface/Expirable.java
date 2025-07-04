package Interface;
import java.time.LocalDate;

public interface Expirable {
    String getName();
    LocalDate getExpiryDate();
    boolean isExpired();
}
