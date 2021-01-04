package solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Algorithms {

    /**
     * Дискретне піднесення до степеня по модулю
     */
    public static int discretePower(int x, int y, int p) {
        int res = 1;
        x = x % p;
        if (x == 0) return 0;
        while (y > 0)
        {
            if ( (y & 1) == 1)
                res = (res * x) % p;
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }

    /**
     * Знаходження випадкового числа між двома межами
     * @param minQuote Мінімальне співвідношення між згенерованим
     *                        випадковим числом і правою межою
     */
    public static int findRandomInRangeWithMinQuote (
            int a,
            int b,
            float minQuote)
    {
        final Random r = new Random();
        int leftBound = (int)( a + ((b - a) * minQuote));
        return leftBound + r.nextInt( Math.abs(b - leftBound));
    }

    /**
     * Рекурсивний алгоритм Евкліда
     */
    public static int euclidGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return euclidGCD(b, a % b);
    }

    /**
     * Знаходження взаємнопростого випадкового числа
     * в межах від 1 до p - 1
     */
    public static int findRandomCoprimeNumberBetween(int p) {
        final Random r = new Random();
        int a = 2 + Math.abs(r.nextInt(p - 3));
        while (euclidGCD(p - 1, a ) != 1) {
            a = 2 + Math.abs(r.nextInt(p - 3));
        }
        return a;
    }

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


}
