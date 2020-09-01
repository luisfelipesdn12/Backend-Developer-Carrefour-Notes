import java.util.function.UnaryOperator;

public class Mutavel {
    public static void main(String[] args) {
        int val = 20;
        UnaryOperator<Integer> retornaDobro = n -> n*= 2;

        System.out.println(val); // 20
        System.out.println(retornaDobro.apply(val)); // 40
        System.out.println(val); // 40 (a variável foi alterada)
    }
}