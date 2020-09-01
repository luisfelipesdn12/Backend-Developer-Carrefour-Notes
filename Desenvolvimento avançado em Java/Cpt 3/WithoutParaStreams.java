import java.util.stream.IntStream;

public class WithoutParaStreams {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        IntStream.range(1, 100000)
                        .forEach( n ->
                            fact(n)
                        );
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
    
    public static int fact(int n) {
        int fact = n;

        for (int i = n-1; i >= 1; i--) {
            fact*= i;
        }

        return fact;
    }
}