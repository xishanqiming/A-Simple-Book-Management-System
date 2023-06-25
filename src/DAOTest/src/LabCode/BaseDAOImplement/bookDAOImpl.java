package LabCode.BaseDAOImplement;

import LabCode.bean.book;

import java.sql.SQLException;
import java.util.List;

public class bookDAOImpl extends BaseDAOImpl implements bookDAO
{    //继承自BaseDAOImpl公共的DAO实现类

    //添加一条记录的方法（添加一本书）
    @Override
    public boolean addbook(book book)    //调用通用的增删改方法，同时重载函数，添加对应的sql
    {
        String sql = "INSERT INTO book VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?)";
        try
        {     //大于0表示添加成功，小于0表示添加失败
            return update(sql, book.getCategory(), book.getTitle(), book.getPress(), book.getYear(), book.getAuthor(), book.getPrice(), book.getTotal(), book.getStock())>0;
        }
        catch (SQLException e)
        {    //SQLException是编译时异常，将编译时异常转化为运行时异常，抛出给上层
            throw new RuntimeException(e);
        }
    }

    //更新书籍的方法（更新存量）
    @Override
    public boolean updatebook(book book)
    {    //修改只允许修改库存余量，其余的不允许修改
        //想修改其他信息，先删除，再重新添加！
        String sql = "update book set stock = ? where bno = ?";
        try
        {
            return  update(sql, book.getStock(), book.getBno()) >0;
        }
        catch (SQLException e)
        {    //编译时异常，将编译时异常转化为运行时异常，抛出给上层
            throw new RuntimeException(e);
        }
    }


    //按书号删除的方法
    @Override
    public boolean removebybno(Integer bno)
    {    //删除时需要按书号删除
        String sql = "delete from book where bno = ?";
        try
        {
            return  update(sql, bno) >0;
        }
        catch (SQLException e)
        {    //编译时异常，将编译时异常转化为运行时异常，抛出给上层
            throw new RuntimeException(e);
        }
    }


    //查询全部书籍的方法
    @Override
    public List<book> getAllbook() throws RuntimeException
    {    //获取book表中全部的书籍
        String sql = "select * from book";
        try
        {
            return getList(book.class, sql);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    //根据书号bno，查询单个书籍的方法
    @Override
    public book getbybno(int bno)
    {
        String sql = "select * from book where bno = ?";
        try
        {
            return getBean(book.class, sql, bno);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
