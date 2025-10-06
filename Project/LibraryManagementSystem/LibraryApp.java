import java.io.*;

public class LibraryApp {

    private static BufferedReader br;

    static {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public static String getValidatedString(String prompt) {
        String input = null;
        while(true) {
            try {
                System.out.print(prompt);
                input = br.readLine().trim();
                if(!input.isEmpty()) {
                    break;
                } else {
                    System.out.println("Input cannot be empty. Try again.");
                }
            } catch (IOException e) {
                System.out.println("Error reading input. Please try again.");
            }
        }
        return input;
    }

    public static Book getValidatedBookInput(Library library) {
        String title = getValidatedString("Enter book title: ");
        String author = getValidatedString("Enter author name: ");

        String isbn;
        while(true) {
            isbn = getValidatedString("ISBN: ");
            if(library.searchBookByISBN(isbn) == null) break;
            System.out.println("This ISBN already exists. Enter a unique ISBN.");
        }

        return new Book(title, author, isbn);
    }

    public static Book getValidatedNewBookInput(Library library) {
        String title = getValidatedString("Book Title: ");
        String author = getValidatedString("Author name: ");

        String isbn;
        while(true) {
            isbn = getValidatedString("ISBN: ");
            if(isbn == null) {
                System.out.println("Invalid ISBN");
                continue;
            }
            break;
        }

        return new Book(title, author, isbn);
    }

    public static Member getValidatedMemberInput(Library library) {
        String name = getValidatedString("Name: ");
        return new Member(name);
    }

    public static String getValidatedBookTitle(Library library) {
        String title = getValidatedString("Book Title: ");
        if(library.getBooks().isEmpty()) {
            System.out.println("No Books Registered.  Register a Book");
            return null;
        }
        return title;
    }

    public static String getValidatedBookISBN() {
        return getValidatedString("ISBN: ");
    }

    public static String getValidatedBookAuthor() {
        return getValidatedString("Author name: ");
    }

    public static String getValidatedMemberName() {
        return getValidatedString("Member name: ");
    }

    public static Member getValidatedMemberById(Library library) {
        while(true) {
            try {
                System.out.print("Member ID: ");
                int id = Integer.parseInt(br.readLine());
                if (library.getMembers().isEmpty()){
                    System.out.println("No Members Registered.  Register a Member.");
                    return null;
                }
                if(id == 0 || id < 0){
                    System.out.println("Invalid Id. Enter valid Id.");
                    continue;
                }
                for (Member member : library.getMembers()) {
                    if (member.getId() == id)
                        return member;
                }
            } catch(NumberFormatException e) {
                System.out.println("Enter valid Id.");
            } catch (IOException e) {
                System.out.println("Oops! Something went wrong");
            }
        }
    }

    public static int getValidatedMemberId() {
        while(true) {
            try {
                System.out.print("Member ID: ");
                int id = Integer.parseInt(br.readLine());
                if(id == 0 || id < 0){
                    System.out.println("Invalid Id. Enter valid Id.");
                    continue;
                }
                return id;
            } catch(NumberFormatException e) {
                System.out.println("Enter valid Id.");
            } catch (IOException e) {
                System.out.println("Oops! Something went wrong");
            }
        }
    }

    public static int validateChoice() {
        while (true) {
            try {
                System.out.print("\nChoice ->  ");
                int choice = Integer.parseInt(br.readLine());
                return choice;
            } catch (NumberFormatException e) {
                System.out.println("Enter valid choice");
            } catch (IOException e) {
                System.out.println("Oops! Something went wrong");
            }
        }
    }

    public static void removeBookMenu(Library library) {
        System.out.println("1. Remove Book by ISBN");
        System.out.println("2. Remove Book by Title");
        System.out.println("3. Remove Books by Author");
        int choice = validateChoice();

        switch (choice) {
            case 1:
                library.removeBookByISBN(getValidatedBookISBN());
                break;
            case 2:
                String title = getValidatedBookTitle(library);
                library.removeBookByTitle(title);
                break;
            case 3:
                library.removeBooksByAuthor(getValidatedBookAuthor());
                break;
            default:
                System.out.println("Invalid Choice!");
        }
    }

    public static void removeMemberMenu(Library library) {
        System.out.println("1. Remove Member by Name");
        System.out.println("2. Remove Member by Id");
        int choice = validateChoice();

        switch (choice) {
            case 1:
                String name = getValidatedMemberName();
                library.removeMemberByName(name);
                break;
            case 2:
                int id = getValidatedMemberId();
                library.removeMemberById(id);
                break;
            default:
                System.out.println("Invalid Choice!");
        }
    }

    public static void main(String[] args) {
        Library library = new Library();

        System.out.println("╔══════════════════════════════════════════════════════════════════╗");
        System.out.println("║         Welcome to MyLibrary — Where Stories Come Alive!         ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════╣");
        System.out.println("║  Borrow. Return. Discover.                                       ║");
        System.out.println("║  Your cozy digital library awaits you.                           ║");
        System.out.println("║  Explore, learn, and let imagination flow freely.                ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════╝\n");


        System.out.println("╔═════════════════════════════════════════════════════════════╗");
        System.out.println("║                     LIBRARY MAIN MENU                       ║");
        System.out.println("╠═════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Add Book                           →  Add new book      ║");
        System.out.println("║  2. Add Member                         →  Register member   ║");
        System.out.println("║  3. Borrow Book                        →  Lend a book       ║");
        System.out.println("║  4. Return Book                        →  Return borrowed   ║");
        System.out.println("║  5. List Available Books               →  Show all books    ║");
        System.out.println("║  6. List Members                       →  Show all members  ║");
        System.out.println("║  7. List Member Borrowed Books         →  Show borrowed     ║");
        System.out.println("║  8. Remove Book                        →  Delete a book     ║");
        System.out.println("║  9. List All Registered Books          →  Show books        ║");
        System.out.println("║  0. Exit                               →  Close program     ║");
        System.out.println("╚═════════════════════════════════════════════════════════════╝\n");

        while (true) {
            int choice = validateChoice();

            switch (choice) {
                case 1:
                    Book book = getValidatedNewBookInput(library);
                    library.addBook(book);
                    break;
                case 2:
                    Member member = getValidatedMemberInput(library);
                    library.addMember(member);
                    break;
                case 3:
                    String title = getValidatedBookTitle(library);
                    member = getValidatedMemberById(library);
                    if(library.borrowBook(title, member))
                        System.out.println("Book Borrowed Successfully");
                    else
                        System.out.println("Book Borrow Unsuccessful");
                    break;
                case 4:
                    title = getValidatedBookTitle(library);
                    member = getValidatedMemberById(library);
                    if(library.returnBook(title, member))
                        System.out.println("Book Returned Successfully");
                    else
                        System.out.println("Book Return Unsuccessful");
                    break;
                case 5:
                    library.listAvailableBooks();
                    break;
                case 6:
                    library.listMembers();
                    break;
                case 7:
                    member = getValidatedMemberById(library);
                    library.getBorrowedBookOfMember(member);
                    break;
                case 8:
                    library.removeBookByTitle(getValidatedBookTitle(library));
                    break;
                case 9:
                    library.listRegisteredBooks();
                    break;
                case 0:
                    System.out.println("Thanks for visiting MyLibrary! Goodbye 👋");
                    return;
                default:
                    System.out.println("Invalid choice, try again!");
            }
        }
    }
}