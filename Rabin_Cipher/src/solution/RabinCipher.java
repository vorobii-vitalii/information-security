package solution;

import java.util.ArrayList;
import java.util.List;

public class RabinCipher {
    private final int N;

    public RabinCipher(int n) {
        N = n;
    }

    public List<Integer> cipher(List<Integer> inputList) {
        final List<Integer> cipheredList = new ArrayList<>();
        for (Integer m : inputList) {
            final int c = Algorithms.discretePower(m, 2, N);
            cipheredList.add(c);
        }
        return cipheredList;
    }

}
