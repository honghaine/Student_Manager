
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Datasource {
    public static final String DB_NAME = "testdb";
    public static final String CONNECTION = "jdbc:mysql://localhost:3306/" + DB_NAME;

    public static final String TABLE_NAME = "student";
    public static final String COLUMN_STUDENT_ID = "id";
    public static final String COLUMN_STUDENT_LASTNAME = "lastname";
    public static final String COLUMN_STUDENT_FIRSTNAME = "firstname";
    public static final String COLUMN_STUDENT_AGE = "age";
    public static final String COLUMN_STUDENT_EMAIL = "email";
    public static final String COLUMN_STUDENT_PHONE = "phone";

    public static final String USER = "root";
    public static final String PASS = "";
    Scanner scanner = new Scanner(System.in);

    public static final String QUERY_STUDENT_BY_ID = "SELECT" + " * FROM " + TABLE_NAME + " WHERE " + COLUMN_STUDENT_ID + " = ";
    public static final String INSERT_STUDENT = "INSERT INTO " + TABLE_NAME + "(id, lastname, firstname, age, email, phone)" + "VALUES(?, ?, ?, ?, ?, ?)";


    private Connection connection;

    private PreparedStatement insertIntoStudents;

    public boolean open() {
        try {
            connection = DriverManager.getConnection(CONNECTION, USER, PASS);
            insertIntoStudents = connection.prepareStatement(INSERT_STUDENT);
            return true;
        } catch (SQLException e) {
            System.out.println("Could not connect to database" + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (insertIntoStudents!=null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Could not close connection" + e.getMessage());
        }
    }

    public List<Student> queryStudents() {


        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME)) {

            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt(COLUMN_STUDENT_ID));
                student.setLastname(resultSet.getString(COLUMN_STUDENT_LASTNAME));
                student.setFirstname(resultSet.getString(COLUMN_STUDENT_FIRSTNAME));
                student.setAge(resultSet.getInt(COLUMN_STUDENT_AGE));
                student.setEmail(resultSet.getString(COLUMN_STUDENT_EMAIL));
                student.setPhone(resultSet.getString(COLUMN_STUDENT_PHONE));
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            System.out.println("Query failed" + e.getMessage());
            return null;
        }

    }

    public List<Student> queryStudentByID(int id) {
        StringBuilder sb = new StringBuilder(QUERY_STUDENT_BY_ID);
        sb.append(id);
        System.out.println("SQl:" + sb.toString());
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sb.toString())) {
            List<Student> studentsList = new ArrayList<>();

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt(COLUMN_STUDENT_ID));
                student.setLastname(resultSet.getString(COLUMN_STUDENT_LASTNAME));
                student.setFirstname(resultSet.getString(COLUMN_STUDENT_FIRSTNAME));
                student.setAge(resultSet.getInt(COLUMN_STUDENT_AGE));
                student.setEmail(resultSet.getString(COLUMN_STUDENT_EMAIL));
                student.setPhone(resultSet.getString(COLUMN_STUDENT_PHONE));
                studentsList.add(student);
            }
            return studentsList;
        } catch (SQLException e) {
            System.out.println("Query failed" + e.getMessage());
            return null;
        }
    }


    public int insertStudent(int id, String lastname, String firstname, int age, String email, String phone) throws SQLException {
        ResultSet rs = null;
        int candidateId = 0;
        try (PreparedStatement pstmt = connection.prepareStatement(INSERT_STUDENT,Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setInt(1, id);
            pstmt.setString(2, lastname);
            pstmt.setString(3, firstname);
            pstmt.setInt(4, age);
            pstmt.setString(5, email);
            pstmt.setString(6, phone);

            int rowAffected = pstmt.executeUpdate();
            if(rowAffected == 1) {
                rs = pstmt.getGeneratedKeys();
                if(rs.next())
                    candidateId = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if(rs!= null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return candidateId;
    }




}