package pers.dcg.jdbc.demo;

import pers.dcg.dao.ProductDao;
import pers.dcg.dao.impl.ProductDaoImpl;
import pers.dcg.util.JDBCUtil;

import java.sql.*;

public class MainTest {
    public static void main(String[] args) {
        ProductDao dao = new ProductDaoImpl();
//        dao.findAll();
//        testDelete(dao, 6);
        testInsert(dao,"香蕉",3.33,5);
    }
    public static  void testDelete(ProductDao dao,int pid){
        dao.deleteById(pid);
    }
    public static  void testInsert(ProductDao dao,String pname, double price,int cno){
        dao.create(pname,price,cno);
    }

}
