package solution;

public class RSA {
    private final int p;
    private final int q;
    private int n;
    private int e;
    private int d;

    public RSA(int p, int q) {
        this.p = p;
        this.q = q;
        calcKeys();
    }

    private void calcKeys() {
        n = q * p;
        int phi = (p - 1) * (q - 1);
        for (int n = 2; n < phi; n++) {
            if (RSAlgorithms.greatestCommonDivider(n, phi) == 1) {
                e = n;
                break;
            }
        }
        for (int i = 1; ; i++) {
            float v = (float) (1 + phi * i) / e;
            if (v - (int) v == 0) {
                d = (int) v;
                break;
            }
        }
    }

    public int getN() {
        return n;
    }

    public int getE() {
        return e;
    }

    public int getD() {
        return d;
    }
}
