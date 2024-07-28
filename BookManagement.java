import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;  // Import PreparedStatement
import java.sql.ResultSet;

public class BookManagement {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/library";
        String userName = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(url, userName, password);
        if (!connection.isClosed()) {
            System.out.println(connection.getMetaData().getDatabaseProductName());
        }
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Book Management Menu");
            System.out.println("1. Add a Book");
            System.out.println("2. View All Books");
            System.out.println("3. Update a Book");
            System.out.println("4. Delete a Book");
            System.out.println("5. Search for a Book");
            System.out.println("6. Exit");
            System.out.println();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addBook(scanner, connection);
                    break;
                case 2:

                    viewAllBooks(connection);
                    break;
                case 3:
                    updateABook(scanner, connection);
                    break;
                case 4:
                    deleteABook(scanner, connection);
                    break;
                case 5:
                    searchForABook(scanner, connection);
                    break;
                case 6:
                    System.out.println("Exit");
                    connection.close();
                    System.exit(0);
            }
        }
    }

    static void addBook(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter the book title: ");
        String bookTitle = scanner.next();
        System.out.print("Enter the author: ");
        String author = scanner.next();
        System.out.print("Enter the year: ");
        int year = scanner.nextInt();

        String sql = "INSERT INTO books (title, author, year) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, bookTitle);
        preparedStatement.setString(2, author);
        preparedStatement.setInt(3, year);
        int rows = preparedStatement.executeUpdate();
        System.out.println(rows + " row(s) affected");
        preparedStatement.close();
    }

    static void viewAllBooks(Connection connection) throws SQLException {
        String sql = "SELECT * FROM books";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // Check if there are any results
        if (!resultSet.isBeforeFirst()) {
            System.out.println("No books found in the database.");
        } else {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int year = resultSet.getInt("year");
                System.out.printf("ID: %d, Title: %s, Author: %s, Year: %d%n", id, title, author, year);
            }
        }
        resultSet.close();
        statement.close();
    }

    static void updateABook(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter the book ID: ");
        int bookID = scanner.nextInt();
        System.out.print("Enter the new title: ");
        String bookTitle = scanner.next();
        System.out.print("Enter the new author: ");
        String author = scanner.next();
        System.out.print("Enter the new year: ");
        int year = scanner.nextInt();

        String sql = "UPDATE books SET title = ?, author = ?, year = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, bookTitle);
        preparedStatement.setString(2, author);
        preparedStatement.setInt(3, year);
        preparedStatement.setInt(4, bookID);
        int rows = preparedStatement.executeUpdate();
        System.out.println(rows + " row(s) affected");
        preparedStatement.close();
    }

    static void deleteABook(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter the ID of the book to delete: ");
        int bookID = scanner.nextInt();

        String sql = "DELETE FROM books WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, bookID);

        int rows = preparedStatement.executeUpdate();
        System.out.println(rows + " row(s) affected");
        preparedStatement.close();
    }

    static void searchForABook(Scanner scanner, Connection connection) throws SQLException {
        System.out.print("Enter the title or author of the book: ");
        String search = scanner.next();
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + search + "%");
        preparedStatement.setString(2, "%" + search + "%");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            int year = resultSet.getInt("year");
            System.out.printf("ID: %d, Title: %s, Author: %s, Year: %d%n", id, title, author, year);
        }

        resultSet.close();
        preparedStatement.close();
    }
}
