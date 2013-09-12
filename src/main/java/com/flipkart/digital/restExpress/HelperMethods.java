package com.flipkart.digital.restExpress;

import com.flipkart.digital.DatabaseStore.DBConnection;
import com.flipkart.digital.models.ClubMember;
import com.flipkart.digital.models.MemberDetail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HelperMethods {

    public void createNewClub(String club_name, String fsn, String kind,String account_id){
        String query = "insert into club_master(club_name, fsn, kind) values (?,?,?)";
        try {
            insertOrUpdateRow(query, club_name, fsn, kind);
        } catch (Exception e) {
           e.printStackTrace();
        }

        joinClub(account_id, club_name, "owner");
    }

    public void joinClub(String account_id, String club_name, String role) {
        String query = "insert into club_members(account_id,club_id,role) values(?,?,?)";
        try {
            int club_id = getClubId(club_name);
            insertOrUpdateRow(query, account_id, club_id, role);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getClubId(String club_name) {
        try {
            String query = "select club_id from club_master where club_name='"+club_name+"'";
            Connection connection = DBConnection.INSTANCE.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet == null) return 0;
            if(resultSet.next()) {
                return resultSet.getInt("club_id");
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
        return 0;
    }

    public List<ClubMember> getMembers(String clubId) {
        List<ClubMember> list = new ArrayList<ClubMember>();
        String query = "select u.name,cm.role from club_members cm, users u where club_id='"+clubId+"' and cm.account_id=u.account_id";

        try {
            Connection connection = DBConnection.INSTANCE.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet == null) return null;
            while(resultSet.next()) {
                ClubMember clubMember = new ClubMember();
                clubMember.setName(resultSet.getString(1));
                clubMember.setRole(resultSet.getString(2));
                list.add(clubMember);
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
        return list;
    }

    public String getOrganiser(String clubId) {
        String query = "select name from club_members cm, users u where club_id='"+clubId+"' and cm.role= 'owner' and cm.account_id=u.account_id";
        String organiser = null;
        try {
            Connection connection = DBConnection.INSTANCE.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet == null) return null;
            if(resultSet.next()) {
               organiser = resultSet.getString("name");
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
        return organiser;
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
    public Boolean isClubIdPresent(String club_id) {
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

    public MemberDetail getClubdetails(String account_id, String fsn) {
        String query =" select cmas.club_name, cmem.role, u.name from club_master cmas, club_members cmem, users u where cmem.account_id= '"+account_id+"' and cmas.fsn='"+fsn+"' and cmem.club_id = cmas.club_id and u.account_id = cmem.account_id";

        try {
            Connection connection = DBConnection.INSTANCE.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet == null) return null;
            if (resultSet.next()) {
                MemberDetail memberDetail  = new MemberDetail();
                memberDetail.setName(resultSet.getString(1));
                memberDetail.setRole(resultSet.getString(2));
                return memberDetail;
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
        return null;
    }

    public static void main(String[] args) {
        HelperMethods helperMethods = new HelperMethods();
       helperMethods.joinClub("1","nagas","member");

    }

}
