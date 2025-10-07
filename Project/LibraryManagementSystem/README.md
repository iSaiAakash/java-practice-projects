Library Management System (Console App)
---------------------------------------
Technologies: Java
Type: Console-based application
Project Status: In Progress / Actively Improving

Description:
A simple console-based Library Management System to manage books and members.
Allows users to:
  - Add, view, and remove books
  - Register and view members
  - Borrow and return books
  - Track which member has borrowed which books
Note: All data is stored in memory; no database is used.

How to Run:
  1. Clone the repository:
       git clone https://github.com/iSaiAakash/java-practice-projects/blob/main/Project/LibraryManagementSystem
  2. Open the project in your IDE (Eclipse, IntelliJ, VSCode with Java support)
  3. Run the main file:
       java LibraryApp.java
  4. Follow the on-screen menu to use the system

Features:
  - Add new books to the library
  - Add new members
  - Borrow and return books
  - Display available books and borrowed records
  - Simple console-based menu for easy navigation

Future Improvements:
  - Add persistent storage using files or a database
  - Implement search functionality for books and members
  - Add due dates, fines, and notifications for borrowed books
  - Improve console interface for better user experience
  - Potentially build a GUI version

Project Structure:
  LibraryManagementSystem/
    LibraryApp.java   - Main file to run the application
    Book.java         - Book class
    Member.java       - Member class
    Library.java      - Core logic and data structures
    README.md         - Project documentation

Author: Sai Aakash
License: MIT / Open-source (optional)
