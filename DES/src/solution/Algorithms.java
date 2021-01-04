package solution;

import java.util.*;

public class Algorithms {

    /**
     * Функція для випадкової перестановки об'єктів
     * @param objects вхідний масив
     * @param <T> Тип даних
     */
    public static <T> List<T> shuffle(List<T> objects) {
        final int N = objects.size();
        final List<T> shuffledList = new ArrayList<>();
        final Set<Integer> ownedIndexes = new HashSet<>();
        final Random r = new Random();
        for (int i = 0; i < N; i++) {
            int newIndex = Math.abs(r.nextInt()) % N;
            while (ownedIndexes.contains(newIndex)) {
                newIndex = Math.abs(r.nextInt()) % N;
            }
            ownedIndexes.add(newIndex);
            shuffledList.add(objects.get(newIndex));
        }
        return shuffledList;
    }

    /**
     * @param num  Вхідне 64 бітне число
     * @param position Індекс
     * @param value Значення (0 або 1)
     * @return Видозмінене значення
     */
    public static Long setBit(Long num, int position, long value) {
        if (value == 0) {
            return num & ~((long) 1 << position);
        }
        return num | ((long) 1 << position);
    }

    /**
     * Функція для отримання значення біту у позиції position
     * @param position Позиція (індекс)
     */
    public static Long getBit(Long val, int position) {
        return (  ( val >> position) & 1);
    }

    /**
     * Генерація вектора, що містить послідовність значень від @from до @to
     * Решта елементів додаються довільним чином
     * @param length Довжина вихідного вектора
     * @param from Початкове значення
     * @param to Кінцеве значення
     */
    public static List<Integer> generateVectorAndFitToLengthByRandom(int length, int from, int to) {
        final int m = to - from;
        final List<Integer> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int v = from + (to - from + i - 1 ) % m ;
            result.add(v);
        }
        return result;
    }

    /**
     * Функція для перетворення вектора у масив
     * @param inputVector Вхідний вектор чисел
     */
    public static int[] convertVectorToArray(List<Integer> inputVector) {
        final int N = inputVector.size();
        final int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            res[i] = inputVector.get(i);
        }
        return res;
    }

    /**
     * Функція для генерування випадкового тензора
     */
    public static int[][][] generateRandomTensor(int r, int m, List<Integer> vector) {
        final int N = vector.size();
        final int[][][] resultTensor = new int[r][m][N];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < m; j++) {
                final List<Integer> shuffledVector = shuffle(vector);
                resultTensor[i][j] = convertVectorToArray(shuffledVector);
            }
        }

        return resultTensor;
    }

    /**
     * Функція для зменшення розміру вектора довільним чином
     * @param inputList Вхідний вектор
     * @param size Бажаний розмір
     */
    public static List<Integer> trimToSize(List<Integer> inputList, int size) {
        final int listSize = inputList.size();
        final Set<Integer> uniqueVals = new HashSet<>();
        final Random r = new Random();
        while (uniqueVals.size() != size) {
            Integer v = inputList.get(Math.abs(r.nextInt()) % listSize);
            uniqueVals.add(v);
        }
        return new ArrayList<>(uniqueVals);
    }

    /**
     * Створення бітової маски to < from
     * @param from Початковий індекс
     * @param to Кінцевий індекс
     */
    public static Long createBitMask(int from, int to) {
        long mask = 0L;
        for (int i = from; i < to; i++) {
            mask |= 1L << i;
        }
        return mask;
    }

    /**
     * Зсув вліво без втрат
     * @param initialNum Початкове число
     * @param rounds Кількість зсувів вліво
     * @param mask Бітова маска
     */
    public static long safeLeftShift(final long initialNum, int rounds, long mask, int bitNum) {
        final int neededRounds = rounds % bitNum;
        long res = (initialNum << rounds) & mask;
        for (int i = bitNum - neededRounds; i < bitNum; i++) {
            long bitValue = getBit(initialNum, i);
            res = setBit(res, i - bitNum +  neededRounds , bitValue);
        }
        return res;
    }


    /**
     * Функція для здійснення перестановки бітів у деякому n розрядному числі
     * @param initialNum Початкове значення
     * @param shuffleList Вектор перестановок
     * @param bitsNum Розрядність числа
     * @param readReverse Булеве значення, що вказує чи проводити читання з кінця (true) чи з початку
     * @param writeReverse Булеве значення, що вказує чи проводити запис з кінця (true) чи з початку
     */
    public static long permutate(final long initialNum, List<Integer> shuffleList, int bitsNum, boolean readReverse, boolean writeReverse) {
        final int LIST_SIZE = shuffleList.size();
        long result = 0L;
        for (int i = 0; i < LIST_SIZE; i++) {
            int v = shuffleList.get(i);
            long bitValue = Algorithms.getBit(initialNum, readReverse ? bitsNum - v - 1 : v);
            result = Algorithms.setBit(result, writeReverse ? LIST_SIZE - i - 1 : i, bitValue);
        }
        return result;
    }

    /**
     * Функція для здійснення оберненої перестановки бітів у деякому n розрядному числі
     * @param initialNum Початкове значення
     * @param shuffleList Вектор перестановок
     * @param bitsNum Розрядність числа
     * @param readReverse Булеве значення, що вказує чи проводити читання з кінця (true) чи з початку
     */
    public static long permutateInverse(final long initialNum, List<Integer> shuffleList, int bitsNum, boolean readReverse) {
        final int LIST_SIZE = shuffleList.size();
        long result = 0L;
        for (int i = 0; i < LIST_SIZE; i++) {
            int v = shuffleList.get(i);
            long bitValue = Algorithms.getBit(initialNum, readReverse ? bitsNum - i - 1 : i);
            result = Algorithms.setBit(result, LIST_SIZE - v - 1, bitValue);
        }
        return result;
    }

}
