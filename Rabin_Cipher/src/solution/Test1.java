package solution;

import java.util.Set;

public class Test1 {
    private Set<Character> CACHED = new HashSet<>();

    public void printOnes(char c) {
        if (CACHED.add(c)) {
            System.out.println(c);
        }
    }
}
