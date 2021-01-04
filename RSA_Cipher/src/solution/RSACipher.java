package solution;

import java.util.ArrayList;
import java.util.List;

public class RSACipher {
    private final int e;
    private final int n;

    public RSACipher(int e, int n) {
        this.e = e;
        this.n = n;
    }

    public List<Integer> cipher(List<Integer> inputList) {
        List<Integer> res = new ArrayList<>();
        final int N = inputList.size();
        for (int i = 0; i < N; i++) {
            int v = RSAlgorithms.discretePower(inputList.get(i), e, n);
            res.add(v);
        }
        return res;
    }

}
