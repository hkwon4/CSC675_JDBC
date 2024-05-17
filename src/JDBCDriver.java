import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class JDBCDriver {
    // JDBC URL, username and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/olympick";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    // JDBC variables for opening and managing connection
    private static Connection connection;

    public static void main(String[] args) {
        try {
            // Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established successfully.");

            // Example usage
            insertSport("Basketball", "Team Sports", "Summer","Male");
            fetchSports();

            insertAthlete("John", "Doe", "Male");
            fetchAthletes();

            insertUniversity("XYZ University", "info@xyz.edu", "1234567890", "password123", "123 University St", "City", "ST", "12345");
            fetchUniversities();

            insertFaculty(1, "Doe", "John", "Male", "johndoe@xyz.edu", "password123");
            fetchFaculty();

            insertUser("user@example.com", "password123", "John", "Doe");
            fetchUsers();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to insert a sport
    public static void insertSport(String eventName, String category, String season, String gender) throws SQLException {
        String query = "INSERT INTO Sports (event_name, category, season, gender) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, eventName);
            stmt.setString(2, category);
            stmt.setString(3, season);
            stmt.setString(4, gender);
            stmt.executeUpdate();
        }
    }

    // Method to update a sport
    public static void updateSport(int sportId, String newEventName) throws SQLException {
        String query = "UPDATE Sports SET event_name = ? WHERE sport_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newEventName);
            stmt.setInt(2, sportId);
            stmt.executeUpdate();
        }
    }

    // Method to fetch sports
    public static void fetchSports() throws SQLException {
        String query = "SELECT * FROM Sports";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Sport ID: " + rs.getInt("sport_id"));
                System.out.println("Event Name: " + rs.getString("event_name"));
                System.out.println("Category: " + rs.getString("category"));
                System.out.println("Season: " + rs.getString("season"));
                System.out.println("Gender: " + rs.getString("gender"));
                System.out.println();
            }
        }
    }

    // Method to insert an athlete
    public static void insertAthlete(String firstName, String lastName, String gender) throws SQLException {
        String query = "INSERT INTO Athletes (firstName, lastName, gender) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, gender);
            stmt.executeUpdate();
        }
    }

    // Method to update an athlete
    public static void updateAthlete(int athleteId, String newFirstName, String newLastName) throws SQLException {
        String query = "UPDATE Athletes SET firstName = ?, lastName = ? WHERE athlete_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newFirstName);
            stmt.setString(2, newLastName);
            stmt.setInt(3, athleteId);
            stmt.executeUpdate();
        }
    }

    // Method to fetch athletes
    public static void fetchAthletes() throws SQLException {
        String query = "SELECT * FROM Athletes";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Athlete ID: " + rs.getInt("athlete_id"));
                System.out.println("First Name: " + rs.getString("firstName"));
                System.out.println("Last Name: " + rs.getString("lastName"));
                System.out.println("Full Name: " + rs.getString("fullName"));
                System.out.println("Gender: " + rs.getString("gender"));
                System.out.println();
            }
        }
    }

    // Method to insert a university
    public static void insertUniversity(String name, String email, String phone, String password, String address, String city, String state, String zipcode) throws SQLException {
        String query = "INSERT INTO Universities (uni_name, email, phone, password, address, city, state, zipcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, password);
            stmt.setString(5, address);
            stmt.setString(6, city);
            stmt.setString(7, state);
            stmt.setString(8, zipcode);
            stmt.executeUpdate();
        }
    }

    // Method to update a university
    public static void updateUniversity(int universityId, String newName) throws SQLException {
        String query = "UPDATE Universities SET uni_name = ? WHERE university_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setInt(2, universityId);
            stmt.executeUpdate();
        }
    }

    // Method to fetch universities
    public static void fetchUniversities() throws SQLException {
        String query = "SELECT * FROM Universities";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("University ID: " + rs.getInt("university_id"));
                System.out.println("Name: " + rs.getString("uni_name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("City: " + rs.getString("city"));
                System.out.println("State: " + rs.getString("state"));
                System.out.println("Zipcode: " + rs.getString("zipcode"));
                System.out.println();
            }
        }
    }

    // Method to insert a faculty member
    public static void insertFaculty(int universityId, String lastName, String firstName, String gender, String email, String password) throws SQLException {
        String query = "INSERT INTO Faculty (university_id, l_name, f_name, gender, email, password) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, universityId);
            stmt.setString(2, lastName);
            stmt.setString(3, firstName);
            stmt.setString(4, gender);
            stmt.setString(5, email);
            stmt.setString(6, password);
            stmt.executeUpdate();
        }
    }

    // Method to update a faculty member
    public static void updateFaculty(int facultyId, int universityId, String newLastName, String newFirstName) throws SQLException {
        String query = "UPDATE Faculty SET l_name = ?, f_name = ? WHERE faculty_id = ? AND university_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newLastName);
            stmt.setString(2, newFirstName);
            stmt.setInt(3, facultyId);
            stmt.setInt(4, universityId);
            stmt.executeUpdate();
        }
    }

    // Method to fetch faculty members
    public static void fetchFaculty() throws SQLException {
        String query = "SELECT * FROM Faculty";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Faculty ID: " + rs.getInt("faculty_id"));
                System.out.println("University ID: " + rs.getInt("university_id"));
                System.out.println("Last Name: " + rs.getString("l_name"));
                System.out.println("First Name: " + rs.get);
            }
        }
    }
    public static void insertUser()
}
