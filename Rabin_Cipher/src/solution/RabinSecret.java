package solution;

import java.util.ArrayList;
import java.util.List;

public class RabinSecret {
    private final int p;
    private final int q;
    private final int n;

    public RabinSecret(int p, int q) {
        this.p = p;
        this.q = q;
        this.n = p * q;
    }

    public List<Integer> formSecret(List<Integer> input, List<Integer> cipheredInput) {
        List<Integer> result = new ArrayList<>();
        final int p_x = (p + 1) / 4;
        final int q_x = (q + 1) / 4;
        for (int i = 0; i < input.size(); i++) {
            int c = cipheredInput.get(i);
            int m_p = Algorithms.discretePower(c, p_x, p);
            int m_q = Algorithms.discretePower(c, q_x, q);
            int[] euclidRes = Algorithms.extendedEuclidAlgorithm(p, q);
            final int y_p = euclidRes[0], y_q = euclidRes[1];

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

            System.out.println("r1 = " + r1 + " r2 = " + r2 + " r3 = " + r3 + " r4 = " + r4);

            result.add(r1 + r2 + r3 + r4 - input.get(i));
        }
        return result;
    }

}
