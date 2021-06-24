import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.TableColumnModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;    
public class NurseMenu implements ActionListener, MenuListener {    
JFrame frame,frameAllshift;    
JMenuBar menuBarDashboard;    
JMenu allShiftMenu,addAvailabilityMenu,updateProfileMenu,logoutMenu;    
JButton backButton,submitButton;
JTextArea ta;
JList list;
JLabel preferenceLabel;
JComboBox  preferenceComboBox;
public String id,tableDB;
NurseMenu(){   
frame=new JFrame();   
 list = new JList();

JFrame frameAllshift; 
  submitButton =new JButton("Submit");     

String[] preference ={"Select","Day","Night"};

 preferenceLabel = new JLabel("Preference");
  preferenceComboBox = new JComboBox(preference);

menuBarDashboard = new JMenuBar();    

allShiftMenu = new JMenu("All Shifts");    
addAvailabilityMenu =new JMenu("Add Availability");    
updateProfileMenu = new JMenu("Update Profile");     
logoutMenu =new JMenu("Logout");   
  

allShiftMenu.addMenuListener(this);    
addAvailabilityMenu.addMenuListener(this);    
updateProfileMenu.addMenuListener(this);    
logoutMenu.addMenuListener(this);    

menuBarDashboard.add(allShiftMenu);
menuBarDashboard.add(addAvailabilityMenu);
menuBarDashboard.add(updateProfileMenu);    
menuBarDashboard.add(logoutMenu);    
frame.add(menuBarDashboard);
frame.setJMenuBar(menuBarDashboard);  
frame.setLayout(null);    
frame.setSize(600,600);    
frame.setVisible(true);    

}     
public void actionPerformed(ActionEvent e) {    
//	 JOptionPane.showMessageDialog(null, e.getSource());  
		if(e.getSource().equals(backButton) )   {
			frame.setVisible(true);
			frameAllshift.setVisible(false);
		}
		if(e.getSource().equals(submitButton) )   {
			frame.setVisible(true);
			frameAllshift.setVisible(false);
			if(preferenceComboBox.getSelectedItem().toString().equals("Select")) {
				 JOptionPane.showMessageDialog(null,"Please Try again");  

				}else {
				if(tableDB.equals("nurses") ) {
					int nurse_ids;
					try {
			            //Creating Connection Object
			            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/nursemanagement","root","1234");
			            
			            //Preapared Statement
			            PreparedStatement Pstatement=connection.prepareStatement("insert into add_preference(nurse_id,"
			            		+ "preference) values(?,?)");
			            //Specifying the values of it's parameter
			      
			            Pstatement.setString(1,id);
			            Pstatement.setString(2,preferenceComboBox.getSelectedItem().toString());
			            
			            //Checking for the Password match
			            
			                //Executing query

			            Pstatement.executeUpdate();
//		            	"select prefer_id from add_preference ORDER BY Id DESC LIMIT 1"

			            
			            	PreparedStatement Pstatement1 = connection.prepareStatement("select prefer_id from add_preference ORDER BY prefer_id DESC LIMIT 1");
//
			                   ResultSet rs = Pstatement1.executeQuery();
			                   if(rs.next()) {
			                	String last_inserted_id = rs.getString("prefer_id");
			                	 PreparedStatement Pstatement2=connection.prepareStatement("insert into add_preference_day(prefer_id,nurse_id,"
						            		+ "preference_day) values(?,?,?)");
						            //Specifying the values of it's parameter
			                	 	Pstatement2.setString(1,last_inserted_id);
						            Pstatement2.setString(2,id);
						            String s = (String) list.getSelectedValue();
						            Pstatement2.setString(3,s);
						            
						            //Checking for the Password match
						            
						                //Executing query

						            Pstatement2.executeUpdate();
						            rs.close();
						            System.out.println(list.getName());
			                   }
			                

			            
			                			              

			               

			        } catch (SQLException e1) {
			            e1.printStackTrace();
			        }
				}
				}
		}
 

}     
public void CheckNurseID(String id,String tableDB) {
	this.id = id;
	this.tableDB = tableDB;
}
@Override
public void menuSelected(MenuEvent e) {
	 String rec[][] = new String[1][];
	 JTable table1 ;
	// TODO Auto-generated method stub
	if(e.getSource().equals(allShiftMenu)) {
		
			frame.setVisible(false);
			frameAllshift = new JFrame("Panel Example");
		    frameAllshift.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	        JPanel panelAllshift = new JPanel();  
	        panelAllshift.setBounds(450,80,500,700);    
	        panelAllshift.setBackground(Color.decode("#EEEEEE"));
	         backButton =new JButton("Back");     
        
        backButton.setBounds(5,5,80,30);    
        backButton.addActionListener(this);
       
        panelAllshift.add(backButton);
       // backButton.setBackground(Color.yellow);   
        if(tableDB.equals("nurses") ) {
         
			 try {
                 //Creating Connection Object
          	
                 Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/nursemanagement","root","1234");
               
                 PreparedStatement Pstatement1=connection.prepareStatement("select * from add_preference_day where nurse_id = ?");

                 Pstatement1.setString(1,id);
                 ResultSet rs = Pstatement1.executeQuery();

                 int i=0;
                 while (rs.next()) {
                	 String hostpital_id = rs.getString("nurse_id");
                	 String schedule_id = rs.getString("preference_day");
                	 String schedule_date = rs.getString("preference_day");
                	 rec[i] = new String[] {hostpital_id,schedule_id,schedule_date};
                	 i = i+1;
                 }
  
             } catch (SQLException e1) {
                 e1.printStackTrace();
             }
			
			    panelAllshift.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Assign shifts", TitledBorder.CENTER, TitledBorder.TOP));
			    String[] header = { "Nurse Id", "Preference Id", "Preference Day" };
			     JTable table11= new JTable(rec, header);
			        panelAllshift.add(table11);

        }
  
        frameAllshift.add(panelAllshift); 
                  
        frameAllshift.setSize(600,600);    
        frameAllshift.setLayout(null);    
        frameAllshift.setVisible(true);    
        

	}
	else if(e.getSource().equals(addAvailabilityMenu)) {
//		 JOptionPane.showMessageDialog(null, e.getSource());  
//		frame.setVisible(false);
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		addAvailability();
				
	}
	else if(e.getSource().equals(updateProfileMenu)) {
	   JOptionPane.showMessageDialog(null, e.getSource());  frame.setVisible(true);  
	}
	else {
    if(e.getSource().equals(logoutMenu)) {
	   System.exit(0);
   }
	}
}
public void addAvailability() {
	frameAllshift = new JFrame("Add Availavility");
    frameAllshift.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    JPanel panelAllshift = new JPanel();  
    panelAllshift.setSize(800,800);    
    panelAllshift.setBackground(Color.decode("#EEEEEE"));
    backButton =new JButton("Back");     
 
    JLabel DayLabel = new JLabel("Availability");
    backButton.setBounds(50,50,80,30);   
    preferenceLabel.setBounds(50,100,80,30); 
    DayLabel.setBounds(50,150,80,30); 
   
    
    backButton.addActionListener(this);
    submitButton.addActionListener(this);
    
  
    
    DefaultListModel dayList = new DefaultListModel();
    int past = 7;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("EEE MMM d");
    ArrayList arrayList = new ArrayList();
    for (int i = 0; i < past; i++) {
    if (i == 0){
    calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 0);
    }else if (i >0){
    calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
    }

    Date today = calendar.getTime();
    String result = format.format(today);
//    arrayList.add(result);
    //Reverse the arrayList in the next past days, you don’t need to reverse it.
  //Collections.reverse(arrayList);
    dayList.addElement(result);
     }
    

