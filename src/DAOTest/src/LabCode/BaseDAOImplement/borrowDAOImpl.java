package LabCode.BaseDAOImplement;

import LabCode.bean.borrow;
import java.sql.SQLException;
import java.util.List;

public class borrowDAOImpl extends BaseDAOImpl implements borrowDAO
{
    //添加一条借书记录（其中归还时间设置为null，表示未归还）
    @Override
    public boolean addborrow(borrow borrow)
    {
        String sql = "INSERT INTO borrow(cno, bno, borrow_date, admin_id) VALUES(?, ?, ?, ?)";
        try
        {
            return update(sql, borrow.getCno(), borrow.getBno(), borrow.getBorrow_date(), borrow.getAdmin_id()) > 0;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    //修改借书记录（一般修改归还时间，表示当日归还了一本书）
    @Override
    public boolean updateborrow(borrow borrow)
    {
        String sql = "UPDATE borrow SET return_date = ? WHERE cno = ? AND bno = ?";
        //UPDATE borrow SET return_date = '2023-04-20' WHERE cno = 98800 and bno = 100969;
        try
        {
            return  update(sql, borrow.getReturn_date(), borrow.getCno(), borrow.getBno()) >0;
        }
        catch (SQLException e)
        {    //编译时异常，将编译时异常转化为运行时异常，抛出给上层
            throw new RuntimeException(e);
        }
    }


    //根据borrow_id唯一确定一条借书记录
    @Override
    public borrow getby_borrow_id(int borrow_id)
    {
        String sql = "SELECT * FROM borrow WHERE borrow_id = ?";
        try
        {
            return getBean(borrow.class, sql, borrow_id);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    //按借书记录的编号删除
    @Override
    public boolean removeby_cno(Integer cno)
    {
        String sql = "delete from borrow where cno = ?";
        try
        {
            return  update(sql, cno) >0;
        }
        catch (SQLException e)
        {    //编译时异常，将编译时异常转化为运行时异常，抛出给上层
            throw new RuntimeException(e);
        }
    }


    //查询所有的借书记录
    @Override
    public List<borrow> getAllborrows()
    {
        String sql = "SELECT * FROM borrow";
        try
        {
            return getList(borrow.class, sql);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
