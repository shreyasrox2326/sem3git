import java.util.Scanner;
public class l1q7 {
    void insertionSort(double arr[])
    {
        for (int i = 1; i < arr.length; i++) {
            double curIndex = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > curIndex) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = curIndex;
        }
    }

    public static void main(String args[])
    {
        double array [] = new double [10];
        Scanner scn = new Scanner (System.in);
        System.out.println("Enter 10 numbers: ");
        for (int i=0; i < 10; i++){
            System.out.print("num" + (i+1) + ":  ");
            array [i] = scn.nextDouble();
        }
        scn.close();

        l1q7 classobj = new l1q7();
        classobj.insertionSort(array);
        System.out.println("\nSorted array: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array [i] + "  ");
        }
        System.err.println("");
    }
}
