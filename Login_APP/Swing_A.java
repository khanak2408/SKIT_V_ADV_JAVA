import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;
class LoginForm extends JFrame implements ActionListener{
    Container c;
    JLabel lblFirstName,lblEmail;
    JTextField txtFirstName,txtEmail;
    JButton btnLogin;

    public LoginForm(){
        c=getContentPane();
        lblFirstName=new JLabel("First Name");
        lblEmail=new JLabel("Email");

        txtFirstName=new JTextField(10);
        txtEmail=new JTextField(10);

        btnLogin=new JButton();
        btnLogin.setText("Login");

        c.add(lblFirstName);
        c.add(txtFirstName);
        c.add(lblEmail);
        c.add(txtEmail);
        c.add(btnLogin);

        btnLogin.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()==btnLogin){
            String fn=txtFirstName.getText();
            String email=txtEmail.getText();
            Connection con=null;
            Statement stmt=null;
            ResultSet rs=null;
            con=DBConnection.getDatabaseConnection();
            String sqlQuery="select firstname,email from student_info where firstname='"+fn+"' and email='"+email+"'";
            try
            {
                stmt=con.createStatement();
                rs=stmt.executeQuery(sqlQuery);
                if(rs.next())
                {
                    System.out.println("Hi");
                    Welcome w=new Welcome();
                    w.setTitle("Welcome "+fn);
                    w.setSize(400,400);
                    w.setVisible(true);
                    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else
                {
                    System.out.println("bye");
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
        LoginForm f=new LoginForm();
        f.setSize(600,600);
        f.setLayout(new FlowLayout());
        f.setTitle("Login Form");
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}