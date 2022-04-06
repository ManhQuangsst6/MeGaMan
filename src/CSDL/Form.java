/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CSDL;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author Admin
 */
public class Form extends JFrame{
    public JButton Save;
   
    JPanel formPanel=new JPanel();
    
    JLabel nameJLabel=new JLabel("ten :");
    JTextField nameTextField;
    
    
    
    
    
    JPanel controlButtonsPanel=new JPanel();
    
    public Form(){
        nameTextField=new JTextField();
      
       
        builFormPanel();
        BuildcontrolButtonsPanel();
        setSize(300,200);
        setTitle(" Form");
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(JTextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    

    
    private  void builFormPanel(){
        formPanel =new JPanel();
        add(BorderLayout.CENTER,formPanel);
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints x=new GridBagConstraints();
        x.insets=new Insets(10, 10, 10, 10);
        x.weighty=1;
        x.fill=GridBagConstraints.HORIZONTAL;
        x.gridx=0;
        x.gridy=0;
        x.weightx=1;
        formPanel.add(nameJLabel,x);
        
        x.gridx=1;
        x.weightx=4;
        formPanel.add(nameTextField,x);
        
       
        
    }
    private void BuildcontrolButtonsPanel(){
        controlButtonsPanel=new JPanel();
        add(BorderLayout.SOUTH,controlButtonsPanel);
         Save =new JButton("Save");
        controlButtonsPanel.add(Save);
       
    }

    public JButton getSave() {
        return Save;
    }

    

    
//    public static void main(String[] args) {
//        Form frame1=new Form();
//        frame1.setVisible(true);
//    }
}   
