package solution;

public class CoprimeNumberDemo {

    public static void main(String[] args) {
        int a = Algorithms.findRandomCoprimeNumberBetween(4);

        System.out.println(a);

        System.out.println(Algorithms.euclidGCD(4, 2));
        System.out.println(Algorithms.euclidGCD(3, 2));
    }

}
