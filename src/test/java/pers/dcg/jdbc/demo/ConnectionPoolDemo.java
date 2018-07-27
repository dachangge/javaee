package pers.dcg.jdbc.demo;

import pers.dcg.ConnectionPool.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionPoolDemo {
    public static void main(String[] args) {
        MyDataSource instance = MyDataSource.getInstance();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = instance.getConnection();
            ps = con.prepareStatement("select * from account");
            rs = ps.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("线程池当前剩余线程数量：" + instance.getSize());
            instance.addBack(con);
            System.out.println("线程池当前剩余线程数量：" + instance.getSize());
        }
    }
}
