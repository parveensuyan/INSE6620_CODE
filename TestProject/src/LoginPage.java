import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
public class LoginPage implements ActionListener {
	 //Creating object of JFrame class
    JFrame frame;
    

    //Creating objects
    String[] user ={"Select","Nurse","Agency","Hospital"};
    
    String[] register ={"Select","Nurse Registration","Agency Registration","Hospital Registration"};
    JLabel registerLabel =new JLabel("Register Yourself");

    JLabel userLabel =new JLabel("Please choose the occupation");
    JLabel emailLabel=new JLabel("EMAIL");
    JLabel passwordLabel=new JLabel("PASSWORD");

    JComboBox registerComboBox = new JComboBox(register);
    
    JComboBox userComboBox=new JComboBox(user);

    JTextField emailTextField=new JTextField();
    JPasswordField passwordField=new JPasswordField();


    JButton loginButton=new JButton("Login");

    LoginPage()
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
        frame.setTitle("Login Form");
        frame.setBounds(40,40,380,600);
        frame.getContentPane().setBackground(Color.decode("#EEEEEE"));
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        frame.setSize(width/2, height/2);

        // here's the part where i center the jframe on screen
        frame.setLocationRelativeTo(null);
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        
        frame.setResizable(true);
    }
    public void setLocationAndSize() {

        //Setting Location and Size of Each Component
    	registerLabel.setBounds(20,-15,300,70);
    	userLabel.setBounds(20,20,300,70);
    	emailLabel.setBounds(20,70,80,70);
        passwordLabel.setBounds(20,120,100,70);
       
        registerComboBox.setBounds(220,2,165,23);

        userComboBox.setBounds(220,43,165,23);
        emailTextField.setBounds(220,93,165,23);
        passwordField.setBounds(220,143,165,23);
        
        
        loginButton.setBounds(220,193,100,35);
        
    }
    public void actionEvent()
    {
        loginButton.addActionListener(this);
        userComboBox.addActionListener(this);
        registerComboBox.addActionListener(this);
    }
    public void addComponentsToFrame()
    {
        //Adding components to Frame
    	frame.add(registerLabel);
        frame.add(userLabel);
        frame.add(passwordLabel);
        frame.add(emailLabel);


        frame.add(registerComboBox);
        frame.add(userComboBox);
        frame.add(passwordField);
        frame.add(emailTextField);

        frame.add(loginButton);
    }
 
    public void actionPerformed(ActionEvent e) {
    	
    		 String userComboValue = userComboBox.getSelectedItem().toString();
    		 String registerComboValue = registerComboBox.getSelectedItem().toString();
    		 String tableDB,tablefield;
    		 if(registerComboBox.getSelectedItem().toString().equals("Nurse Registration")) {
            		
    			    NurseRegistration nurseRegister = new NurseRegistration();
    			    frame.dispose();
    		 }
    		 else if (registerComboBox.getSelectedItem().toString().equals("Agency Registration")) {
    			 AgencyRegistration agencyRegister = new AgencyRegistration();
 			    frame.dispose();
    		 }
    		 else if (registerComboBox.getSelectedItem().toString().equals("Hospital Registration")) {
    			 HospitalRegistration hospitalRegister = new HospitalRegistration();
  			    frame.dispose();
    		 }
    		 
    		 else {
    	   if(e.getSource()==loginButton)
           {
               try {
                   //Creating Connection Object
            	   if(passwordField.getText().equals("") || emailTextField.getText().equals("") || userComboBox.getSelectedItem().toString().equals("Select")) {
            		   JOptionPane.showMessageDialog(null,"All fields are required ");   
            	   }
            	   else {
                   Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/nursemanagement","root","1234");
                   if(userComboValue == "Nurse") {
                	   tableDB = "nurses";
                	   tablefield = "nurse_id";
                   }
                   else if(userComboValue == "Agency"){
                	   tableDB = "agency";
                	   tablefield = "agency_id";

                   }else {
                	   tableDB = "hospital";
                	   tablefield = "hospital_id";
                   }
                  
                   PreparedStatement Pstatement1=connection.prepareStatement("select "+ tablefield +", EMAIL,PASSWRD from "+ tableDB +" where EMAIL = ?");

                   Pstatement1.setString(1,emailTextField.getText());
                   ResultSet rs = Pstatement1.executeQuery();
                  
                   if(rs.next()) {
                	   if(passwordField.getText().equalsIgnoreCase(rs.getString("PASSWRD"))) {
                		   if(tableDB == "nurses") {
                			   MenuExample menuNurse = new MenuExample();
                			   menuNurse.CheckNurseID(rs.getString("nurse_id"),tableDB);
                		   }
                		   else if(tableDB == "agency") {
                			   MenuExample menuNurse = new MenuExample();
                			   JOptionPane.showMessageDialog(null,rs.getString("agency_id"));  
                			   menuNurse.CheckNurseID(rs.getString("agency_id"),tableDB);
                		   }
                		   else {
                			   HospitalMenu menuhospital = new HospitalMenu();
                			   JOptionPane.showMessageDialog(null,rs.getString("hospital_id"));  
                			   menuhospital.CheckNurseID(rs.getString("hospital_id"),tableDB);
                		   }
                	   }
                	   else {
                		   JOptionPane.showMessageDialog(null,"Please try again");  
                	   }
                	  
                     //  JOptionPane.showMessageDialog(null, "Another user exist with the same email id.");  
                      // System.exit(0);
                   }else {
                   rs.close();
                   JOptionPane.showMessageDialog(null,"User doesn't exits");  
                   }
            	   }
    
               } catch (SQLException e1) {
                   e1.printStackTrace();
               }
    
    
           }
    		 }
    }
}
