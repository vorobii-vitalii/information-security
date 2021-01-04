package solution;

import java.util.ArrayList;
import java.util.List;

public class Algorithms {

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
     * Розширений алгоритм Евкліда
     */
    public static int[] extendedEuclidAlgorithm(int a, int b) {
        int u_p = 1, v_p = 0;
        int [] res = {0, 1};
        while (a % b != 1) {
            int q = (int) a / b;
            int u_n = u_p - q * res[0];
            int v_n = v_p - q * res[1];
            u_p = res[0];
            v_p = res[1];
            res[0] = u_n;
            res[1] = v_n;
            int r_n = a % b;
            a = b;
            b = r_n;
        }
        int q = a / b;
        res[0] = u_p - q * res[0];
        res[1] = v_p - q * res[1];
        return res;
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
