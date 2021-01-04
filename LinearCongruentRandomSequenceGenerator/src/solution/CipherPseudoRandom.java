package solution;

import solution.helpers.Algorithms;

public class CipherPseudoRandom {
    private final SequenceGenerator<Integer> sequenceGenerator;

    public CipherPseudoRandom(SequenceGenerator<Integer> sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    public String cipher(String text) {
        final int length = text.length();
        Integer[] sequence = sequenceGenerator.generateSequence(length);
        return Algorithms.xorTextWithMask(text, sequence);
    }

}
