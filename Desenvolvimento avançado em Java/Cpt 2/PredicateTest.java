import java.util.function.Predicate;

public class PredicateTest {
    public static void main(String[] args) {
        Predicate<Integer> ehPar = n -> n % 2 == 0;
        System.out.println(ehPar.test(8));
        System.out.println(ehPar.test(9));

        Div ehDivPor = (x, y) -> x % y == 0;
        System.out.println(ehDivPor.test(14, 7));
        System.out.println(ehDivPor.test(25, 2));
    }
}

@FunctionalInterface
interface Div {
    Boolean test(int x, int y);
}