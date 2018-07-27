package pers.dcg.tanscation;

import pers.dcg.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TranscationDemo {
    public static void main(String[] args) {
        test();
    }
    public static void test() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = JDBCUtil.getConnection();
            //关闭自动提交，开启事务
            conn.setAutoCommit(false);
            String sql = "update account set money = money - ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, 100);
            ps.setInt(2,1);
            ps.executeUpdate();

            //npe 错误
            String str = null;
            str.length();

            ps.setDouble(1, -100);
            ps.setInt(2,2);
            ps.executeUpdate();

            //没有出错，提交事务
            conn.commit();
        }catch (Exception e){
            //出错了，回滚事务
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            JDBCUtil.CloseJDBC(conn,ps,rs);
        }
    }
}
