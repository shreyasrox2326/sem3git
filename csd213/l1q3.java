import java.util.Scanner;

public class l1q3 {
    
    public static boolean isPrime (int number)
    {
        int count = 0;
        for (int i=1; i<number; i++)
        {
            if (number%i==0)
            count += 1;

        }
        if (count==1)
        return(true);
        else
        return(false);
    }
    public static void main(String[] args)
    {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter the number of numbers:");
        int n = scn.nextInt();
        int[] primeArray;
        primeArray = new int[n];
        int input=0;
        for (int i=0;i<n;i++)
        {
            input = scn.nextInt();
            if (isPrime(input))
            primeArray[i]=input;
        }
        scn.close();
        for (int i : primeArray) {
            if (i!=0)
            System.out.print(i+" ");
        }
    }

}
