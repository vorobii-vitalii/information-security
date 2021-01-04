package solution;

import java.util.ArrayList;
import java.util.List;

public class ElHamalCipher {
    private final ElHamal elHamal;

    public ElHamalCipher(ElHamal elHamal) {
        this.elHamal = elHamal;
    }

    public List<Integer> cipher(List<Integer> inputList) {
        List<Integer> cipheredList = new ArrayList<>();
        for (Integer m : inputList) {
            CryptoPair cryptoPair = cipher(m);
            cipheredList.add(cryptoPair.a);
            cipheredList.add(cryptoPair.b);
        }
        return cipheredList;
    }

    /**
     * Розрахунок криптограми
     */
    private CryptoPair cipher(int m) {
        final int p = elHamal.getP();
        final int q = elHamal.getQ();
        final int y = elHamal.getY();

        final int k = Algorithms.findRandomCoprimeNumberBetween(p);

        // Розрахунок криптограми
        final int a = Algorithms.discretePower(q, k, p);
        final int b = Algorithms.discretePower(y, k, p) + m % p;

        return new CryptoPair(a, b);
    }

    /**
     * Контейнер криптограми
     */
    private static class CryptoPair {
        final int a;
        final int b;

        public CryptoPair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

}
