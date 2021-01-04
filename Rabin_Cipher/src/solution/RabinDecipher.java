package solution;

import java.util.ArrayList;
import java.util.List;

public class RabinDecipher {
    private final int p;
    private final int q;
    private final int n;

    public RabinDecipher(int p, int q) {
        this.p = p;
        this.q = q;
        this.n = p * q;
    }

    List<Integer> decipher(List<Integer> inputList, List<Integer> secret) {
        List<Integer> decipheredList = new ArrayList<>();
        final int p_x = (p + 1) / 4;
        final int q_x = (q + 1) / 4;
        for (int i = 0; i < inputList.size(); i++) {
            int c = inputList.get(i);
            int m_p = Algorithms.discretePower(c, p_x, p);
            int m_q = Algorithms.discretePower(c, q_x, q);
            int[] euclidRes = Algorithms.extendedEuclidAlgorithm(p, q);
            final int y_p = euclidRes[0], y_q = euclidRes[1];

            // k % n = n - k % n якщо k < 0

            int r1 = (y_p * p * m_q + y_q * q * m_p) % n;
            if (r1 < 0) {
                r1 = n - r1;
            }
            int r2 = n - r1;
            int r3 = (y_p * p * m_q - y_q * q * m_p) % n;
            if (r3 < 0) {
                r3 = n - r3;
            }
            int r4 = n - r3;

            decipheredList.add(r1 + r2 + r3 + r4 - secret.get(i));
        }
        return decipheredList;
    }

}
