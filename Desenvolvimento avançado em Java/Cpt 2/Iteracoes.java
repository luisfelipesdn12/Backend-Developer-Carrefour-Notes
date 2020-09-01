import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Iteracoes {
    public static void main(String[] args) {
        String[] nomes = {"Lu", "Ra", "Gu", "Pe", "Bru", "Ga", "Vi"};

        String imprimir = Stream.of(nomes)
                            .collect(Collectors.joining(" == "));
        System.out.println(imprimir);
        /** 
         * Printa:
         * Lu == Ra == Gu == Pe == Bru == Ga == Vi
         */                  
        
        Stream.of(nomes)
                .forEach(n -> System.out.println("> " + n));
        /**
         * Printa:
         * > Lu
         * > Ra
         * > Gu
         * > Pe 
         * > Bru
         * > Ga
         * > Vi
         */
    }
}