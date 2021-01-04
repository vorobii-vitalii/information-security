package solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CharHashMapReader {
    private final String fileName;

    public CharHashMapReader(String fileName) {
        this.fileName = fileName;
    }

    public Map<Character, Character> retrieveMap() throws FileNotFoundException {
        Map<Character, Character> map = new HashMap<>();
        Scanner scanner = new Scanner(new FileReader(new File(fileName)));
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (!line.equals(""))
                map.put(line.charAt(0), line.charAt(2));
        }
        return map;
    }

}
