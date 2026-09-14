import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PaymentGateway gateway = new PaymentGateway();

        gateway.add(new GCashPayment(
                1001,
                "Ana",
                1500.00,
                "0917-555-0134"
        ));

        gateway.add(new MayaPayment(
                1002,
                "Jerome",
                899.50,
                "jerome@liceo.edu.ph"
        ));

        gateway.add(new CashPayment(
                1003,
                "Liza",
                250.00
        ));

        int choice;

        do {
            System.out.println();
            System.out.println("================================");
            System.out.println("          LICEO PAY");
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

                    int type;

                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input.");
                        scanner.next();
                        System.out.print("Choose payment method: ");
                    }

                    type = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter payment ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter payer name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    if (type == 1) {
                        System.out.print("Enter mobile number: ");
                        String mobile = scanner.nextLine();

                        Payment payment =
                                new GCashPayment(id, name, amount, mobile);

                        gateway.add(payment);

                        System.out.println();
                        payment.printReceipt();
                        payment.printThankYou();

                    } else if (type == 2) {
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();

                        Payment payment =
                                new MayaPayment(id, name, amount, email);

                        gateway.add(payment);

                        System.out.println();
                        payment.printReceipt();
                        payment.printThankYou();

                    } else if (type == 3) {

                        Payment payment =
                                new CashPayment(id, name, amount);

                        gateway.add(payment);

                        System.out.println();
                        payment.printReceipt();
                        payment.printThankYou();

                    } else {
                        System.out.println("Invalid payment method.");
                    }
                    break;

                case 2:
                    System.out.println();
                    System.out.println("All Payment Receipts:");
                    gateway.processAll();
                    break;

                case 3:
                    System.out.println();
                    System.out.print("Enter payment ID to find: ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid ID.");
                        scanner.next();
                        System.out.print("Enter payment ID to find: ");
                    }

                    int searchId = scanner.nextInt();
                    scanner.nextLine();

                    Payment found = gateway.findById(searchId);

                    if (found != null) {
                        System.out.println("Payment found:");
                        System.out.printf(
                                "[%d] %s - %s - PHP %.2f%n",
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
                    System.out.println("Thank you for using LICEO PAY.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
