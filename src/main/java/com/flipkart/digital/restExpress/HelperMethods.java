package com.flipkart.digital.restExpress;

import com.flipkart.digital.DatabaseStore.DBConnection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class HelperMethods {

    public void createNewClub(String club_id, String fsn, String accoutId){
        String query = "insert into club_master(club_id, fsn, account_id) values (?,?,?)";
        try {
            insertOrUpdateRow(query, club_id, fsn, accoutId);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public void joinClub(String account_id, String club_id) {
        String query = "insert into club_members(account_id,club_id) values(?,?)";
        try {
            insertOrUpdateRow(query, account_id, club_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Integer insertOrUpdateRow(String query, Object... values) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DBConnection.INSTANCE.getConnection();

            preparedStatement = connection.prepareStatement(query);
            int i=1;
            for(Object value: values) {
                if(value == null) {
                    i++;
                    continue;
                }
                String typeName = value.getClass().getName();

                if (typeName.equals(String.class.getName()))
                    preparedStatement.setString(i,value+"");
                else if (typeName.equals(Double.class.getName()))
                    preparedStatement.setDouble(i, new Double(value.toString()));
                else if (typeName.equals(Integer.class.getName()))
                    preparedStatement.setInt(i, new Integer(value.toString()));
                i++;
            }


            return preparedStatement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;

    }
    private Boolean isClubIdPresent(String club_id) {
        try {
            String query = "select * from club_master where club_id='"+club_id+"'";
            Connection connection = DBConnection.INSTANCE.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet == null) return false;
            if(resultSet.next()) {
                return true;
            }

        } catch(SQLException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        HelperMethods helperMethods = new HelperMethods();
        System.out.println(helperMethods.isClubIdPresent("1234"));

    }

}
