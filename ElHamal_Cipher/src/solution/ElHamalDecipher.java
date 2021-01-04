package solution;

import java.util.ArrayList;
import java.util.List;

public class ElHamalDecipher {
    private final ElHamal elHamal;

    public ElHamalDecipher(ElHamal elHamal) {
        this.elHamal = elHamal;
    }

    public List<Integer> decipher(List<Integer> cipheredList) {
        final int N = cipheredList.size();
        final int x = elHamal.getX();
        final int p = elHamal.getP();

        final List<Integer> decipheredList = new ArrayList<>();

        for (int i = 0; i < N - 1; i += 2) {
            final int a = cipheredList.get(i);
            final int b = cipheredList.get(i + 1);

            final int m = b - (Algorithms.discretePower(a, x, p));
            decipheredList.add(m);
        }
        return decipheredList;
    }


}
