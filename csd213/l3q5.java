import java.util.Scanner;

public class l3q5 {
    
    public static void main(String[] args) {
        // Initialize seating chart: 10 seats, all initially empty (false)
        boolean[] seats = new boolean[10];
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            // Display options
            System.out.println("Please type 1 for First Class, 2 for Economy, or q to quit.");
            String input = scanner.next().toLowerCase();
            
            if (input.equals("q")) {
                // Exit the loop and terminate the program
                System.out.println("Exiting...");
                break;
            }
            
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please type 1, 2, or q.");
                continue;
            }
            
            if (choice == 1) {
                // Handle First Class section (seats 1–5)
                boolean seatAssigned = assignSeat(seats, 0, 5, "First Class");
                if (!seatAssigned) {
                    // If First Class is full, ask if they want to sit in Economy
                    System.out.println("First Class is full. Would you like to be placed in Economy? (yes/no)");
                    String response = scanner.next().toLowerCase();
                    if (response.equals("yes")) {
                        boolean economySeatAssigned = assignSeat(seats, 5, 10, "Economy");
                        if (!economySeatAssigned) {
                            System.out.println("Economy is also full. Next flight leaves in 3 hours.");
                        }
                    } else {
                        System.out.println("Next flight leaves in 3 hours.");
                    }
                }
            } else if (choice == 2) {
                // Handle Economy section (seats 6–10)
                boolean seatAssigned = assignSeat(seats, 5, 10, "Economy");
                if (!seatAssigned) {
                    // If Economy is full, ask if they want to sit in First Class
                    System.out.println("Economy is full. Would you like to be placed in First Class? (yes/no)");
                    String response = scanner.next().toLowerCase();
                    if (response.equals("yes")) {
                        boolean firstClassSeatAssigned = assignSeat(seats, 0, 5, "First Class");
                        if (!firstClassSeatAssigned) {
                            System.out.println("First Class is also full. Next flight leaves in 3 hours.");
                        }
                    } else {
                        System.out.println("Next flight leaves in 3 hours.");
                    }
                }
            } else {
                System.out.println("Invalid choice. Please type 1, 2, or q.");
            }
        }
        
        scanner.close(); // Close the scanner
    }
    
    // Method to assign a seat in a specified section
    public static boolean assignSeat(boolean[] seats, int start, int end, String section) {
        for (int i = start; i < end; i++) {
            if (!seats[i]) { // Seat is available
                seats[i] = true; // Assign the seat
                System.out.printf("Boarding Pass: Seat %d (%s)%n", i + 1, section);
                return true;
            }
        }
        return false; // All seats in this section are taken
    }
}
