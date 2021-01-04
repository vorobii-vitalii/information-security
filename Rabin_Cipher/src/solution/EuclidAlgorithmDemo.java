package solution;

import java.util.Arrays;

public class EuclidAlgorithmDemo {

    public static void main(String[] args) {
        int[ ] res = Algorithms.extendedEuclidAlgorithm(211, 79);
        System.out.println("Result: " + Arrays.toString(res));
    }

}
