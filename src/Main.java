import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PaymentGateway gateway = new PaymentGateway();

        int choice;

        do {
            System.out.println();
            System.out.println("================================");
            System.out.println("           LICEO PAY");
            System.out.println("================================");
            System.out.println("1. Make Payment");
            System.out.println("2. Show All Receipts");
            System.out.println("3. Find Payment");
            System.out.println("4. Show Total Collected");
            System.out.println("5. Refund Refundable Payments");
            System.out.println("6. Show Service Fees");
            System.out.println("0. Exit");
            System.out.println("================================");
            System.out.print("Enter choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                System.out.print("Enter choice: ");
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.println();
                    System.out.println("Make Payment");
                    System.out.println("1. GCash");
                    System.out.println("2. Maya");
                    System.out.println("3. Cash");
                    System.out.print("Choose payment method: ");

                    int type = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter payment ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter payer name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    Payment payment;

                    if (type == 1) {
                        System.out.print("Enter mobile number: ");
                        String mobile = scanner.nextLine();

                        payment = new GCashPayment(
                                id, name, amount, mobile
                        );

                    } else if (type == 2) {
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();

                        payment = new MayaPayment(
                                id, name, amount, email
                        );

                    } else if (type == 3) {
                        payment = new CashPayment(
                                id, name, amount
                        );

                    } else {
                        System.out.println("Invalid payment method.");
                        break;
                    }

                    gateway.add(payment);

                    System.out.println();
                    payment.printReceipt();
                    payment.printThankYou();

                    break;

                case 2:
                    System.out.println();
                    System.out.println("All Payment Receipts:");
                    gateway.processAll();
                    break;

                case 3:
                    System.out.println();
                    System.out.print("Enter payment ID: ");

                    int searchId = scanner.nextInt();

                    Payment found = gateway.findById(searchId);

                    if (found != null) {
                        System.out.println("Payment found:");
                        System.out.printf(
                                "[%d] %-6s %-10s PHP %10.2f%n",
                                found.getId(),
                                found.provider(),
                                found.getPayerName(),
                                found.getAmount()
                        );
                    } else {
                        System.out.println("Payment not found.");
                    }

                    break;

                case 4:
                    System.out.println();
                    System.out.printf(
                            "Total collected: PHP %.2f%n",
                            gateway.totalCollected()
                    );
                    break;

                case 5:
                    System.out.println();
                    System.out.println(
                            "Refunding every payment that can be refunded:"
                    );
                    gateway.refundAll();
                    break;

                case 6:
                    System.out.println();
                    System.out.println(
                            "Service fees (the two serviceFee methods):"
                    );
                    gateway.showServiceFees();
                    break;

                case 0:
                    System.out.println();
                    System.out.println("Thank you for using LICEO PAY.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
