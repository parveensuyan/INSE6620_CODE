import javax.swing.*;
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
import javax.swing.border.TitledBorder;
public class AgencyMenu implements ActionListener, MenuListener,MouseListener {    
JFrame frame,frameAllshift;    
JMenuBar menuBarDashboard;    
JMenu allShiftMenu,addAvailabilityMenu,updateProfileMenu,logoutMenu;    
JButton backButton,submitButton;
JTextArea ta;
JList list;
JLabel preferenceLabel;
JComboBox  preferenceComboBox;
public String id,tableDB;
AgencyMenu(){   
frame=new JFrame();   
 list = new JList();

JFrame frameAllshift; 
  submitButton =new JButton("Submit");     

String[] preference ={"Select","Day","Night"};

// preferenceLabel = new JLabel("Preference");
  preferenceComboBox = new JComboBox(preference);

menuBarDashboard = new JMenuBar();    

allShiftMenu = new JMenu("All Shifts");    
addAvailabilityMenu =new JMenu("Assign Shifts ");    
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
				if(tableDB.equals("hospital") ) {
					int nurse_ids;
					try {
			            //Creating Connection Object
			            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/nursemanagement","root","1234");
			            
			            //Preapared Statement
			            PreparedStatement Pstatement=connection.prepareStatement("insert into add_schedule(hospital_id,"
			            		+ "schedule_day_night) values(?,?)");
			            //Specifying the values of it's parameter
			      
			            Pstatement.setString(1,id);
			            Pstatement.setString(2,preferenceComboBox.getSelectedItem().toString());
			            
			            //Checking for the Password match
			            
			                //Executing query

			            Pstatement.executeUpdate();
//		            	"select prefer_id from add_preference ORDER BY Id DESC LIMIT 1"

			            
			            	PreparedStatement Pstatement1 = connection.prepareStatement("select schedule_id from add_schedule ORDER BY schedule_id DESC LIMIT 1");
//
			                   ResultSet rs = Pstatement1.executeQuery();
			                   if(rs.next()) {
			                	String last_inserted_id = rs.getString("schedule_id");
			                	 PreparedStatement Pstatement2=connection.prepareStatement("insert into add_schedule_day(schedule_id,nurse_id,"
						            		+ "schedule_day) values(?,?,?)");
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
        String rec[][] = new String[1][];
        JTable table1 ;
        panelAllshift.add(backButton);
       // backButton.setBackground(Color.yellow);   
		 try {
             //Creating Connection Object
      	
             Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/nursemanagement","root","1234");
           
             PreparedStatement Pstatement1=connection.prepareStatement("select * from assignment limit 1");

             ResultSet rs = Pstatement1.executeQuery();

             int i=0;
             while (rs.next()) {
            	 String hostpital_id = rs.getString("nurse_id");
            	 String schedule_id = rs.getString("hospital_id");
            	 String schedule_date = rs.getString("preference_day");
            	 rec[i] = new String[] {hostpital_id,schedule_id,schedule_date};
            	 i = i+1;
             }

         } catch (SQLException e1) {
             e1.printStackTrace();
         }
		
		    panelAllshift.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Assign shifts", TitledBorder.CENTER, TitledBorder.TOP));
		    String[] header = { "Nurse Id", "Hospital Id", "Preference Day" };
		     JTable table11= new JTable(rec, header);
		        panelAllshift.add(table11);   
        
        panelAllshift.add(backButton);
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
	/*
	frameAllshift = new JFrame("Assign Shifts");
    frameAllshift.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    JPanel panelAllshift = new JPanel();  
    panelAllshift.setSize(800,800);    
    panelAllshift.setBackground(Color.decode("#EEEEEE"));
    backButton =new JButton("Back");     
 
//    JLabel DayLabel = new JLabel("Availability");
    backButton.setBounds(50,50,80,30);   
//    preferenceLabel.setBounds(50,100,80,30); 
//    DayLabel.setBounds(50,150,80,30); 
   
    
    backButton.addActionListener(this);
    submitButton.addActionListener(this);
    
  
	 try {
		 
         //Creating Connection Object
  	
         Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/nursemanagement","root","1234");
         PreparedStatement Pstatement1=connection.prepareStatement("select t1.hospital_id, t2.* from add_schedule t1 join add_schedule_day t2 on t1.schedule_id = t2.schedule_id");
         
         //PreparedStatement Pstatement1=connection.prepareStatement("select t3.schedule_day_night,t2.name ,t2. hospital_id,t2.name,t1.schedule_day from add_schedule_day t1 \r\n" + 
         //		"    join hospital t2 on t1.nurse_id = t2.hospital_id \r\n" + 
         //		"    join add_schedule t3 on t3.schedule_id = t1.schedule_id;");
         
         ResultSet rs = Pstatement1.executeQuery();
         while (rs.next()) {
        	 String idss = rs.getString("hospital_id");
             System.out.println(idss);     
            //System.exit(1);

         }
         
         

     } catch (SQLException e1) {
         e1.printStackTrace();
     }

   
	 JPanel panel = new JPanel();
     panel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "ODI Rankings", TitledBorder.CENTER, TitledBorder.TOP));
  String[][] rec = {
     { "1", "Steve", "AUS" },
     { "2", "Virat", "IND" },
     { "3", "Kane", "NZ" },
     { "4", "David", "AUS" },
     { "5", "Ben", "ENG" },
     { "6", "Eion", "ENG" },
  };
  String[] header = { "Rank", "Player", "Country" };
  JTable table = new JTable(rec, header);
  panelAllshift.add(new JScrollPane(table));
  
	    
    panelAllshift.add(backButton);
//    panelAllshift.add(preferenceLabel);
//    panelAllshift.add(DayLabel);
//    panelAllshift.add(preferenceComboBox);
//    panelAllshift.add(list);
    panelAllshift.add(submitButton);

    
    panelAllshift.setLayout(null);

    frameAllshift.add(panelAllshift);       
    frameAllshift.setSize(800,800);    
    //frameAllshift.setLayout(null); 
    
    frameAllshift.setVisible(true); 
    */
    String rec[][] = new String[1][];
   
	
	 try {
  	
         Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/nursemanagement","root","1234");
         PreparedStatement Pstatement1=connection.prepareStatement("select t1.hospital_id, t2.* from add_schedule t1 join add_schedule_day t2 on t1.schedule_id = t2.schedule_id");
         
         
         ResultSet rs = Pstatement1.executeQuery();
         //String[][] rec;
         int i=0;
         while (rs.next()) {
        	 String hostpital_id = rs.getString("hospital_id");
        	 String schedule_id = rs.getString("schedule_id");
        	 String schedule_date = rs.getString("schedule_day");
        	 rec[i] = new String[] {hostpital_id,schedule_id,schedule_date};
        	 i = i+1;
         }
         //System.out.println(rec);
         //System.exit(1);

     } catch (SQLException e1) {
         e1.printStackTrace();
     }
	
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createTitledBorder( BorderFactory.createEtchedBorder(), "Assign shifts", TitledBorder.CENTER, TitledBorder.TOP));
    String[] header = { "Hostpital Id", "Schedule Id", "Schedule Day" };
    JTable table = new JTable(rec, header);
    //table.addMouseListener(this);
  //  String seltedDay ;
    table.addMouseListener(new MouseAdapter() {              
        public void mouseClicked(MouseEvent evt) {       
                int col = table.getSelectedColumn();  
                int row = table.getSelectedRow();  
                String seltedDay = GetData(table, row, col);

                
                
           	 try {
           	  	
                 Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/nursemanagement","root","1234");
                 PreparedStatement Pstatement1=connection.prepareStatement("select t1.*, t2.nurse_id as hospital_id from add_preference_day t1 join add_schedule_day t2 on t1.preference_day = t2.schedule_day"
                 		+ " where preference_day = ?");
                 Pstatement1.setString(1,seltedDay);
                 ResultSet rs = Pstatement1.executeQuery();
                 while (rs.next()) {
                	 String nurse_id = rs.getString("nurse_id");
                	 String hospital_id =  rs.getString("hospital_id");
                	 String preference_day = rs.getString("preference_day"); 
                	 String prefer_id = rs.getString("prefer_id");

                	  PreparedStatement Pstatement=connection.prepareStatement("insert into assignment(nurse_id,"
                         		+ "hospital_id,preference_day,prefer_id) values(?,?,?,?)");
                         //Specifying the values of it's parameter
//                         Pstatement.setString(1,'4');
                   
                         Pstatement.setString(1,nurse_id);
                         Pstatement.setString(2,hospital_id);
                         Pstatement.setString(3,preference_day);
                         Pstatement.setString(4,prefer_id);
                         Pstatement.executeUpdate();

//                	 System.out.println(rs.getString("nurse_id"));
//                	 System.exit(1);
                 }


             } catch (SQLException e1) {
                 e1.printStackTrace();
             }
//                
                
                //System.out.println("Selected data: MouseClicked ::  " + (String)selData);
                JFrame framep = new JFrame();
                JLabel p = new JLabel("Data Enter Successfully");
                p.setBounds(20,20,80,30);
                framep.add(p);
                framep.setSize(550, 400);
                framep.setVisible(true);
        }  
    });
    panel.add(new JScrollPane(table));
    frame.add(panel);
    frame.setSize(550, 400);
    frame.setVisible(true);
    
}

public String GetData(JTable table, int row_index, int col_index){
    return (String)table.getModel().getValueAt(row_index, col_index);
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
//            PreparedStatement Pstatement=connection.prepareStatement("insert into add_preference(hospital_id,"
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
@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

}    