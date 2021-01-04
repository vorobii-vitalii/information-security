package solution;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

public class MapReaderText {

    public static void main(String[] args) throws FileNotFoundException {
        CharHashMapReader reader = new CharHashMapReader("4_map.txt");
        Map<Character, Character> map = reader.retrieveMap();
        Set<Map.Entry<Character, Character>> set = map.entrySet();
        for (Map.Entry<Character, Character> entry : set) {
            System.out.printf("%c -> %c %n%n", entry.getKey(), entry.getValue());
        }
    }

}
