package solution;

import java.util.ArrayList;
import java.util.List;

public class RSADecipher {
    private final int d;
    private final int n;

    public RSADecipher(int d, int n) {
        this.d = d;
        this.n = n;
    }

    public List<Integer> decipher(List<Integer> list) {
        List<Integer> res = new ArrayList<>();
        final int N = list.size();
        for (int i = 0; i < N; i++) {
            int v = RSAlgorithms.discretePower(list.get(i), d, n);
            res.add(v);
        }
        return res;
    }
}
