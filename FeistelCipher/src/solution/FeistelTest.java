package solution;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeistelTest {
    private static Feistel feistel;

    @BeforeClass
    public static void setUp() {
        feistel = new Feistel(10, (R, K) -> R << K); // Число раундів і твірна функція
    }

    @Test
    public void testCorrectnessOddLengthOfText() {
        String text = "Hello world";
        assertEquals(text.length() % 2, 1);

        Feistel.Result cipherResult = feistel.cipherText(text);

        assertEquals(cipherResult.getLenWasOdd(), true);
        assertEquals(feistel.decipherText(cipherResult), text);
    }

    @Test
    public void testCorrectnessEvenLengthOfText() {
        String text = "SomeText";
        assertEquals(text.length() % 2, 0);

        Feistel.Result cipherResult = feistel.cipherText(text);

        assertEquals(cipherResult.getLenWasOdd(), false);
        assertEquals(feistel.decipherText(cipherResult), text);
    }

}
