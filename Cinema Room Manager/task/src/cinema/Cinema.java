package cinema;

import java.util.Scanner;

public class Cinema {

    static Scanner scanner = new Scanner(System.in);
    static int rows = 0;
    static int seats = 0;
    static int rowSelected = 0;
    static int seatSelected = 0;
    static int totalSeats = 0;
    static String[][] seatArray;
    static int purchasedTickets = 0;
    static double purchaseInPercentage = 0;
    static int currentIncome = 0;
    static int totalIncome = 360;

    public static void main(String[] args) {
        // Write your code here
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        totalSeats = rows * seats;

        //Show cinema places' diagram
        seatArray = new String[rows][seats];
        for (int i = 0; i < seatArray.length; i++) { //vertical left-side number rows
            for (int j = 0; j < seatArray[0].length; j++) { //cinemas' seats
                seatArray[i][j] = "S";
            }
        }
        cinemaMenu();
    }

    private static void cinemaMenu() {
        while (true) {
            System.out.println("""
                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit
                    """);
            int choice = scanner.nextInt();

            switch (choice) {

                case 0:
                    System.exit(0);
                    break;
                case 1:
                    showCinePlaces(seatArray);
                    break;
                case 2:
                    calculateTicketPrice();
                    break;
                case 3:
                    getStatistics();
                    break;
                default:
                    if (choice < 0 || choice > 3) {
                        System.out.println("Elija una opcion correcta");
                        break;
                    }
            }
        }
    }

    private static void getStatistics() {
        System.out.printf("""
                Number of purchased tickets: %d
                Percentage: %.2f%%
                Current income: $%d
                Total income: $%d
                
                """, purchasedTickets, purchaseInPercentage, currentIncome, totalIncome);
    }

    private static void calculateTicketPrice() {
        // Choose row and seat, get location and price
        System.out.println("Enter a row number:");
        rowSelected = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        seatSelected = scanner.nextInt();

        if(rowSelected>rows || seatSelected>seats){
            System.out.println("Wrong input!");
            return;
        }
        String seat = seatArray[rowSelected-1][seatSelected-1];
        if(seat.equals("B")){
            System.out.println("That ticket has already been purchased!");
            return;
        }else {
            seatArray[rowSelected - 1][seatSelected - 1] = "B";
        }

        int ticketPrice = 10;
        int frontHalf = (rows / 2);

        if (totalSeats < 60) {
            System.out.printf("\nTicket price: $%d", ticketPrice);
        } else if (rowSelected <= frontHalf) {
            System.out.printf("\nTicket price: $%d", ticketPrice);
        } else {
            ticketPrice = 8;
            System.out.printf("\nTicket price: $%d", ticketPrice);
        }
        purchasedTickets++;
        currentIncome += ticketPrice;
        purchaseInPercentage = calculateTicketsSellPercentage(currentIncome, totalIncome);
        System.out.println();
    }

    private static double calculateTicketsSellPercentage(int currentIncome, int totalIncome){
       return (double) (currentIncome * 100) /totalIncome;
    }

    private static void showCinePlaces(String[][] seatArray) {
        System.out.println("\nCinema:"); //title
        System.out.print("  ");
        for (int j = 1; j <= seats; j++) { //head number seats
            System.out.print(j + " ");
        }
        System.out.println();
        for (int i = 0; i < seatArray.length; i++) { //vertical left-side number rows
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seatArray[0].length; j++) { //cinemas' seats
                System.out.print(seatArray[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

