public class Aula {
    public static void main(String[] args) {
        Funcao minhaF = (val, vezes) -> {
            for (int i = 0; i < vezes; i++) {
                System.out.println(val);
            }
        };

        minhaF.gerar("KKK", 3);
    }
}

@FunctionalInterface
interface Funcao {
    void gerar(String val, int vezes);
}