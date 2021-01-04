package org.example.algorithm;

import java.util.Arrays;
import java.util.List;

public class BlowFishTest {

    public static void main(String[] args) {
//
        BlowFish bf = new BlowFish("Hello world");

//        int[] input = new int[] {84, 101};

        int[] input = BlowFish.Algorithms.splitLongToInts(1L);

        System.out.println(Arrays.toString(input));

        bf.cipherBlock(input);

        System.out.println("Combined " + BlowFish.Algorithms.mergeIntsToLong(input));

        System.out.println(Arrays.toString(input));

        bf.decipherBlock(input);

        System.out.println(Arrays.toString(input));


        System.out.println(".................");

        final List<Long> inputValues = List.of(1L, 2L, 3L);
        final List<Long> cipheredValues = bf.cipher(inputValues);
        final List<Long> decipheredValues = bf.decipher(cipheredValues);

        System.out.println(inputValues);
        System.out.println(cipheredValues);
        System.out.println(decipheredValues);

        System.out.println("....................");

        System.out.println(bf.cipher(List.of(72L)));

        System.out.println("--------------------");

        System.out.println(Long.toBinaryString(1704012785));
        System.out.println(Long.toBinaryString(-1753429772));

        System.out.println(Long.toBinaryString(BlowFish.Algorithms.mergeIntsToLong(new int[] {1704012785, -1753429772})));

    }

}
