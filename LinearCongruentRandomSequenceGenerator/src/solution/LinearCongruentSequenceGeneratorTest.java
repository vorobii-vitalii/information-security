package solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест для тестування правильності роботи генератора псевдовипадкових чисел
 * Параметри генератора були взяті з методичних рекомендацій до курсу
 */
class LinearCongruentSequenceGeneratorTest {
    private static final int A = 5;
    private static final int C = 3;
    private static final int M = 32;
    private static final int T_0 = 7;

    @Test
    public void testCorrectness() {
        LinearCongruentSequenceGenerator gen = new LinearCongruentSequenceGenerator(A, C, M, T_0);
        Integer[] expected = {6, 1, 8};
        assertArrayEquals(gen.generateSequence(3), expected);
    }

}