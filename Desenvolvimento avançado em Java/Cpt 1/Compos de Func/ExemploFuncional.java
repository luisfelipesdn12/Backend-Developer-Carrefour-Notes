import java.util.Arrays;

public class ExemploFuncional {
    // Multiplica por 2 e imprime um valor caso ele seja par
    // Funcional
    public static void main(String[] args) {
        int[] vals = {1,2,3,4,5,6,7,8,9,10};
        
        Arrays.stream(vals)
                    .filter(val -> val % 2 == 0)
                    .map(val -> val * 2)
                    .forEach(val -> System.out.println(val));
    }
}