package solution;

public class ElHamalKey {
    private final int a;
    private final int b;

    public ElHamalKey(ElHamal elHamal, int m) {
        final int p = elHamal.getP();
        final int q = elHamal.getQ();
        final int x = elHamal.getX();
        final int k = Algorithms.findRandomCoprimeNumberBetween(p);

        a = calcA(q, k, p);
        b = calcB(x, a, k, p, m);
    }

    /**
     * Розрахунок значення а
     */
    private int calcA(int q, int k, int p) {
        return Algorithms.discretePower(q, k, p);
    }

    /**
     * Розрахунок значення b
     */
    private int calcB(int x, int a, int k, int p, int m) {
        final int r = m % (p - 1);
        for (int i = 1; ; i++) {
            int o = (a * x + k * i) % (p - 1);
            if (o == r) {
                return i;
            }
        }
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}
