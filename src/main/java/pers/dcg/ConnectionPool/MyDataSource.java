package pers.dcg.ConnectionPool;

import pers.dcg.util.JDBCUtil;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MyDataSource implements DataSource {

    private  static MyDataSource myDataSource = null;
    private  List<Connection> conns = new ArrayList<Connection>();

    private MyDataSource(){
        for(int x = 0; x < 10; x++){
            conns.add(JDBCUtil.getConnection());
        }
    }

    public static MyDataSource getInstance() {
        synchronized (MyDataSource.class){
            if(myDataSource == null){
                myDataSource = new MyDataSource();
            }
        }
        return myDataSource;
    }
    public  int getSize() {
        return conns.size();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return  null;
    }

    /**
    * @Description: 将用完后的线程放入线程池
    * @param   con 需要回收的线程
    * @Return:
    * @Author: Mr.Deng
    * @Date: 2018/7/27 17:52
    */
    public  void addBack(Connection con){
        conns.add(con);
    }

    @Override
    public Connection getConnection() throws SQLException {
        //线程用完进行扩容
        System.out.println(conns.size());
        if(conns.size() == 0){
            for(int x = 0; x < 3; x++){
                conns.add(JDBCUtil.getConnection());
            }
        }

        Connection con = conns.remove(0);
        return con;
    }



    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
