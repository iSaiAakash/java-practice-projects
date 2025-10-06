import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TodoMain {

    private static boolean isOnline;

    static {
        System.out.println("\n===================================");
        System.out.println("    Hello there! Welcome to My     ");
        System.out.println("          To-Do List App           ");
        System.out.println("===================================\n");
        isOnline = true;
    }

    public static void main(String[] args) throws IOException {
        
        Todo u = new Todo();

        System.out.println("1) Add New Task \n2) Delete Task (Task Completed) \n3) Show All Task \n4) Exit");

        while (isOnline) {
            System.out.println("\n1-Add   2-Delete    3-Show  4-Exit");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("\nEnter Your Option: ");
            int option;

            try {
                String input = in.readLine();
                if(input.trim().isEmpty()) {
                    System.out.print("No Input Entered.  Please type a Number!");
                    continue;
                } 
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please Enter a Number(1-4)");
                continue;
            } catch(Exception e) {
                System.out.println("Please Enter a Number(1-4)");
                continue;
            }

            switch(option) {
                case 1 : System.out.print("\nEnter new Task: ");
                         String task = in.readLine();
                         u.addTask(task);
                         break;

                case 2 : System.out.print("\nEnter Completed Task Number: ");
                         int index;
                         try {
                            String input = in.readLine();
                            if(input.trim().isEmpty()) {
                                System.out.print("No Input Entered.  Please type a Number!");
                                continue;
                            } 
                            index = Integer.parseInt(input);
                         } catch (NumberFormatException e) {
                            System.out.print("Please Enter a valid task number!");
                            continue;
                         } catch(Exception e) {
                            System.out.println("Please Enter a Number(1-4)");
                            continue;
                         }
                         u.removeTask(index - 1);
                         break;

                case 3 : u.display();
                         break;

                case 4 : System.out.println("\n========================");
                         System.out.println("       THANK YOU!       ");
                         System.out.println("  Have a productive day ");
                         System.out.println("========================\n");
                         isOnline = false;
                         break;

                default: System.out.println("Invalid choice! \n");
                         break;
            }
        }
    }
}