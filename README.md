# Book-Management-System

Welcome to the Book Management System! This Java application allows you to manage a library of books with features for adding, viewing, updating, deleting, and searching for books in a database.

# Getting Started

To get started with the Book Management System, follow these steps:

1. **Clone the Repository:**
   - Use the command `git clone https://github.com/Pudamya/Book-Management-System.git` to clone the repository to your local machine.

2. **Set Up the Database:**
   - Ensure you have MySQL installed and running on your local machine.
   - Create a database named `library`.
   - Create a table named `books` with the following structure:
     ```sql
     CREATE TABLE books (
       id INT AUTO_INCREMENT PRIMARY KEY,
       title VARCHAR(255) NOT NULL,
       author VARCHAR(255) NOT NULL,
       year INT NOT NULL
     );
     ```

3. **Update Database Connection Settings:**
   - Open the `BookManagement.java` file and update the `url`, `userName`, and `password` variables with your MySQL connection details.

4. **Compile and Run the Program:**
   - Open your terminal or command prompt and navigate to the directory where the project files are located using the command `cd book-management-system`.
   - Compile the Java files by running `javac *.java`.
   - Run the program using the command `java BookManagement`.

5. **Interact with the Program:**
   - Follow the on-screen instructions to add, view, update, delete, and search for books.

# Features

- **Add a Book:**
  - Add new books to the database by providing the book title, author, and year.

- **View All Books:**
  - Display a list of all books stored in the database.

- **Update a Book:**
  - Update the details of an existing book by specifying the book ID and the new details.

- **Delete a Book:**
  - Remove a book from the database by providing the book ID.

- **Search for a Book:**
  - Search for books by title or author.

# Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, feel free to open an issue or create a pull request. Your contributions can help make this project better for everyone.

# License

This project is licensed under the MIT License. Feel free to use, modify, and distribute this software in accordance with the terms of the license.

For questions, suggestions, or further information, feel free to reach out. Thank you for using the Book Management System!
