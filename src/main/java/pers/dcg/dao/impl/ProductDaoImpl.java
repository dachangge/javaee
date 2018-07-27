package pers.dcg.dao.impl;

import pers.dcg.dao.ProductDao;
import pers.dcg.util.JDBCUtil;

import java.sql.*;

public class ProductDaoImpl implements ProductDao {

    public void create(String pname, double price, int cno) {
        Connection con =null;
        PreparedStatement stat = null;
        try{
            con = JDBCUtil.getConnection();
            String sql = "insert into product values(null,?,?,?)";
            stat = con.prepareStatement(sql);
            stat.setString(1,pname);
            stat.setDouble(2,price);
            stat.setInt(3,cno);
            int res = stat.executeUpdate();
            if(res > 0){
                System.out.println("新增成功");
            }else{
                System.out.println("新增失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtil.CloseJDBC(con, stat);
        }
    }

    public void deleteById(int id) {
        Connection con = null;
        PreparedStatement stat = null;


        try {
            con = JDBCUtil.getConnection();
            String sql = "delete from product where pid = ?";
            stat = con.prepareStatement(sql);
            stat.setInt(1,id);
            int rs = stat.executeUpdate();
            if(rs > 0 ){
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findAll() {
        Connection con = JDBCUtil.getConnection();
        Statement stat = null;
        ResultSet res = null;
        try {
            stat = con.createStatement();
            String sql = "select * from product";
            res = stat.executeQuery(sql);
            while (res.next()){
                int pid =res.getInt("pid");
                String pname = res.getString("pname");
                Double price = res.getDouble("price");
                System.out.println("pid: "+pid+ "、pname:" + pname + "、price:" + price);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.CloseJDBC(con,stat,res);
        }
    }
}
