//importing packages
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;

 
public class NurseRegistration implements ActionListener {
    //Creating object of JFrame class
    JFrame frame;
    

    //Creating objects
    String[] gender={"Male","Female"};
    JLabel nameLabel=new JLabel("NAME");
    JLabel genderLabel=new JLabel("GENDER");
    JLabel address=new JLabel("Address");
    JLabel passwordLabel=new JLabel("PASSWORD");
    JLabel confirmPasswordLabel=new JLabel("CONFIRM PASSWORD");
    JLabel cityLabel=new JLabel("CITY");
    JLabel streetLabel=new JLabel("Street");
    JLabel countryLabel=new JLabel("Country");
    JLabel postalcodeLabel=new JLabel("Postal Code");
    JLabel emailLabel=new JLabel("EMAIL");
    JLabel contactLabel=new JLabel("Contact Number");
    JLabel dobLabel=new JLabel("Date of Birth");

    JLabel qualificationLabel=new JLabel("Qualification");
    JLabel experienceLabel=new JLabel("Experience");
    JLabel licenseLabel=new JLabel("License");
    
    JTextField nameTextField=new JTextField();
    JComboBox genderComboBox=new JComboBox(gender);
    JTextField addresssTextField=new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JPasswordField confirmPasswordField=new JPasswordField();
    JTextField cityTextField=new JTextField();
    JTextField streetTextField=new JTextField();
    JTextField countryTextField=new JTextField();
    JTextField postalcodeTextField = new JTextField();
    JTextField emailTextField=new JTextField();
    JTextField contactTextField=new JTextField();
    JTextField dobTextField=new JTextField();
    JTextField qualificationTextField=new JTextField();
    JTextField experienceTextField=new JTextField();
    JTextField licenseTextField=new JTextField();
    

    JButton registerButton=new JButton("REGISTER");
    JButton resetButton=new JButton("RESET");
    NurseRegistration()
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
	 

