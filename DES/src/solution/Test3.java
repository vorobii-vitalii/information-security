package solution;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test3 {

    private static final int FEISTEL_ROUNDS = 16;

    static List<Integer> G = List.of(
            56, 48, 40, 32, 24, 16, 8,
            0, 57, 49, 41, 33, 25, 17,
            9, 1, 58, 50, 42, 34, 26,
            18, 10, 2, 59, 51, 43, 35,
            62, 54, 46, 38, 30, 22, 14,
            6, 61, 53, 45, 37, 29, 21,
            13, 5, 60, 52, 44, 36, 28,
            20, 12, 4, 27, 19, 11, 3
    );

    static List<Integer> vectorH = List.of(
        14, 17, 11, 24, 1, 5,
        3, 28, 15, 6, 21, 10,
        23, 19, 12, 4, 26, 8,
        16, 7, 27, 20, 13, 2,
        41, 52, 31, 37, 47, 55,
        30, 40, 51, 45, 33, 48,
        44, 49, 39, 56, 34, 53,
        46, 42, 50, 36, 29, 32
    )
        .stream()
        .map(n -> n - 1)
        .collect(Collectors.toList());

    static List<Integer> IP = List.of(
        58, 50, 42, 34, 26, 18, 10, 2,
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8,
        57, 49, 41, 33, 25, 17, 9, 1,
        50, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7
    )
        .stream()
        .map(n -> n - 1)
        .collect(Collectors.toList());

    static List<Integer> vectorE = List.of(
        32, 1, 2, 3, 4, 5,
        4, 5, 6, 7, 8, 9,
        8, 9, 10, 11, 12, 13,
        12, 13, 14, 15, 16, 17,
        16, 17, 18, 19, 20, 21,
        20, 21, 22, 23, 24, 25,
        24, 25, 26, 27, 28, 29,
        28, 29, 30, 31, 32, 1
    )
        .stream()
        .map(n -> n - 1)
        .collect(Collectors.toList());

    static List<Integer> vectorP = List.of(
            16, 7, 20, 21,
            29, 12, 28, 17,
            1, 15, 23, 26,
            5, 18, 31, 10,
            2, 8, 24, 14,
            32, 27, 3, 9,
            19, 13, 30, 6,
            22, 11, 4, 25
    )
        .stream()
        .map(n -> n - 1)
        .collect(Collectors.toList());

    static int[][][] tensorS = new int[][][]{
        {
            { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
            { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
            { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
            { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
        },
        {
            { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
            { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
            { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
            { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
        },
        {
            { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
            { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
            { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
            { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 },
        },
        {
            { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
            { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
            { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
            { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 },
        },
        {
            { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
            { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
            { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
            { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 },
        },
        {
            { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
            { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
            { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
            { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 },
        },
        {
            { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
            { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
            { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
            { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 }
        },
        {
            { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
            { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
            { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
            { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 }
        }
    };

    static List<Integer> keyShifts = List.of(1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1);

    static final int HALF_IP = 64 / 2;
    static final long HALF_IP_MASK = Algorithms.createBitMask(0, HALF_IP);

    public static void main(String[] args) {

        long initialValue = 1L;

        final int HALF_SIZE = G.size() / 2;

        // Бітова маска довжиною половини розміру вектора G (28 біт)
        final long HALF_SIZE_MASK = Algorithms.createBitMask(0, HALF_SIZE);

        // Перестановлене число відносно вектора G
        final Long shuffledNum = Algorithms.permutate(initialValue, G, 64, true, true);


        final List<Long> keys = new ArrayList<>();

        // Поточні 32 бітні 'половинки'
        long C_i =  ((shuffledNum >> HALF_SIZE) & HALF_SIZE_MASK);
        long D_i =  (shuffledNum & HALF_SIZE_MASK);

        for (int i = 0; i < FEISTEL_ROUNDS; i++) {

            final int currentShift = keyShifts.get(i);

            // Застосування попередньо розробленого алгоритму для зсуву вліво половинок на n позицій без втрати бітів
            C_i = Algorithms.safeLeftShift(C_i, currentShift,  HALF_SIZE_MASK, HALF_SIZE);
            D_i = Algorithms.safeLeftShift(D_i, currentShift,  HALF_SIZE_MASK, HALF_SIZE);

            // Об'єднання половинок у 64 бітне число
            final long combined = (C_i << HALF_SIZE) |  D_i;

            // Застосування вектора H для перестановки бітів у сконкатенованому числі
            final long K_i_h = Algorithms.permutate(combined, vectorH, HALF_SIZE * 2, true, true);

            // Додаємо ключ у список
            keys.add( K_i_h);
        }

        long M = 0b0000000100100011010001010110011110001001101010111100110111101111L;

        long T_0 = Algorithms.permutate(M, IP, 64, true, true);

        long L = ( (T_0 >> HALF_IP) & HALF_IP_MASK );
        long R = ( T_0 & HALF_IP_MASK );

        // Цикл шифрування заміною
        for (int i = 0; i < FEISTEL_ROUNDS; i++) {
            final long T = L ^ desFunction(R, keys.get(i));
            L = R;
            R = T;
        }

        // Об'єднання правої  і лівої частини у 64 бітне число
        long combined = ( R << HALF_IP) | L;

        long finalPermutation = Algorithms.permutateInverse(combined, IP, 64, true);

        //decrypt

        long T_1 = Algorithms.permutate(finalPermutation, IP, 64, true, true);

        long L1 = ( (T_1 >> HALF_IP) & HALF_IP_MASK );
        long R1 = ( T_1 & HALF_IP_MASK );

        // Цикл шифрування заміною
        for (int i = FEISTEL_ROUNDS - 1; i >= 0; i--) {
            final long T = L1 ^ desFunction(R1, keys.get(i));
            L1 = R1;
            R1 = T;
        }

        // Об'єднання правої  і лівої частини у 64 бітне число
        long combined2 = ( R1 << HALF_IP) | L1;

        long finalPermutation2 = Algorithms.permutateInverse(combined2, IP, 64, true);

        System.out.println("M");
        System.out.println(Long.toBinaryString(M));

        System.out.println("Crypted");
        System.out.println(Long.toBinaryString(finalPermutation));

        System.out.println("Decrypt");
        System.out.println(Long.toBinaryString(finalPermutation2));

    }

    private static long desFunction(long R, Long K) {

        // Кількість блоків
        final int NUM_BLOCKS = 8;

        // Обчислене розширене Е XOR K
        long extendedR = Algorithms.permutate(R, vectorE, 64 / 2, true, true);

        extendedR =  extendedR ^ K;

        // Розмір одного блоку
        final int BLOCK_SIZE = vectorE.size() / NUM_BLOCKS;

        // Маска розміром одного блоку (6 бітів)
        final long BLOCK_MASK = Algorithms.createBitMask(0, BLOCK_SIZE);

        // Масив блоків
        final byte[] blocks = new byte[NUM_BLOCKS];

        for (int i = 0; i < NUM_BLOCKS; i++) {

            // Отримання кожних 6 бітів
            final long b = ((extendedR >> (i * BLOCK_SIZE)) & BLOCK_MASK);

            // Обчислення номеру рядку і стовпця
            final long rowNum =    (Algorithms.getBit(b, 5) << 1) |  (Algorithms.getBit(b, 0));

            final long columnNum =  (Algorithms.getBit(b, 4) << 3) |
                    (Algorithms.getBit(b, 3) << 2) |
                    (Algorithms.getBit(b, 2) << 1) |
                    (Algorithms.getBit(b, 1));

            // Отримання значення з тензору S відносно порядкового номеру блока
            // і розрахоаного рядка і стовпця вище
            blocks[i] = (byte) (tensorS[NUM_BLOCKS - i - 1][(int) rowNum][(int) columnNum]);
        }

        // Чотирьох бітова маска
        final long FOUR_BIT_MASK = Algorithms.createBitMask(0, 4);

        // 'Конкатенація' блоків у одне 32 бітне значення
        int res = 0;
        for (int i = 0; i < NUM_BLOCKS; i++) {
            res |= (int)(blocks[i] & FOUR_BIT_MASK) << (i * 4);
        }
        res &= Algorithms.createBitMask(0, 32);

        // Перестановка бітів відносно вектора P
        return Algorithms.permutate(res, vectorP, 64 / 2, true, true);
    }


}
