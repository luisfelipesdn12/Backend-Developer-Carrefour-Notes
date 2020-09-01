public class FuncaoAltaOrdem {
    public static void main(String[] args) {
        Calc sum = (a, b) -> a+b;
        Calc mul = (a, b) -> a*b;
        Calc div = (a, b) -> a/b;
        Calc sub = (a, b) -> a-b;
        
        System.out.println(applyLambd(sum, 3, 14));
        System.out.println(applyLambd(mul, 3, 14));
        System.out.println(applyLambd(div, 3, 14));
        System.out.println(applyLambd(sub, 3, 14));
    }

    public static int applyLambd(Calc lambdaOperator, int x, int y) {
        return lambdaOperator.doOpt(x, y);
    }
    /**
    * A função applyLambd aplica um comportamento à
    * dois inteiros, mas esse comportamento é definido
    * como parâmetro. Logo, o método applyLambd é uma
    * função de alta ordem.
    */
}

@FunctionalInterface
interface Calc {
    public int doOpt(int x, int y);
}