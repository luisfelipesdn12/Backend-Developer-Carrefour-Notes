import java.util.function.UnaryOperator;

public class Funcional {
    public static void main(String[] args) {
        UnaryOperator<Integer> calcVezes3 = (val) -> val*3;

        int val = 10;
        System.out.println(calcVezes3.apply(val));
    }
}