import java.time.LocalTime;
import java.util.function.Supplier;

public class SuplierTest {
    public static void main(String[] args) {
        Supplier<LocalTime> nowTime = () -> LocalTime.now();

        System.out.println(nowTime.get());
    }
}