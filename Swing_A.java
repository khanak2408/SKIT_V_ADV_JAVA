import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JLabel;

import java.sql.*;
import java.awt.event.*;
class RegistrationForm extends JFrame implements ActionListener{
    Container c;
    JLabel lblFirstName,lblLastName,lblEmail;
    JTextField txtFirstName,txtLastName,txtEmail;
    JButton btnRegister;

    public RegistrationForm(){
        c=getContentPane();
        lblFirstName=new JLabel("First Name");
        lblLastName=new JLabel("Last Name");
        lblEmail=new JLabel("Email");

        txtFirstName=new JTextField(10);
        txtLastName=new JTextField(10);
        txtEmail=new JTextField(10);

        btnRegister=new JButton();
        btnRegister.setText("Register");

        c.add(lblFirstName);
        c.add(txtFirstName);
        c.add(lblLastName);
        c.add(txtLastName);
        c.add(lblEmail);
        c.add(txtEmail);
        c.add(btnRegister);

        btnRegister.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==btnRegister){
            String fn=txtFirstName.getText();
            String ln=txtLastName.getText();
            String email=txtEmail.getText();

            Connection con=null;
            Statement stmt=null;
            con=DBConnection.getDatabaseConnection();
            if(con==null)
            {
                System.out.println("s cann't be established..");
            }
            String sqlQuery="Insert into student_info values('"+fn+"','"+ln+"','"+email+"')";
            
            try
            {
                stmt=con.createStatement();
                int rowsInserted=stmt.executeUpdate(sqlQuery);
                if(rowsInserted>0)
                {
                    //System.out.println("Registration is successful");
                    Welcome w=new Welcome();
                    w.setTitle("Welcome "+fn);
                    w.setVisible(true);
                    w.setSize(400,400);
                    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else
                {
                    System.out.println("Ni hua");
                }
            }
            catch(SQLException ex)
            {

            }
            
        }
    }
}

public class Swing_A{
    public static void main(String args[]){
        RegistrationForm f=new RegistrationForm();
        f.setSize(600,600);
        f.setLayout(new FlowLayout());
        f.setTitle("Registration Form");
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}