import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.event.*;    
public class MenuExample implements ActionListener, MenuListener {    
JFrame frame;    
JMenuBar menuBarDashboard;    
JMenu allShiftMenu,addAvailabilityMenu,updateProfileMenu,logoutMenu;    
JTextArea ta;
MenuExample(){   
frame=new JFrame();    



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
frame.setSize(400,400);    
frame.setVisible(true);    
}     
public void actionPerformed(ActionEvent e) {    
	 JOptionPane.showMessageDialog(null, e.getSource());  
if(e.getSource()== "allShiftMenu")   {
	
}
 
//if(e.getSource()==paste)    
//ta.paste();    
//if(e.getSource()==copy)    
//ta.copy();    
//if(e.getSource()==selectAll)    
//ta.selectAll();    
}     
//public static void main(String[] args) {    
//    new MenuExample();    
//
//}    
@Override
public void menuSelected(MenuEvent e) {
	
	// TODO Auto-generated method stub
	if(e.getSource().equals(allShiftMenu)) {
	 JOptionPane.showMessageDialog(null, e.getSource());  
	}
	 if(e.getSource().equals(addAvailabilityMenu)) {
		 JOptionPane.showMessageDialog(null, e.getSource());  
	}
    if(e.getSource().equals(updateProfileMenu)) {
	   JOptionPane.showMessageDialog(null, e.getSource());  
	}
    if(e.getSource().equals(logoutMenu)) {
	   System.exit(0);
   }
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