import java.util.Scanner;
public class l1q8 {
    public static double [] insertionSort(double arr[])
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
        return arr;
    }

    public static int binSearch (double arr [], double element)
    {
        int Lpointer = 0;
        int Rpointer = arr.length;
        int mid = (Lpointer + Rpointer) / 2;

        while (Lpointer != Rpointer){
            mid = (Lpointer + Rpointer) / 2;
            if (arr [mid] == element) {
                return mid;
            }
            else if (element < arr [mid]){
                Rpointer = mid;
            }
            else if (element > arr [mid]) {
                Lpointer = mid;
            }
        }
        return -1;
    }
    public static boolean isSorted (double arr[]) {
        for (int index = 0; index < arr.length - 1; index++) {
            if (arr [index] > arr [index + 1])
            return false;
        }
        return true;
    }
    public static void main (String args[]) {
        Scanner scn = new Scanner (System.in);
        System.out.println(" Enter number of elements in the array:");
        int number = scn.nextInt();
        double array [] = new double [number];
        for (int i=0; i < number; i++){
            System.out.print("num" + (i+1) + ":  ");
            array [i] = scn.nextDouble();
        }

        if (!isSorted(array)) {
            System.out.println("Not sorted");
            array = insertionSort(array);
        }
        else 
        System.out.println("Sorted");
        for (int i = 0; i < array.length; i++)
        System.out.print(array [i] + "  ");
        System.out.print("\n");
        
        
        System.out.print("Enter element to be searched for:  ");
        int searchElement = scn.nextInt();
        scn.close();
        int index = binSearch(array, searchElement);
        if (index != -1)
        System.out.println("The element is at index " + index);
        else
        System.err.println("The element is not in the array");
    }
}
