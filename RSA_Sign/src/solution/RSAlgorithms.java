package solution;

import java.util.ArrayList;
import java.util.List;

public class RSAlgorithms {

    /**
     * Функція для генерування простих чисел
     */
    public static List<Integer> generatePrimeNumbers(int length) {
        List<Integer> numbers = new ArrayList<>();
        for (int n = 2; numbers.size() != length; n++) {
            boolean isPrime = true;
            for (Integer r: numbers) {
                if (n % r == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                numbers.add(n);
            }
        }
        return numbers;
    }

    /**
     * Рекурсивний алгоритм Евкліда
     */
    public static int greatestCommonDivider(int a, int b) {
        if (b == 0) return a;
        return greatestCommonDivider(b, a % b);
    }

    /**
     * Дискретне піднесення до степеня по модулю
     */
    public static int discretePower(int x, int y, int p) {
        int res = 1;
        x = x % p;
        if (x == 0) return 0;
        while (y > 0)
        {
            if((y & 1)==1)
                res = (res * x) % p;
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }


}
