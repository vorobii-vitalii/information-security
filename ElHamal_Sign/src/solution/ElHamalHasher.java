package solution;

import java.util.List;
import java.util.Random;

public class ElHamalHasher {
    private final int H_0;
    private final int p;
    private final int n;

    public ElHamalHasher(ElHamal elHamal, int h_0) {
        this.n = elHamal.getP() * elHamal.getQ();
        this.p = elHamal.getP();
        H_0 = h_0;
    }

    public ElHamalHasher(ElHamal elHamal) {
        this.n = elHamal.getP() * elHamal.getQ();
        this.p = elHamal.getP();
        H_0 = new Random().nextInt();
    }

    public int hash(List<Integer> M) {
        int prevH = H_0;
        for (Integer num : M) {
            prevH = Algorithms.discretePower(
                    num + prevH,
                    2,
                    n
            );
        }
        return 2 + Math.abs(prevH % (p - 2));
    }

}
