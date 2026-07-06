import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int MAX = 10;
        int[] id = new int[MAX];
        String[] name = new String[MAX];
        int[] age = new int[MAX];
        String[] course = new String[MAX];
        double[] grade = new double[MAX];
        boolean[] enrolled = new boolean[MAX];

        int count = 0, choice;

        do {
            System.out.println("\n===== STUDENT INFORMATION SYSTEM =====");
            System.out.println("[1] Add Student");
            System.out.println("[2] View All Students");
            System.out.println("[3] Search Student by ID");
            System.out.println("[4] View Statistics");
            System.out.println("[5] Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    if (count == MAX) {
                        System.out.println("Student list is full!");
                        break;
                    }

                    System.out.print("ID: ");
                    id[count] = sc.nextInt();

                    sc.nextLine();
                    System.out.print("Name: ");
                    name[count] = sc.nextLine();

                    System.out.print("Age: ");
                    age[count] = sc.nextInt();

                    sc.nextLine();
                    System.out.print("Course: ");
                    course[count] = sc.nextLine();

                    System.out.print("Grade: ");
                    grade[count] = sc.nextDouble();

                    System.out.print("Enrolled (true/false): ");
                    enrolled[count] = sc.nextBoolean();

                    count++;
                    System.out.println("Student added!");
                    break;

                case 2:
                    if (count == 0) {
                        System.out.println("No records.");
                        break;
                    }

                    for (int i = 0; i < count; i++) {
                        String standing;

                        if (grade[i] >= 90)
                            standing = "Dean's Lister";
                        else if (grade[i] >= 75)
                            standing = "Passed";
                        else
                            standing = "Failed";

                        System.out.println("\nID: " + id[i]);
                        System.out.println("Name: " + name[i]);
                        System.out.println("Grade: " + grade[i]);
                        System.out.println("Standing: " + standing);
                    }
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    int search = sc.nextInt();
                    boolean found = false;

                    for (int i = 0; i < count; i++) {
                        if (id[i] == search) {
                            System.out.println("Name: " + name[i]);
                            System.out.println("Course: " + course[i]);
                            System.out.println("Grade: " + grade[i]);
                            found = true;
                        }
                    }

                    if (!found)
                        System.out.println("Student not found.");
                    break;

                case 4:
                    if (count == 0) {
                        System.out.println("No records.");
                        break;
                    }

                    double total = 0, topGrade = grade[0];
                    String topStudent = name[0];

                    for (int i = 0; i < count; i++) {
                        total += grade[i];

                        if (grade[i] > topGrade) {
                            topGrade = grade[i];
                            topStudent = name[i];
                        }
                    }

                    System.out.println("Total Students: " + count);
                    System.out.println("Average Grade: " + (total / count));
                    System.out.println("Top Student: " + topStudent + " (" + topGrade + ")");
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}
