import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private int id;
    private static int memberCount = 0;
    private List<Book> borrowedBooks;

    public Member(String name) {
        this.name = name;
        this.id = ++memberCount;
        borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }

    public void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
