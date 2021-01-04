package solution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneralTest {
    private static final int A = 5;
    private static final int C = 3;
    private static final int M = 128;
    private static final int T_0 = 1;

    private CipherPseudoRandom cipherObj;
    private DecipherPseudoRandom decipherObj;

    @BeforeEach
    public void setup() {
        SequenceGenerator<Integer> generator = new LinearCongruentSequenceGenerator(A, C, M, T_0);
        cipherObj = new CipherPseudoRandom(generator);
        decipherObj = new DecipherPseudoRandom(generator);
    }

    @Test
    public void testCorrectnessAscii() {
        final String initialText = "Some text to check correctness";
        String encodedText = cipherObj.cipher(initialText);
        assertEquals(initialText, decipherObj.decipher(encodedText));
    }

    @Test
    public void testCorrectnessUkrainianEncoding() {
        final String initialText = "Деякий текст для перевірки алгоритму";
        String encodedText = cipherObj.cipher(initialText);
        assertEquals(initialText, decipherObj.decipher(encodedText));
    }

}
