import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;    
public class MenuExample implements ActionListener, MenuListener {    
JFrame frame,frameAllshift;    
JMenuBar menuBarDashboard;    
JMenu allShiftMenu,addAvailabilityMenu,updateProfileMenu,logoutMenu;    
JButton backButton;
JTextArea ta;
MenuExample(){   
frame=new JFrame();    
JFrame frameAllshif; 






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
	 JOptionPane.showMessageDialog(null, e.getSource());  
		if(e.getSource().equals(backButton) )   {
			frame.setVisible(true);
			frameAllshift.setVisible(false);
		}
 

}     
public void CheckNurseID(String id,String tableDB) {
	System.out.print(id);
	System.out.print(tableDB);
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
        
       // backButton.setBackground(Color.yellow);   
        String data[][]={ {"101","Amit","670000"},    
	                {"102","Jai","780000"},    
	                {"101","Sachin","700000"}};    
			String column[]={"ID","NAME","SALARY"};         
			JTable jt=new JTable(data,column);    
			jt.setBounds(50,100,500,500);          
			JScrollPane sp=new JScrollPane(jt);    
        
        panelAllshift.add(backButton);
        panelAllshift.add(sp);
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
//	   System.exit(0);
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
    JButton  submitButton =new JButton("Submit");     
    
    String[] preference ={"Select","Day","Night"};
    JLabel preferenceLabel = new JLabel("Preference");
    JLabel DayLabel = new JLabel("Availability");
    backButton.setBounds(50,50,80,30);   
    preferenceLabel.setBounds(50,100,80,30); 
    DayLabel.setBounds(50,150,80,30); 
   
    
    backButton.addActionListener(this);
    submitButton.addActionListener(this);
    
    JComboBox  preferenceComboBox = new JComboBox(preference);
    
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
    

    JList list = new JList();
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
@Override
public void menuDeselected(MenuEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void menuCanceled(MenuEvent e) {
	// TODO Auto-generated method stub
	
}

}    