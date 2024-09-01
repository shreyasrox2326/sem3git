import java.util.Scanner;
import java.math.BigInteger;
public class l1q1 {  
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter x to find x!:");
        int InputNum = scn.nextInt();
        BigInteger factorial = BigInteger.valueOf(1);
        for (int i=InputNum;i>1;i--)
        {
          
          factorial=factorial.multiply(BigInteger.valueOf(i));
        }
        scn.close();
        System.out.println(InputNum+"! = "+factorial);
    }
}