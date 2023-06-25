package LabCode.BaseDAOImplement;

import LabCode.bean.card;

import java.sql.SQLException;
import java.util.List;

public class cardDAOImpl extends BaseDAOImpl implements cardDAO
{    //继承自BaseDAOImpl公共的DAO实现类

    //新建一个借书卡账户（自动分配借书卡号）
    @Override
    public boolean addcard(card card)    //调用通用的增删改方法，同时重载函数，添加对应的sql
    {
        String sql = "INSERT INTO card VALUES(null, ?, ?, ?)";
        try
        {     //大于0表示添加成功，小于0表示添加失败
            return update(sql, card.getName(), card.getDepartment(), card.getType())>0;
        }
        catch (SQLException e)
        {    //SQLException是编译时异常，将编译时异常转化为运行时异常，抛出给上层
            throw new RuntimeException(e);
        }
    }

    //根据借书卡号，删除一个借书卡（需要在调用时先检测是否有未归还的图书）
    @Override
    public boolean removebycno(Integer cno)
    {    //删除时需要按书号删除
        String sql = "DELETE FROM card WHERE cno = ?";
        try
        {
            return  update(sql, cno) >0;
        }
        catch (SQLException e)
        {    //编译时异常，将编译时异常转化为运行时异常，抛出给上层
            throw new RuntimeException(e);
        }
    }

    //修改借书卡信息（如：姓名、院系、所属类别）
    @Override
    public boolean updatecard(card card)
    {
        String sql = "UPDATE card SET name = ?, department = ?, type = ? WHERE cno = ?";
        try
        {
            return  update(sql, card.getName(), card.getDepartment(), card.getType(), card.getCno()) >0;
        }
        catch (SQLException e)
        {    //编译时异常，将编译时异常转化为运行时异常，抛出给上层
            throw new RuntimeException(e);
        }
    }



    //查询全部借书卡信息
    @Override
    public List<card> getAllcard() throws RuntimeException
    {
        String sql = "SELECT * FROM card";
        try
        {
            return getList(card.class, sql);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public card getbycno(int cno)
    {
        String sql = "select * from card where cno = ?";
        try
        {
            return getBean(card.class, sql, cno);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
