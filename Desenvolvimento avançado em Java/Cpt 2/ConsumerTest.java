import java.util.function.Consumer;

public class ConsumerTest {
    public static void main(String[] args) {
        Consumer<String> print = (StringPar) -> System.out.println(StringPar);

        /**
         * Outra sintaxe:
         * Consumer<String> print = System.out::println;
         */
        
        print.accept("KKKKKKK");
    }
}