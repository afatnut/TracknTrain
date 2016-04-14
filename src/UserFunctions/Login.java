/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserFunctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author AliFetanat
 */
public class Login {

    public boolean passed;
    //Verify User name and password function
    public void Verify(String u, String p) {

        if (u.equals("") || p.equals(""))
        {
            JOptionPane.showMessageDialog(null, "User or Password Should not be Empty");
        }
        
        else 
        {
            try {
                Class.forName("org.sqlite.JDBC");
                Connection con = DriverManager.getConnection("jdbc:sqlite:users");
                String sql = "select * from users where usern=? and upass=?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, u);
                st.setString(2, p);

                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    
                    
                    try {
                                    JOptionPane.showMessageDialog(null, "Thanks "+ u+ " You are now signed in");
                              passed=true;      
                        } 
                    
                    catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                } 
                else {
                    JOptionPane.showMessageDialog(null, "User or Password Error");
                }
                                    con.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
        }
    }
}