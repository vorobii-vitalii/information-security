package solution;

import solution.helpers.Algorithms;

public class DecipherPseudoRandom {
    private final SequenceGenerator<Integer> sequenceGenerator;

    public DecipherPseudoRandom(SequenceGenerator<Integer> sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    public String decipher(String text) {
        final int length = text.length();
        Integer[] sequence = sequenceGenerator.generateSequence(length);
        return Algorithms.xorTextWithMask(text, sequence);
    }

}
