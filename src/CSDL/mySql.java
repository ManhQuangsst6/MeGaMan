/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CSDL;

/**
 *
 * @author Admin
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.SQLException;
import javax.swing.JOptionPane;


 
 
public class mySql implements ActionListener {
    private Form view;
    private String name;
   
    
    public mySql(Form view) {
        this.view = view;
        view.Save.addActionListener(this);
        
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("aaaaa");
            }
        });
        
    }
    
    public boolean validateName(){
        try {
            if (!"".equals(view.getNameTextField().getText())) {
               name = view.getNameTextField().getText();
                return true;
            } else {
                JOptionPane.showMessageDialog(view, " ten không được trống.");
                return false;
            }
        } catch (NumberFormatException ex) {
             //ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Ten không hợp lệ.");
        }
        return false;
    }
  
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
        try {
            DataProvider dataProvider=new DataProvider();
            java.sql.Statement stmt=dataProvider.ketNoi().createStatement();
            if(e.getSource().equals(view.getSave())){
                if(!validateName())
                    return;
                String sqlInsert = "insert into game values ("+"'"+name+"')";
                               
                int countInserted =stmt.executeUpdate(sqlInsert);
                System.out.println("The SQL statement is: " + sqlInsert + "\n"); 
            }
            
            
        } catch (SQLException ex) {
          
        }
    }

   
}

