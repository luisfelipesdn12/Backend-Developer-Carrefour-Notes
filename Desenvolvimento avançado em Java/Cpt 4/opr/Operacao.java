public interface Operacao {
    public int calc(int a, int b);
}

public class Sum implements Operacao {
    @Override
    public int calc(int a, int b) {
        return a + b;
    }
}

public class Sub implements Operacao {
    @Override
    public int calc(int a, int b) {
        return a - b;
    }
}

public class Mult implements Operacao {
    @Override
    public int calc(int a, int b) {
        return a * b;
    }
}

public class Div implements Operacao {
    @Override
    public int calc(int a, int b) {
        return a / b;
    }
}
