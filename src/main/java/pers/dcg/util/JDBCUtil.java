package pers.dcg.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {
    private static String driverClass = "";
    private static String url = "";
    private static String user = "";
    private static String password = "";
    static {

        try {
            Properties pro = new Properties();
            InputStream is = pro.getClass().getResourceAsStream("/jdbc_config.properties");
            pro.load(is);
            driverClass = pro.getProperty("driverClass");
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            System.out.println(driverClass);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static Connection getConnection(){
        Connection con = null;
        try {
            Class.forName(driverClass);
            con = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
    public static void CloseJDBC(Connection con, Statement stat , ResultSet res){
        closeRes(res);
        closeStat(stat);
        closeCon(con);
    }
    public static void CloseJDBC(Connection con, Statement stat ){
        closeStat(stat);
        closeCon(con);
    }
    private static void closeCon(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private static  void closeStat(Statement stat){
        if(stat != null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private  static  void closeRes(ResultSet res){
        if(res != null){
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