    list.setModel(dayList);
    preferenceComboBox.setBounds(140,100,80,30);
    list.setBounds(140,150,150,150);
    submitButton.setBounds(100,320,80,30);
    
    panelAllshift.add(backButton);
    panelAllshift.add(preferenceLabel);
    panelAllshift.add(DayLabel);
    panelAllshift.add(preferenceComboBox);
    panelAllshift.add(list);
    panelAllshift.add(submitButton);

    
    panelAllshift.setLayout(null);

    frameAllshift.add(panelAllshift);       
    frameAllshift.setSize(800,800);    
    frameAllshift.setLayout(null); 
    
    frameAllshift.setVisible(true); 
    
   
}
public void addDataIntoDatabase() {
//	if(preferenceComboBox.getSelectedItem().toString().equals("Select")) {
//		
//	}
//	if(tableDB.equals("nurses") ) {
//		try {
//            //Creating Connection Object
//            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/nursemanagement","root","1234");
//            
//            //Preapared Statement
//            PreparedStatement Pstatement=connection.prepareStatement("insert into add_preference(nurse_id,"
//            		+ "preference) values(?,?)");
//            //Specifying the values of it's parameter
//      
//            Pstatement.setString(1,id);
//            Pstatement.setString(2,preferenceComboBox.getSelectedItem().toString());
//            
//            //Checking for the Password match
//            
//                //Executing query
//                Pstatement.executeUpdate();
//                LoginPage login = new LoginPage();
//                frame.dispose();
//
//                JOptionPane.showMessageDialog(null,"Done");
//
//        } catch (SQLException e1) {
//            e1.printStackTrace();
//        }
//	}

}
@Override
public void menuDeselected(MenuEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void menuCanceled(MenuEvent e) {
	// TODO Auto-generated method stub
	
}

}    