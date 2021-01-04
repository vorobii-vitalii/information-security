package solution;

import java.util.HashMap;
import java.util.Map;

public class Encoder {
    private final Map<Character, Character> charMap;
    private Map<Character, Character> reversedCharMap;

    public Encoder(Map<Character, Character> charMap) {
        this.charMap = charMap;
        initReversedCharMap();
    }

    private void initReversedCharMap() {
        Map<Character, Character> reversedCharMap = new HashMap<>();
        for (Map.Entry<Character, Character> entry : charMap.entrySet()) {
            reversedCharMap.put(entry.getValue(), entry.getKey());
        }
        this.reversedCharMap = reversedCharMap;
    }

    public String cipherText(String inputText) {
        return getProcessedString(inputText, charMap);
    }

    public String decipherText(String inputText) {
        return getProcessedString(inputText, reversedCharMap);
    }

    private String getProcessedString(String inputText, Map<Character, Character> map) {
        final int N = inputText.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < N; i++) {
            char c = inputText.charAt(i);
            builder.append(map.getOrDefault(c, c));
        }
        return builder.toString();
    }

}
