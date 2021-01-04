package solution;
import static solution.Algorithms.discretePower;

public class ElHamalKeyValidator {
    private final ElHamal elHamal;

    public ElHamalKeyValidator(ElHamal elHamal) {
        this.elHamal = elHamal;
    }

    /**
     * Перевірка на достовірність ключа
     */
    public boolean validate(ElHamalKey key, int m) {
        final int a = key.getA();
        final int b = key.getB();
        final int y = elHamal.getY();
        final int p = elHamal.getP();
        final int q = elHamal.getQ();

        int l = (discretePower(y, a, p) * discretePower(a, b, p)) % p;
        int r = discretePower(q, m, p);

        return l == r;
    }

}
