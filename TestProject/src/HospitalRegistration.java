//importing packages
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
public class HospitalRegistration implements ActionListener {
	 //Creating object of JFrame class
    JFrame frame;
    

    //Creating objects
    String[] gender={"Male","Female"};
    JLabel nameLabel=new JLabel("NAME");
    JLabel address=new JLabel("Address");
    JLabel passwordLabel=new JLabel("PASSWORD");
    JLabel confirmPasswordLabel=new JLabel("CONFIRM PASSWORD");
    JLabel cityLabel=new JLabel("CITY");
    JLabel streetLabel=new JLabel("Street");
    JLabel countryLabel=new JLabel("Country");
    JLabel postalcodeLabel=new JLabel("Postal Code");
    JLabel emailLabel=new JLabel("EMAIL");
    JLabel contactLabel=new JLabel("Contact Number");

    JLabel licenseLabel=new JLabel("License");
    JLabel certifiedLabel=new JLabel("Certified");

    JTextField nameTextField=new JTextField();
    JTextField addresssTextField=new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JPasswordField confirmPasswordField=new JPasswordField();
    JTextField cityTextField=new JTextField();
    JTextField streetTextField=new JTextField();
    JTextField countryTextField=new JTextField();
    JTextField postalcodeTextField = new JTextField();
    JTextField emailTextField=new JTextField();
    JTextField contactTextField=new JTextField();
    JTextField licenseTextField=new JTextField();
    JTextField certifiedTextField=new JTextField();


    JButton registerButton=new JButton("REGISTER");
    JButton resetButton=new JButton("RESET");
    HospitalRegistration()
    {
    

    	  createWindow();
          setLocationAndSize();
          addComponentsToFrame();
          actionEvent();
    }
    
    
	//Creating user-defined method
    public void createWindow()
    {          JScrollBar oneJScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);

       //Setting properties of JFrame
        frame=new JFrame();
        frame.setTitle("Hospital Registration Form");
        frame.setBounds(40,40,380,600);
        frame.getContentPane().setBackground(Color.decode("#EEEEEE"));
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
        frame.add(oneJScrollBar, BorderLayout.NORTH);

        frame.setResizable(true);
    }
    public void setLocationAndSize() {

        //Setting Location and Size of Each Component
        nameLabel.setBounds(20,20,40,70);
        address.setBounds(20,70,80,70);
        passwordLabel.setBounds(20,120,100,70);
        confirmPasswordLabel.setBounds(20,170,140,70);
        cityLabel.setBounds(20,220,140,70);
        streetLabel.setBounds(20,270,100,70);
        countryLabel.setBounds(20,320,100,70);
        postalcodeLabel.setBounds(20,370,100,70);
        emailLabel.setBounds(20,420,100,70);
        contactLabel.setBounds(20,470,100,70);
     
        licenseLabel.setBounds(20,520,100,70);
        certifiedLabel.setBounds(20,570,100,70);
        
        nameTextField.setBounds(180,43,165,23);
        addresssTextField.setBounds(180,93,165,23);
        passwordField.setBounds(180,143,165,23);
        confirmPasswordField.setBounds(180,193,165,23);
        cityTextField.setBounds(180,243,165,23);
        streetTextField.setBounds(180,293,165,23);
        countryTextField.setBounds(180,343,165,23);
        postalcodeTextField.setBounds(180,393,165,23);
        emailTextField.setBounds(180,443,165,23);
        contactTextField.setBounds(180,493,165,23);
     
        licenseTextField.setBounds(180,543,165,23);
        certifiedTextField.setBounds(180,593,165,23);
        
        registerButton.setBounds(70,643,100,35);
        resetButton.setBounds(220,643,100,35);
    }
    public void actionEvent()
    {
        registerButton.addActionListener(this);
        resetButton.addActionListener(this);
    }
    public void addComponentsToFrame()
    {
        //Adding components to Frame
        frame.add(nameLabel);
        frame.add(address);
        frame.add(passwordLabel);
        frame.add(confirmPasswordLabel);
        frame.add(cityLabel);
        frame.add(streetLabel);
        frame.add(countryLabel); 
        frame.add(postalcodeLabel);
        frame.add(emailLabel);
        frame.add(contactLabel);
        frame.add(licenseLabel);
        frame.add(certifiedLabel);

        
        frame.add(nameTextField);
        frame.add(addresssTextField);
        frame.add(passwordField);
        frame.add(confirmPasswordField);
        frame.add(cityTextField);
        frame.add(streetTextField);
        frame.add(countryTextField);
        frame.add(postalcodeTextField);
        frame.add(emailTextField);
        frame.add(contactTextField);

        frame.add(licenseTextField);
        frame.add(certifiedTextField);

        frame.add(registerButton);
        frame.add(resetButton);
    }
 
    public void actionPerformed(ActionEvent e) {
    	   if(e.getSource()==registerButton)
           {
               try {
                   //Creating Connection Object
                   Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/nursemanagement","root","1234");
                   
                   PreparedStatement Pstatement1=connection.prepareStatement("select EMAIL from hospital where EMAIL = ?");
                   Pstatement1.setString(1,emailTextField.getText());
                   ResultSet rs = Pstatement1.executeQuery();
                   if(rs.next()) {
                       JOptionPane.showMessageDialog(null, "Another user exist with the same email id.");  
                      // System.exit(0);
                   }else {
                   rs.close();
                   //Preapared Statement
                   PreparedStatement Pstatement=connection.prepareStatement("insert into hospital(name,"
                   		+ "address,PASSWRD,CONFIRMPASSWRD,CITY,EMAIL,street,country,postalcode,"
                   		+ "contact,"
                   		+ "license,certified) values(?,?,?,?,?,?,?,?,?,?,?,?)");
                   //Specifying the values of it's parameter
//                   Pstatement.setString(1,'4');
             
                   Pstatement.setString(1,nameTextField.getText());
                   Pstatement.setString(2,addresssTextField.getText());
                   Pstatement.setString(3,passwordField.getText());
                   Pstatement.setString(4,confirmPasswordField.getText());
                   Pstatement.setString(5,cityTextField.getText());

                   Pstatement.setString(6,emailTextField.getText());
                   Pstatement.setString(7,streetTextField.getText());
                   Pstatement.setString(8,countryTextField.getText());      
                   Pstatement.setString(9,postalcodeTextField.getText());
                   Pstatement.setString(10,contactTextField.getText());
                
                   Pstatement.setString(11,licenseTextField.getText());
                   Pstatement.setString(12,certifiedTextField.getText());

                   //Checking for the Password match
                   if(passwordField.getText().equalsIgnoreCase(confirmPasswordField.getText()))
                   {
                       //Executing query
                       Pstatement.executeUpdate();
                       LoginPage login = new LoginPage();
                       frame.dispose();
                   }
                   else
                   {
                       JOptionPane.showMessageDialog(null,"password did not match");
                   }
                   }
    
               } catch (SQLException e1) {
                   e1.printStackTrace();
               }
    
    
           }
           if(e.getSource()==resetButton)
           {
               //Clearing Fields
               nameTextField.setText("");
               addresssTextField.setText("");
               passwordField.setText("");
               confirmPasswordField.setText("");
               cityTextField.setText("");
               emailTextField.setText("");
           }
    }
}
