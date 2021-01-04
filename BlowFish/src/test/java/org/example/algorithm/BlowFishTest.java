package org.example.algorithm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class BlowFishTest {

    @Test
    @DisplayName("Коли довжина ключа більша 448 біт стається помилка")
    public void throwsIllegalArgumentExceptionWhenKeyIsTooLong() {
        final int TOO_BIG_SIZE = 500;

        // Ініціалізація списку байтів занадто великого розміру
        final byte[] bytes = new byte[TOO_BIG_SIZE];

        assertThrows(IllegalArgumentException.class, () ->
                new BlowFish(bytes)
        );
    }

    @Test
    @DisplayName("Перевірка розкладення 64 бітного числа на два 32 бітних")
    public void testSplittingLongWorksFine() {
        final long v = Long.MAX_VALUE;
        final int[] retrieved = BlowFish.Algorithms.splitLongToInts(v);

        assertEquals(2, retrieved.length);
        assertEquals(31, Integer.toBinaryString(retrieved[0]).length());
        assertEquals(32, Integer.toBinaryString(retrieved[1]).length());
    }

    @Test
    @DisplayName("Перевірка шифрування/розшифрування")
    public void testCipherDecipherCombination() {
        final BlowFish blowFish = new BlowFish("Деякий ключ");
        final List<String> inputList = List.of(
                "Тестування алгоритму",
                "Testing of the algorithm",
                "TestTest123...!",
                "Валідація Blowfish алгоритму",
                "Validation of this algorithm"
        );
        for (String str : inputList) {
            final List<Long> inputValues = BlowFish.Algorithms.convertStringToList(str);
            final List<Long> cipheredValues = blowFish.cipher(inputValues);
            final List<Long> decipheredValues = blowFish.decipher(cipheredValues);
            assertEquals(inputValues, decipheredValues);
        }
    }

    @Test
    @DisplayName("Перевірка зчеплення двох 32 - бітних чисел у одне 64 бітне")
    public void checkMerge32bitsNumbers() {
        int a = 0b000000000000000000000000000001;
        int b = 0b000000000000000000000000000001;
        long merged = BlowFish.Algorithms.mergeIntsToLong(new int[] {a, b});
        assertEquals("100000000000000000000000000000001", Long.toBinaryString(merged));
    }

    @Test
    @DisplayName("Коли у функції переданий масив розмірністю не рівною 2 - отримуємо помилку")
    public void assertMergeThrowsExceptionWhenInputArraysSizeMismatch() {
        assertThrows(IllegalArgumentException.class, () ->
                BlowFish.Algorithms.mergeIntsToLong(new int[] {}));
        assertThrows(IllegalArgumentException.class, () ->
                BlowFish.Algorithms.mergeIntsToLong(new int[] {1, 2, 3}));
    }

}