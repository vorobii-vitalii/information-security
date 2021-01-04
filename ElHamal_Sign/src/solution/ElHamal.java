package solution;

/**
 * Клас, що описує конфігурацію Ель Гамаля
 */
public class ElHamal {
    private static final float MIN_QUOTE = 0.6f;
    private final int p;
    private final int q;
    private final int x;
    private final int y;

    public ElHamal(int p, int q) {
        this.p = p;
        this.q = q;
        this.x = calcX();
        this.y = calcY();
    }

    /**
     * Обрахунок значення x
     */
    private int calcX() {
        return Algorithms.findRandomInRangeWithMinQuote(q, p, MIN_QUOTE);
    }

    /**
     * Обрахунок значення y
     */
    private int calcY() {
        return Algorithms.discretePower(q, x, p);
    }

    public int getP() {
        return p;
    }

    public int getQ() {
        return q;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
