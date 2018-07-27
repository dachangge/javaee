package pers.dcg.dao;

public interface ProductDao {
    void findAll();
    void deleteById(int id);
    void create(String pname,double price,int cno);
}