        frame.setTitle("Registration Form");
        frame.setSize(800,800);
        frame.getContentPane().setBackground(Color.decode("#EEEEEE"));
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
        frame.add(oneJScrollBar, BorderLayout.NORTH);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setResizable(true);
    }
    public void setLocationAndSize() {

        //Setting Location and Size of Each Component
        nameLabel.setBounds(20,20,40,70);
        genderLabel.setBounds(20,70,60,70);
        address.setBounds(20,120,100,70);
        passwordLabel.setBounds(20,170,100,70);
        confirmPasswordLabel.setBounds(20,220,140,70);
        cityLabel.setBounds(20,270,100,70);
        streetLabel.setBounds(20,320,100,70);
        countryLabel.setBounds(20,370,100,70);
        postalcodeLabel.setBounds(20,420,100,70);
        emailLabel.setBounds(20,470,100,70);
        contactLabel.setBounds(20,520,100,70);
        dobLabel.setBounds(20,570,100,70);
        qualificationLabel.setBounds(20,620,100,70);
        experienceLabel.setBounds(420,20,80,70);
        licenseLabel.setBounds(420,70,100,70);
        
        nameTextField.setBounds(180,43,165,23);
        genderComboBox.setBounds(180,93,165,23);
        addresssTextField.setBounds(180,143,165,23);
        passwordField.setBounds(180,193,165,23);
        confirmPasswordField.setBounds(180,243,165,23);
        cityTextField.setBounds(180,293,165,23);
        streetTextField.setBounds(180,343,165,23);
        countryTextField.setBounds(180,393,165,23);
        postalcodeTextField.setBounds(180,443,165,23);
        emailTextField.setBounds(180,493,165,23);
        contactTextField.setBounds(180,543,165,23);
        dobTextField.setBounds(180,593,165,23);
        qualificationTextField.setBounds(180,643,165,23);
        experienceTextField.setBounds(500,43,170,23);
        licenseTextField.setBounds(500,93,170,23);
        
        registerButton.setBounds(500,143,100,35);
        resetButton.setBounds(620,143,100,35);
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
        frame.add(genderLabel);
        frame.add(address);
        frame.add(passwordLabel);
        frame.add(confirmPasswordLabel);
        frame.add(cityLabel);
        frame.add(streetLabel);
        frame.add(countryLabel); 
        frame.add(postalcodeLabel);
        frame.add(emailLabel);
        frame.add(contactLabel);
        frame.add(dobLabel);
        frame.add(qualificationLabel);
        frame.add(experienceLabel);
        frame.add(licenseLabel);
        frame.add(nameTextField);
        frame.add(genderComboBox);
        frame.add(addresssTextField);
        frame.add(passwordField);
        frame.add(confirmPasswordField);
        frame.add(cityTextField);
        frame.add(streetTextField);
        frame.add(countryTextField);
        frame.add(postalcodeTextField);
        frame.add(emailTextField);
        frame.add(contactTextField);
        frame.add(dobTextField);
        frame.add(qualificationTextField);
        frame.add(experienceTextField);
        frame.add(licenseTextField);

        frame.add(registerButton);
        frame.add(resetButton);
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
    	   if(e.getSource()==registerButton)
           {
               try {
                   //Creating Connection Object
                   Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/nursemanagement","root","1234");
                   
                   PreparedStatement Pstatement1=connection.prepareStatement("select EMAIL from nurses where EMAIL = ?");
                   Pstatement1.setString(1,emailTextField.getText());
                   ResultSet rs = Pstatement1.executeQuery();
                   if(rs.next()) {
                       JOptionPane.showMessageDialog(null, "Another user exist with the same email id.");  
                      // System.exit(0);
                   }else {
                   rs.close();
                   //Preapared Statement
                   PreparedStatement Pstatement=connection.prepareStatement("insert into nurses(name,"
                   		+ "GENDER,address,PASSWRD,CONFIRMPASSWRD,CITY,EMAIL,street,country,postalcode,"
                   		+ "contact,dob,qualification,"
                   		+ "experience,license) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                   //Specifying the values of it's parameter
//                   Pstatement.setString(1,'4');
             
                   Pstatement.setString(1,nameTextField.getText());
                   Pstatement.setString(2,genderComboBox.getSelectedItem().toString());
                   Pstatement.setString(3,addresssTextField.getText());
                   Pstatement.setString(4,passwordField.getText());
                   Pstatement.setString(5,confirmPasswordField.getText());
                   Pstatement.setString(6,cityTextField.getText());

                   Pstatement.setString(7,emailTextField.getText());
                   Pstatement.setString(8,streetTextField.getText());
                   Pstatement.setString(9,countryTextField.getText());      
                   Pstatement.setString(10,postalcodeTextField.getText());
                   Pstatement.setString(11,contactTextField.getText());
                   Pstatement.setString(12,dobTextField.getText());
                   Pstatement.setString(13,qualificationTextField.getText());
                   Pstatement.setString(14,experienceTextField.getText());
                   Pstatement.setString(15,licenseTextField.getText());
                   
                   //Checking for the Password match
                   if(passwordField.getText().equalsIgnoreCase(confirmPasswordField.getText()))
                   {
                       //Executing query
                       Pstatement.executeUpdate();
                       LoginPage login = new LoginPage();
                       frame.dispose();

                       JOptionPane.showMessageDialog(null,"Login Please");

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
         
               nameTextField.setText("");
               genderComboBox.setSelectedItem("Male");
               addresssTextField.setText("");
               passwordField.setText("");
               confirmPasswordField.setText("");
               cityTextField.setText("");
               streetTextField.setText("");
               countryTextField.setText("");
               postalcodeTextField.setText("");
               emailTextField.setText("");
               contactTextField.setText("");
               dobTextField.setText("");
               qualificationTextField.setText("");
               experienceTextField.setText("");
               licenseTextField.setText("");
           }
    }
}