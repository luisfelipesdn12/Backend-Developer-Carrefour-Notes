public class ExemploImperativo {
    // Multiplica por 2 e imprime um valor caso ele seja par
    // Imperativa
    public static void main(String[] args) {
        int[] vals = {1,2,3,4,5,6,7,8,9,10};

        for (int i = 0; i < vals.length; i++) {
            int val = vals[i];

            // Se o número for par:
            if (val % 2 == 0) {
                val*= 2;

                System.out.println(val);
            }
        }
    }
}