import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        final int MAX = 10;

        int[] studentID = new int[MAX];
        String[] fullName = new String[MAX];
        int[] age = new int[MAX];
        String[] course = new String[MAX];
        double[] grade = new double[MAX];
        boolean[] enrolled = new boolean[MAX];

        int studentCount = 0;
        int choice;

        do {

            System.out.println("\n===== STUDENT INFORMATION SYSTEM =====");
            System.out.println("[1] Add Student");
            System.out.println("[2] View All Students");
            System.out.println("[3] Search Student by ID");
            System.out.println("[4] View Statistics");
            System.out.println("[5] Exit");
            System.out.print("Enter choice: ");
            choice = input.nextInt();

            switch (choice) {

                case 1:

                    if (studentCount == MAX) {
                        System.out.println("Student list is already full.");
                        break;
                    }

                    System.out.print("Enter Student ID: ");
                    studentID[studentCount] = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter Full Name: ");
                    fullName[studentCount] = input.nextLine();

                    do {
                        System.out.print("Enter Age: ");
                        age[studentCount] = input.nextInt();

                        if (age[studentCount] <= 0) {
                            System.out.println("Age must be positive.");
                        }

                    } while (age[studentCount] <= 0);

                    input.nextLine();

                    System.out.print("Enter Course: ");
                    course[studentCount] = input.nextLine();

                    do {
                        System.out.print("Enter Grade (0-100): ");
                        grade[studentCount] = input.nextDouble();

                        if (grade[studentCount] < 0 || grade[studentCount] > 100) {
                            System.out.println("Grade must be between 0 and 100.");
                        }

                    } while (grade[studentCount] < 0 || grade[studentCount] > 100);

                    System.out.print("Is Enrolled? (true/false): ");
                    enrolled[studentCount] = input.nextBoolean();

                    studentCount++;

                    System.out.println("Student added successfully!");
                    break;

                case 2:

                    if (studentCount == 0) {
                        System.out.println("No student records found.");
                    } else {

                        System.out.println("\n===== STUDENT LIST =====");

                        for (int i = 0; i < studentCount; i++) {

                            String standing;

                            if (grade[i] >= 90) {
                                standing = "Dean's Lister";
                            } else if (grade[i] >= 75) {
                                standing = "Passed";
                            } else {
                                standing = "Failed";
                            }

                            System.out.println("----------------------------");
                            System.out.println("Student ID : " + studentID[i]);
                            System.out.println("Name       : " + fullName[i]);
                            System.out.println("Age        : " + age[i]);
                            System.out.println("Course     : " + course[i]);
                            System.out.println("Grade      : " + grade[i]);
                            System.out.println("Standing   : " + standing);
                            System.out.println("Enrolled   : " + enrolled[i]);
                        }
                    }

                    break;

                case 3:

                    if (studentCount == 0) {
                        System.out.println("No student records found.");
                        break;
                    }

                    System.out.print("Enter Student ID to search: ");
                    int searchID = input.nextInt();

                    boolean found = false;

                    for (int i = 0; i < studentCount; i++) {

                        if (studentID[i] == searchID) {

                            String standing;

                            if (grade[i] >= 90) {
                                standing = "Dean's Lister";
                            } else if (grade[i] >= 75) {
                                standing = "Passed";
                            } else {
                                standing = "Failed";
                            }

                            System.out.println("\nStudent Found");
                            System.out.println("ID         : " + studentID[i]);
                            System.out.println("Name       : " + fullName[i]);
                            System.out.println("Age        : " + age[i]);
                            System.out.println("Course     : " + course[i]);
                            System.out.println("Grade      : " + grade[i]);
                            System.out.println("Standing   : " + standing);
                            System.out.println("Enrolled   : " + enrolled[i]);

                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Student not found.");
                    }

                    break;

                case 4:

                    if (studentCount == 0) {
                        System.out.println("No student records found.");
                        break;
                    }

                    double total = 0;
                    double highest = grade[0];
                    String topStudent = fullName[0];

                    for (int i = 0; i < studentCount; i++) {

                        total += grade[i];

                        if (grade[i] > highest) {
                            highest = grade[i];
                            topStudent = fullName[i];
                        }
                    }

                    double average = total / studentCount;

                    System.out.println("\n===== CLASS STATISTICS =====");
                    System.out.println("Total Students : " + studentCount);
                    System.out.printf("Average Grade  : %.2f%n", average);
                    System.out.println("Top Student    : " + topStudent);
                    System.out.println("Highest Grade  : " + highest);

                    break;

                case 5:
                    System.out.println("Thank you for using the Student Information System.");
                    break;

                default:
                    System.out.println("Invalid choice.");

            }

        } while (choice != 5);

        input.close();
    }
}
