import java.util.function.Function;

public class FunctionTest {
    public static void main(String[] args) {
        Function<Integer, Integer> dobra = n -> n*2;

        System.out.println(dobra.apply(5));
    }
}