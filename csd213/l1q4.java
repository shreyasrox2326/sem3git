import java.util.Scanner;

public class l1q4 {
    public static void main(String[] args) {
        Scanner scn = new Scanner (System.in);
        System.out.println("Enter the number of numbers:");
        int inputNum = scn.nextInt();
        scn.close();
        boolean flagPrinted = false;
        for (int i=1; i<=inputNum; i++) {
            flagPrinted = false;
            if (i%3 == 0) {
                System.out.print("Fizz");
                flagPrinted = true;
            }
            if (i%5 == 0) {
                System.out.print("Buzz");
                flagPrinted = true;
            }
            if (!flagPrinted)
            System.out.print(i);
            System.out.print("\n");
        }
    }
}
