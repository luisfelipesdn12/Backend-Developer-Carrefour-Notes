public class Aula {
    public static void main(String[] args) {
        System.out.println(fact(4));
    }

    public static int fact(int n) {
        if (n == 1) {
            return n;
        } else {
            return n * fact(n - 1);
        }
    }
}
