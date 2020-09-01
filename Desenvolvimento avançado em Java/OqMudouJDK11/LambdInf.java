import java.util.function.Function;

public class LambdInf {
    public static void main(String[] args) {
        Function<Integer, Integer> ehDivPor = (var x) -> x % 2;

        var resp = ehDivPor.apply(20);

        System.out.println(resp);
    }
}