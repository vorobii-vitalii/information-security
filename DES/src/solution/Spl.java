package solution;

import java.util.List;

public class Spl {

    public static void main(String[] args) {

        String inputText = "13  2   8  4   6 15  11  1  10  9   3 14   5  0  12  7\n" +
                "      1 15  13  8  10  3   7  4  12  5   6 11   0 14   9  2\n" +
                "      7 11   4  1   9 12  14  2   0  6  10 13  15  3   5  8\n" +
                "      2  1  14  7   4 10   8 13  15 12   9  0   3  5   6 11";
        inputText = inputText.trim();

        String[] lines = inputText.split("\n");

        for (String s : lines) {
            String[] splitted = s.trim().split("\\s+");

            String res = String.join(", ", splitted);

            System.out.println("{ " + res + " }");
        }



    }

}
