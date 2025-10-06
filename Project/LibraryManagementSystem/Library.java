import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private Map<Member, List<Book>> borrowedRecords;
    private List<Book> availableBooks;

    public Map<Member, List<Book>> getBorrowedRecords() {
        return borrowedRecords;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.borrowedRecords = new HashMap<>();

        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "ISBN1001"));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", "ISBN1002"));
        books.add(new Book("1984", "George Orwell", "ISBN1003"));
        books.add(new Book("Pride and Prejudice", "Jane Austen", "ISBN1004"));
        books.add(new Book("Moby-Dick", "Herman Melville", "ISBN1005"));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", "ISBN1006"));
        books.add(new Book("Harry Potter and the Sorcerer’s Stone", "J.K. Rowling", "ISBN1007"));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", "ISBN1008"));
        books.add(new Book("Brave New World", "Aldous Huxley", "ISBN1009"));
        books.add(new Book("The Alchemist", "Paulo Coelho", "ISBN1010"));

        members.add(new Member("Alice"));
        members.add(new Member("Bob"));
        members.add(new Member("Charlie"));
        members.add(new Member("Diana"));
        members.add(new Member("Ethan"));
        members.add(new Member("Fiona"));
        members.add(new Member("George"));
        members.add(new Member("Hannah"));
        members.add(new Member("Ivan"));
        members.add(new Member("Julia"));

        this.availableBooks = new ArrayList<>(books);
    }

    public List<Book> getAvailableBooks() {
        return availableBooks;
    }

    public void addBook(Book book) {
        if(book != null) {
            for (Book b : books) {
                if (b.getIsbn().equalsIgnoreCase(book.getIsbn())) {
                    System.out.println("Duplicate ISBN! Book not added.");
                    return;
                }
            }
        } else
            System.out.println("No Books Registered");
        books.add(book);
        availableBooks.add(book);
        System.out.println("Book Added Successfully");
    }

    public void addMember(Member member) {
        if (member == null) {
            System.out.println("No Members Registered");
            return;
        }
        members.add(member);
        System.out.println("Member Added Successfully");
    }

    public void removeBookByISBN(String isbn) {
        boolean removed = availableBooks.removeIf(book -> book.getTitle().equalsIgnoreCase(isbn));
        if(removed) {
            books.removeIf(book -> book.getIsbn().equalsIgnoreCase(isbn));
            System.out.println("Book is Removed Successfully");
        } else
            System.out.println("Book Has Been Borrowed, Wait Until it Returned");
    }

    public void removeBookByTitle(String title) {
        boolean removed = availableBooks.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        if(removed) {
            books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
            System.out.println("Book is Removed Successfully");
        } else
            System.out.println("Book Has Been Borrowed, Wait Until it Returned");
    }

    public void removeBooksByAuthor(String author) {
        boolean removed = availableBooks.removeIf(book -> book.getTitle().equalsIgnoreCase(author));
        if(removed) {
            books.removeIf(book -> book.getTitle().equalsIgnoreCase(author));
            System.out.println("Book is Removed Successfully");
        } else
            System.out.println("Book Has Been Borrowed, Wait Until it Returned");
    }

    public void removeMemberByName(String name) {
        boolean removed = members.removeIf(book -> book.getName().equalsIgnoreCase(name));
        System.out.println((removed) ? "Member Removed Successfully" : "Member Not found with Name: " + name);
    }

    public void removeMemberById(int id) {
        boolean removed = members.removeIf(book -> book.getId() == id);
        System.out.println((removed) ? "Member Removed Successfully" : "Member Not found with ID: " + id);
    }

    public Book returnSearchBookByTitle(String title) {
        if (books.isEmpty()) {
            System.out.println("No Books Registered");
            return null;
        }
        for(Book book : books) {
            if(book.getTitle().equalsIgnoreCase(title))
                return book;
        }
        return null;
    }

    public Book borrowSearchBookByTitle(String title) {
        boolean available = true;
        if (books.isEmpty()) {
            System.out.println("No Books Registered");
            return null;
        }
        for(Book book : availableBooks) {
            if(book.getTitle().equalsIgnoreCase(title))
                return book;
            available = false;
        }
        if(!available){
            System.out.println("Book Has Been Borrowed");
            return null;
        }
        return null;
    }

    public Book searchBookByISBN(String isbn) {
        if(books.isEmpty()) {
            System.out.println("No books registered");
        }
        for(Book book : availableBooks) {
            if(book.getIsbn().equalsIgnoreCase(isbn))
                return book;
        }
        return null;
    }

    public boolean borrowBook(String title, Member member) {
        Book book = borrowSearchBookByTitle(title);

        if(book != null) {
            if (member != null) {
                book.setAvailable(false);
                availableBooks.remove(book);
                member.addBorrowedBook(book);
                borrowedRecords.put(member, member.getBorrowedBooks());
                return true;
            } else
                return false;
        }
        return false;
    }

    public boolean returnBook(String title, Member member) {
        Book book = returnSearchBookByTitle(title);

        if(book == null) {
            System.out.println("Book not found in library");
            return false;
        }

        if(!member.getBorrowedBooks().contains(book)) {
            System.out.println("This member didn't borrowed that book");
            return false;
        }

        book.setAvailable(true);
        availableBooks.add(book);
        member.removeBorrowedBook(book);
        borrowedRecords.put(member, member.getBorrowedBooks());
        return true;
    }

    public void listAvailableBooks() {
        if(!availableBooks.isEmpty()) {
            System.out.println("Books Available:");
            for (Book book : availableBooks)
                System.out.println("    - " + book.getTitle() + " by " + book.getAuthor());
        }  else
            System.out.println("No Book is Registered.  Register a Book");
    }

    public void listMembers() {
        if(!members.isEmpty()) {
            System.out.println("\nMembers in Library:\n");
            System.out.printf("%-5s | %-15s | %s%n", "ID", "Name", "Books Borrowed");
            System.out.println("---------------------------------------------");
            for(Member member : members) {
                List<String> booksBorrowed = new ArrayList<>();
                for(Book book : member.getBorrowedBooks())
                    booksBorrowed.add(book.getTitle());
                System.out.printf("%-5d | %-15s | %s%n", member.getId(), member.getName(), booksBorrowed);
            }
            System.out.println("---------------------------------------------");
        } else
            System.out.println("No members registered");
    }

    public void getBorrowedBookOfMember(Member member) {
        if(member.getBorrowedBooks().isEmpty()) {
            System.out.println("This member didn't borrowed any books");
        } else {
            System.out.println(member.getName() + " borrowed " + (member.getBorrowedBooks().size() > 1 ? "books" : "book"));
            for(Book book : member.getBorrowedBooks())
                System.out.println(book.getTitle());
        }
    }

    public void listRegisteredBooks() {
        System.out.println("\nBooks in Library:\n");
        System.out.printf("%-45s | %-25s | %-15s | %-10s%n", "Title", "Author", "ISBN", "Available");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        for(Book book : books)
            System.out.printf("%-45s | %-25s | %-15s | %-10s%n", book.getTitle(), book.getAuthor(), book.getIsbn(), (book.getAvailable()) ? "Yes" : "No");
        System.out.println("--------------------------------------------------------------------------------------------------------");
    }
}