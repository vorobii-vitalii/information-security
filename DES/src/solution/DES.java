package solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.LongFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DES {

    /**
     * Число бітів блоку
     */
    private static final Integer NUM_OF_BITS = 64;

    /**
     * Кількість раундів Фестеля
     */
    private static final Integer FEISTEL_ROUNDS = 16;

    /**
     * Параметру вектора E
     */
    private static final Integer E_MIN = 0, E_MAX = 32, E_COUNT = 48;

    /**
     * Параметри тензора S
     */
    private static final Integer S_BLOCKS = 8, S_ROWS = 4, S_VECTOR_MIN = 0, S_VECTOR_MAX = 16;

    /**
     * Параметри вектора P
     */
    private static final Integer P_MIN = 0, P_MAX = 32;

    /**
     * Параметри вектора G
     */
    private static final Integer G_MIN = 0, G_MAX = 64;

    /**
     * Параметри вектора H
     */
    private static final Integer H_MIN = 0, H_MAX = 56, H_NEEDED = 48;

    /**
     * IP
     */
    private List<Integer> IP;

    /**
     * Вектор E
     */
    private List<Integer> E;

    /**
     * Тензор S
     */
    private int[][][] S;

    /**
     * Вектор P
     */
    private List<Integer> P;

    /**
     * Вектор G
     */
    private List<Integer> G;

    /**
     * Вектор H
     */
    private List<Integer> H;

    /**
     * Вектор зсувів для формування ключів
     */
    private List<Integer> keyShifts;

    /**
     * Вектор ключів
     */
    private List<Long> keys;

    /**
     * Половина розміру IP і відповідна маска
     */
    final int HALF_IP = NUM_OF_BITS / 2;
    final long HALF_IP_MASK = Algorithms.createBitMask(0, HALF_IP);


    public DES() {
        calcShuffleMaps();
        initVectorE();
        initTensorS();
        initVectorP();
        initVectorG();
        initVectorH();
        initKeysShifts();
        initKeys();
    }

    /**
     * Ініціалізація зсувів для ключів
     * Ініцілаізується вектор з 1 і 2 розмір якого рівний кількості раундів Фейстеля
     * Розподіл - нормальний (1 і 2 має однакову ймовірність бути обраною)
     */
    private void initKeysShifts() {
        final Random r = new Random();
        keyShifts = IntStream
                .range(1, FEISTEL_ROUNDS + 1)
                .boxed()
                .map(n -> r.nextInt(100) > 50 ? 1 : 2)
                .collect(Collectors.toList());
    }

    /**
     * Ініціалізація ключів
     */
    private void initKeys() {

        // Початкове значення ключа
        final long initialKey = Math.abs(new Random().nextLong());

        // Кількість ключів рівна кількості раундів Фейштеля
        keys = new ArrayList<>(FEISTEL_ROUNDS);

        final int HALF_SIZE = G.size() / 2;

        // Бітова маска довжиною половини розміру вектора G (28 біт)
        final long HALF_SIZE_MASK = Algorithms.createBitMask(0, HALF_SIZE);

        // Перестановлене число відносно вектора G
        final long shuffledNum = Algorithms.permutate(initialKey, G, NUM_OF_BITS, true, true);

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
            final long K_i_h = Algorithms.permutate(combined, H, HALF_SIZE * 2, true, true);

            // Додаємо ключ у список
            keys.add( K_i_h);
        }

    }

    /**
     * Ініціалізація вектора H
     * З вектора H довільно вилучаються 8 чисел
     */
    private void initVectorH() {
        final List<Integer> hVector = IntStream
                .range(H_MIN, H_MAX)
                .boxed()
                .collect(Collectors.toList());
        final List<Integer> trimmed = Algorithms.trimToSize(hVector, H_NEEDED);
        this.H = Algorithms.shuffle(trimmed);
    }

    /**
     * Ініціалізація вектора G
     */
    private void initVectorG() {
        final List<Integer> gVector = IntStream
                .range(G_MIN + 1, G_MAX + 1)
                .boxed()
                .filter(num -> num < 8 || num % 8 != 0) // Фільтр кратних чисел 8
                .map(num -> num - 1)
                .collect(Collectors.toList());
        this.G = Algorithms.shuffle(gVector);
    }

    /**
     * Ініціалізація P
     */
    private void initVectorP() {
        final List<Integer> rangeVector = IntStream
                .range(P_MIN, P_MAX)
                .boxed()
                .collect(Collectors.toList());
        P = Algorithms.shuffle(rangeVector);
    }

    /**
     * Ініціалізація S
     */
    private void initTensorS() {
        S = Algorithms.generateRandomTensor(
                S_BLOCKS,
                S_ROWS,
                IntStream
                    .range(S_VECTOR_MIN, S_VECTOR_MAX)
                    .boxed()
                    .collect(Collectors.toList())
        );
    }

    /**
     * Ініціалізація E
     */
    private void initVectorE() {
        E = Algorithms.generateVectorAndFitToLengthByRandom(E_COUNT, E_MIN, E_MAX);
    }

    /**
     * Розрахунок IP
     */
    private void calcShuffleMaps() {
        final List<Integer> inputRange = IntStream
                .range(0, NUM_OF_BITS)
                .boxed()
                .collect(Collectors.toList());
        IP = Algorithms.shuffle(inputRange);
    }

    /**
     * Твірна функція Фейстеля
     * @param K Поточний ключ
     */
    private long desFunction(long R, long K) {

        // Кількість блоків
        final int NUM_BLOCKS = 8;

        // Обчислене розширене Е XOR K
        long extendedR = Algorithms.permutate(R, E, NUM_OF_BITS / 2, true, true);

        extendedR =  extendedR ^ K;

        // Розмір одного блоку
        final int BLOCK_SIZE = E.size() / NUM_BLOCKS;

        // Маска розміром одного блоку (6 бітів)
        final long BLOCK_MASK = Algorithms.createBitMask(0, BLOCK_SIZE);

        // Масив блоків
        final byte[] blocks = new byte[NUM_BLOCKS];

        for (int i = 0; i < NUM_BLOCKS; i++) {

            // Отримання кожних 6 бітів
            final long b = ((extendedR >> (i * BLOCK_SIZE)) & BLOCK_MASK);

            // Обчислення номеру рядку і стовпця
            final long rowNum =     (Algorithms.getBit(b, 5) << 1) |  (Algorithms.getBit(b, 0));

            final long columnNum =  (Algorithms.getBit(b, 4) << 3) |
                                    (Algorithms.getBit(b, 3) << 2) |
                                    (Algorithms.getBit(b, 2) << 1) |
                                    (Algorithms.getBit(b, 1));

            // Отримання значення з тензору S відносно порядкового номеру блока
            // і розрахоаного рядка і стовпця вище
            blocks[i] = (byte) (S[NUM_BLOCKS - i - 1][(int) rowNum][(int) columnNum]);
        }

        // Чотирьох бітова маска
        final long FOUR_BIT_MASK = Algorithms.createBitMask(0, 4);

        // 'Конкатенація' блоків у одне 32 бітне значення
        int res = 0;
        for (int i = 0; i < NUM_BLOCKS; i++) {
            res |= (int)(blocks[i] & FOUR_BIT_MASK) << (i * 4);
        }
        res &= Algorithms.createBitMask(0, NUM_OF_BITS / 2);

        // Перестановка бітів відносно вектора P
        return Algorithms.permutate(res, P, 64 / 2, true, true);
    }

    /**
     * Функція шифрування
     * @param inputValue 8 байтне значення (64 біт)
     */
    public long cipher(Long inputValue) {

        // Перестановка вхідного числа відносно вектора IP
        long T_0 = Algorithms.permutate(inputValue, IP, NUM_OF_BITS, true, true);

        // Розкладення 64 бітного числа на два 32 бітних
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

        // Обернена перестановка вхідного числа відносно вектора IP
        return Algorithms.permutateInverse(combined, IP, NUM_OF_BITS, true);
    }

    /**
     * Функція дешифрування
     * @param inputValue 64 бітне значення
     */
    public long decipher(Long inputValue) {

        // Перестановка вхідного числа відносно вектора IP
        long recovered = Algorithms.permutate(inputValue, IP, NUM_OF_BITS, true, true);

        // Розкладення 64 бітного числа на два 32 бітних
        long R = ( (recovered >> HALF_IP) & HALF_IP_MASK );
        long L = ( recovered & HALF_IP_MASK );

        // Цикл шифрування заміною оберненим чином
        for (int i = FEISTEL_ROUNDS - 1; i >= 0; i--) {
            final long T = R ^ desFunction(L, keys.get(i));
            R = L;
            L = T;
        }

        // Об'єднання правої  і лівої частини у 64 бітне число
        long combined = ( L << HALF_IP) | R;

        // Обернена перестановка вхідного числа відносно вектора IP
        return Algorithms.permutateInverse(combined, IP, NUM_OF_BITS, true);
    }

    public List<Long> cipher(List<Long> inputValues) {
        return apply(inputValues, this::cipher);
    }

    public List<Long> decipher(List<Long> cipheredValues) {
        return apply(cipheredValues, this::decipher);
    }

    /**
     * Функція що приймає список чисел і поелементно застосовує їх до фукції
     * Повертає список значень функції
     * @param values Список значень
     * @param lambda Функція
     */
    private List<Long> apply(List<Long> values, LongFunction<Long> lambda) {
        return values
                .stream()
                .map(lambda::apply)
                .collect(Collectors.toList());
    }

}
