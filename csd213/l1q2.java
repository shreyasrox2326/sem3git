import java.util.Scanner;

public class l1q2 
{
    public static void main(String[] args) 
    {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter the number of numbers:");
        int n = scn.nextInt();
        System.out.println("Enter the numbers, press enter after each number:");
        int input=0;
        int temp=0;
        for (int i=0;i<n;i++)
        {
            if (i==0)
            input = scn.nextInt();
            else 
            {
                temp=scn.nextInt();
                if (temp>input)
                input=temp;
            }
        }
        scn.close();
        System.out.println("Max number is: "+input);

    }
}
