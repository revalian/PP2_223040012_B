package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.MySqlConnection;
import model.JenisMember;


public class JenisMemberDao {
    public int insert(JenisMember jenisMember) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("insert into jenis_member (id, nama) values (?, ?)");
            statement.setString(1, jenisMember.getId());
            statement.setString(2, jenisMember.getNama());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(JenisMember jenisMember) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("update jenis_member set nama = ? where id = ?");
            statement.setString(1, jenisMember.getNama());
            statement.setString(2, jenisMember.getId());

            result = statement.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(JenisMember jenisMember) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("delete from jenis_member where id = ?");
            statement.setString(1, jenisMember.getId());

            result = statement.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<JenisMember> findAll() 
    {
        List<JenisMember> list = new ArrayList<>();
        try(Connection connection = MySqlConnection.getInstance().getConnection();
            Statement statement = connection.createStatement(); ) {
                try(ResultSet resultSet = statement.executeQuery("\"SELECT member.id, member.nama, jenis_member.id AS jenis_member_id, jenis_member.nama AS jenis_member_nama FROM member JOIN jenis_member ON jenis_member.id = member.jenis_member_id\"\r\n" + //
                                        "");){
                    //Retrieving Data
                    while(resultSet.next()) {
                        JenisMember jenisMember = new JenisMember();
                        jenisMember.setId(resultSet.getString("id"));
                        jenisMember.setNama(resultSet.getString("nama"));
                        list.add(jenisMember);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }  catch (SQLException e) {
                e.printStackTrace();
        }
        return list;
    }
}
