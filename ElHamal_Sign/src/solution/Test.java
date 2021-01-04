package solution;

public class Test {

    private static int calcB(int x, int a, int k, int p, int m) {
        final int r = m % (p - 1);
        for (int i = 1; ; i++) {
            int o = (a * x + k * i) % (p - 1);
            if (o == r) {
                return i;
            }
        }
    }



    public static void main(String[] args) {

        int x = 17987;
        int a = 3642;
        int k = 17987;
        int p = 34781;
        int m = 1423;

        System.out.println(calcB(x, a, k, p, m));
    }

}
