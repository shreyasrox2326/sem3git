import java.util.Scanner;
public class l1q5 {
    public static void main(String[] args) {
        Scanner scn = new Scanner (System.in);
        System.out.print("Enter ROI: ");
        double rateOfInterest = scn.nextDouble();
        System.out.print("Enter duration in years: ");
        double numYears = scn.nextDouble();
        System.out.print("Enter principal amount: ");
        double principal = scn.nextDouble();
        scn.close();
        double monthlyPayment = (principal * (rateOfInterest / 100))/(1-(1/(Math.pow((1 + (rateOfInterest / 100)),(numYears*12)))));
        monthlyPayment = (monthlyPayment * 100 - ((monthlyPayment * 100) % 1)) / 100;
        System.out.println("Monthly payment = ₹" + monthlyPayment);
    }
}
