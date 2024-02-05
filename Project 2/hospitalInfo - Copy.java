import java.util.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
public class hospitalInfo {
    static Connection con;
    static Statement st;
    static ResultSet rs;
    private static final String DB_URL="jdbc:mysql://localhost:3306/hospital";
    private static final String DB_USER="root";
    private static final String DB_PW="aryan@123";
    public static void addPatient(){
        try{
            con=DriverManager.getConnection(DB_URL,DB_USER,DB_PW);
            System.out.println("Database connected");
            st=con.createStatement();
            int ID=Integer.parseInt(JOptionPane.showInputDialog("Enter ID: "));
            String name=JOptionPane.showInputDialog("Enter name: ");
            int age=Integer.parseInt(JOptionPane.showInputDialog("Enter age: "));
            String gender=JOptionPane.showInputDialog("Enter gender: ");
            String condition=JOptionPane.showInputDialog("Enter condition: ");
            String query = "INSERT INTO patients (patientID, name, age, gender, condition) VALUES (?,?, ?, ?, ?)";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,ID);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, gender);
            ps.setString(5, condition);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Patient added successfully");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public static void accessPatient(){
        try{
            con=DriverManager.getConnection(DB_URL,DB_USER,DB_PW);
            System.out.println("Database connected");
            st=con.createStatement();
            int ID = Integer.parseInt(JOptionPane.showInputDialog("Enter patient ID: "));
            String query = "SELECT * FROM patients WHERE patientID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Patient Name: " + rs.getString("name") + "\nAge: " + rs.getInt("age") + "\nGender: " + rs.getString("gender") + "\nCondition: " + rs.getString("condition"));
            } else {
                JOptionPane.showMessageDialog(null, "Patient not found");
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public static void updatePatient(){
        try {
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
            System.out.println("Database connected");
            st = con.createStatement();
            int ID = Integer.parseInt(JOptionPane.showInputDialog("Enter patient ID to update: "));
            String name = JOptionPane.showInputDialog("Enter new patient name: ");
            int age = Integer.parseInt(JOptionPane.showInputDialog("Enter new patient age: "));
            String gender = JOptionPane.showInputDialog("Enter new patient gender: ");
            String condition = JOptionPane.showInputDialog("Enter new patient condition: ");
            String query = "UPDATE patients SET name=?, age=?, gender=?, condition=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, condition);
            ps.setInt(5, ID);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Patient data updated successfully");
            } else {
                JOptionPane.showMessageDialog(null, "No patient found with the given ID");
            }
            JOptionPane.showMessageDialog(null,"Patient data updated successfully");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public static void main(String[] args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        JFrame f=new JFrame("Hospital Management");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        JLabel lTitle=new JLabel("Hospital Management System");
        lTitle.setBounds(200,50,300,30);
        f.add(lTitle);
        JButton btnAdd=new JButton("Add Patient");
        btnAdd.setBounds(50,100,100,30);
        f.add(btnAdd);
        JButton btnRetrieve=new JButton("Retrieve Patient");
        btnRetrieve.setBounds(50,150,100,30);
        f.add(btnRetrieve);
        JButton btnUpdate=new JButton("Update Patient");
        btnUpdate.setBounds(50,200,100,30);
        f.add(btnUpdate);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPatient();
            }
        });
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accessPatient();
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePatient();
            }
        });
        f.setSize(400,400);
        f.setVisible(true);
    }
}
