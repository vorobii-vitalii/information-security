package solution;

import java.util.List;
import java.util.Random;

public class RSAHasher {
    private final int H_0;
    private final int n;

    public RSAHasher(int n, int h_0) {
        this.n = n;
        H_0 = h_0;
    }

    public RSAHasher(int n) {
        this.n = n;
        H_0 = new Random().nextInt();
    }

    public int hash(List<Integer> M) {
        int prevH = H_0;
        for (Integer num : M) {
            prevH = RSAlgorithms.discretePower(
                    num + prevH,
                    2,
                    n
            );
//            prevH = prevH ^ num;
        }
        return prevH;
    }

}
