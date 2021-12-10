import java.util.Arrays;

public class Fibonacci {
    private Fibonacci(String[] args) {
        Arrays.stream(args)
                .map(Integer::parseInt)
                .peek(value -> System.out.printf("fibonacci(%d)=", value))
                .map(value -> fibonacci(value))
                .forEach(value -> System.out.println(value));
    }

    private int fibonacci(int value) {
        if(value < 0)
            throw new IllegalArgumentException(String.format("%d: negative value unacceptable", value));
        if(value == 1 || value == 2)
            return 1;
        return fibonacci(value - 1) + fibonacci(value - 2);
    }

    public static void main(String[] args) {
        new Fibonacci(args);
    }
}