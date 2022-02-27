import java.sql.*;
import java.util.Scanner;

public class Student {


    private int id;
    private String lastname;
    private String firstname;
    private int age;
    private String email;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Student(int id, String lastname, String firstname, int age, String email, String phone) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    public Student() {
    }
}
