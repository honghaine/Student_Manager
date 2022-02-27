import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Datasource datasource = new Datasource();
        if(!datasource.open()) {
            System.out.println("Cant open datasource");
            return;
        }
        List<Student> students;
        System.out.println("Enter your choice: ");
        System.out.println("1. Query all students ");
        System.out.println("2. Query student by ID ");
        System.out.println("3. Add student");
        Scanner scanner = new Scanner(System.in);
        byte ch = scanner.nextByte();
        switch (ch) {
            case 1:
                students = datasource.queryStudents();
                query(students);
                break;
            case 2:
                int idStudent = scanner.nextInt();
                students = datasource.queryStudentByID(idStudent);
                query(students);
                break;
            case 3:
                try {
                    System.out.println("Enter id:");
                    int id = scanner.nextInt();


                    System.out.println("Enter last name:");
                    String lastName = scanner.nextLine();
                    scanner.next();

                    System.out.println("Enter first name:");
                    String firstName = scanner.nextLine();
                    scanner.next();

                    System.out.println("Enter age:");
                    byte age = scanner.nextByte();


                    System.out.println("Enter email:");
                    String email = scanner.nextLine();
                    scanner.next();

                    System.out.println("Enter phone:");
                    String phone = scanner.nextLine();
                    scanner.next();



                    int id1 = datasource.insertStudent(id,lastName, firstName, age, email,phone);
                    //System.out.println(id1);

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }


        datasource.close();
    }

    public static void query(List<Student> students) {
        if(students == null) {
            System.out.println("No students!");
            return;
        }
        for (Student student:students) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getLastname() +" " +student.getFirstname() +", Age: "
                    + student.getAge()+" , Email: "+student.getEmail()+" ,Phone: "+student.getPhone());
        }
    }




}
