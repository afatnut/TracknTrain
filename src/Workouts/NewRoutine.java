/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Workouts;

import Workouts.wsrData.wsr;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AliFetanat
 */
public class NewRoutine {

    JTable t;
    String muscle;

    public void ClearTable() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:Temp");

            String sql = "DELETE FROM Temp";
            PreparedStatement st = con.prepareStatement(sql);
            st.executeUpdate();
            con.close();
        } catch (Exception e) {
        }
    }

    public void QueeryList(JTable Table, String m) {
        this.muscle = m;
        try {
            Class.forName("org.sqlite.JDBC");

            String sql
                    = "SELECT eName, pMuscle FROM MuscleExercises WHERE mGroup=" + "'" + muscle + "'" + " ORDER BY pMuscle";

            Connection con = DriverManager.getConnection("jdbc:sqlite:MuscleExercises");
            //String sql = "SELECT null, eName, pMuscle FROM MuscleExercises WHERE  ORDER BY pMuscle";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            try {

                while (Table.getRowCount() > 0) {
                    ((DefaultTableModel) Table.getModel()).removeRow(0);
                }
                int columns = rs.getMetaData().getColumnCount();
                while (rs.next()) {
                    Object[] row = new Object[columns];
                    for (int i = 1; i <= columns; i++) {
                        row[i - 1] = rs.getObject(i);
                    }
                    ((DefaultTableModel) Table.getModel()).insertRow(rs.getRow() - 1, row);
                }
                con.close();

            } catch (Exception e) {
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void addExercise(JTable Table) {

        try {
            Class.forName("org.sqlite.JDBC");

            Connection con = DriverManager.getConnection("jdbc:sqlite:Temp");

            int row = Table.getSelectedRow();
            String a = (String) Table.getValueAt(row, 0);
            String b = (String) Table.getValueAt(row, 1);

            PreparedStatement st = con.prepareStatement(
                    "INSERT INTO Temp (e,m,p) VALUES (?,?,?)");

            st.setString(1, a);
            st.setString(2, muscle);
            st.setString(3, b);

            st.executeUpdate();

            con.commit();
            con.close();

        } catch (Exception e) {
        }

    }

    public JTable OutputRoutineTable(JTable Routines) {
        try {
            Class.forName("org.sqlite.JDBC");

            String sql = "SELECT m, p, e FROM Temp";

            Connection con = DriverManager.getConnection("jdbc:sqlite:Temp");

            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            try {

                while (Routines.getRowCount() > 0) {
                    ((DefaultTableModel) Routines.getModel()).removeRow(0);
                }
                int columns = rs.getMetaData().getColumnCount();
                while (rs.next()) {
                    Object[] row = new Object[columns];
                    for (int i = 1; i <= columns; i++) {
                        row[i - 1] = rs.getObject(i);
                    }
                    ((DefaultTableModel) Routines.getModel()).insertRow(rs.getRow() - 1, row);
                }
                con.close();

            } catch (Exception e) {
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return Routines;
    }

    public void RemoveExercise(JTable Routine) {

        int m = Routine.getSelectedRow();
        String e = (String) Routine.getValueAt(m, 2);

        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:Temp");

            String sql = "DELETE FROM temp WHERE e= '" + e + "'";
            PreparedStatement st = con.prepareStatement(sql);

            st.executeUpdate();
            con.close();

        } catch (Exception b) {
            JOptionPane.showMessageDialog(null, b);
        }

        this.OutputRoutineTable(Routine);
    }

    public void saveRoutine(String n, JTable Routine) {

        try {
            Class.forName("org.sqlite.JDBC");

            Connection con = DriverManager.getConnection("jdbc:sqlite:savedRoutines");

            int row = Routine.getRowCount();

            for (int i = 0; i < row; i++) {

                String e = (String) Routine.getValueAt(i, 0);
                String m = (String) Routine.getValueAt(i, 1);
                String p = (String) Routine.getValueAt(i, 2);

                PreparedStatement st = con.prepareStatement(
                        "INSERT INTO savedRoutines (Name,Mg,Pm,Ex) VALUES (?,?,?,?)");
                st.setString(1, n);
                st.setString(2, m);
                st.setString(3, p);
                st.setString(4, e);

                st.executeUpdate();
            }

            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }

    public void addData(JTable T, JTable d, int row,wsrData dat, ArrayList <wsrData> x) {
        // T is the eTable, d is dTable
  

            String m = (String) T.getValueAt(row, 0);
            String p = (String) T.getValueAt(row, 1);
            String e = (String) T.getValueAt(row, 2);
            dat=new wsrData(e,p,m);

              for (int co = 0; co < d.getRowCount(); co++) {
                String s = (String) d.getValueAt(co, 0);
                String w = (String) d.getValueAt(co, 1);
                String r = (String) d.getValueAt(co, 2);
                dat.createWSR(s, w, r);
                
              }

    
              
    }
    
    public void getData(JTable T, JTable d,int rowof){
        //Select Table Values out
        /*
         DefaultTableModel model = (DefaultTableModel) d.getModel();
        int rows = model.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        */
        String ex=(String)T.getValueAt(rowof, 2);
        try {
            Class.forName("org.sqlite.JDBC");

            String sql = "SELECT s, w, r  FROM Temp WHERE e=" +" '" +ex+ "' AND s IS NOT NULL ORDER BY s" ;

            Connection con = DriverManager.getConnection("jdbc:sqlite:Temp");

            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            try {

                while (d.getRowCount() > 0) {
                    ((DefaultTableModel) d.getModel()).removeRow(0);
                }
                int columns = rs.getMetaData().getColumnCount();
                while (rs.next()) {
                    Object[] row = new Object[columns];
                    for (int i = 1; i <= columns; i++) {
                        row[i - 1] = rs.getObject(i);
                    }
                    ((DefaultTableModel) d.getModel()).insertRow(rs.getRow() - 1, row);
                }
                con.close();

            } catch (Exception e) {
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        
        
        //Insert Table Values in
    }
    
    
      public void addData2(JTable d, wsrData dat, int row) {
        // T is the eTable, d is dTable
  
              for (int co = 0; co < d.getRowCount(); co++) {
                String s = (String) d.getValueAt(co, 0);
                String w = (String) d.getValueAt(co, 1);
                String r = (String) d.getValueAt(co, 2);
                
                dat.createWSR(s, w, r);
              }

    
              
    }

}
