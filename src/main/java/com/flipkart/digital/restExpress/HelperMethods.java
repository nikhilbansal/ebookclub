package com.flipkart.digital.restExpress;

import com.flipkart.digital.DatabaseStore.DBConnection;

import java.sql.*;

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

    public static void main(String[] args) {
        HelperMethods helperMethods = new HelperMethods();
        helperMethods.createNewClub("5678","5678");

    }

}
