import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println(">> Book added successfully.");
    }

    public void listBooks() {

        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }

        System.out.println("\n--- Library Catalog ---");

        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i).describe());
        }
    }

    public void borrowBook(String title) {

        for (Book book : books) {

            if (book.getTitle().equalsIgnoreCase(title)) {

                if (!book.isBorrowed()) {
                    book.borrowBook();
                    System.out.println(">> You borrowed \"" + book.getTitle() + "\".");
                } else {
                    System.out.println(">> Book is already borrowed.");
                }
                return;
            }
        }

        System.out.println(">> Book not found.");
    }

    public void returnBook(String title) {

        for (Book book : books) {

            if (book.getTitle().equalsIgnoreCase(title)) {

                if (book.isBorrowed()) {
                    book.returnBook();
                    System.out.println(">> Book returned successfully.");
                } else {
                    System.out.println(">> Book was not borrowed.");
                }
                return;
            }
        }

        System.out.println(">> Book not found.");
    }

    public void searchBook(String title) {

        for (Book book : books) {

            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println(">> Book found:");
                System.out.println(book.describe());
                return;
            }
        }

        System.out.println(">> Book not found.");
    }
}