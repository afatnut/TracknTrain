package UserFunctions;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;


public class Register {

    public void Create(String n, String u, String p, String cp)
    {

        if (n.equals("") || u.equals("") || p.equals("") || cp.equals("")) {
            JOptionPane.showMessageDialog(null, "User or Password Should not be Empty");

        } else if (!p.equals(cp)) {
            JOptionPane.showMessageDialog(null, "Password dosn't Match");

        } else {

            try {
                Class.forName("org.sqlite.JDBC");
                try (Connection con = DriverManager.getConnection("jdbc:sqlite:users")) {
                    String sql = "INSERT INTO `users`(`usern`,`upass`,'fullname') VALUES (?,?,?)";
                    PreparedStatement st = con.prepareStatement(sql);
                    st.setString(1, u);
                    st.setString(2, p);
                    st.setString(3, n);

                    st.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Successfully User Created.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    
}
