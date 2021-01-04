package solution;

public class LinearCongruentSequenceGenerator implements SequenceGenerator<Integer> {
    private final int A;
    private final int C;
    private final int M;
    private final int T_0;

    public LinearCongruentSequenceGenerator(int a, int c, int m, int t_0) {
        A = a;
        C = c;
        M = m;
        T_0 = t_0;
    }

    public Integer[] generateSequence(int length) {
        final Integer[] sequence = new Integer[length];
        int previousValue = T_0;
        for (int i = 0; i < length; i++) {
            sequence[i] = (A * previousValue + C ) % M;
            previousValue = sequence[i];
        }
        return sequence;
    }

}
