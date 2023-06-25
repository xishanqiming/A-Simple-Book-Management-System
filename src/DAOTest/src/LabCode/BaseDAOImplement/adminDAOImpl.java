package LabCode.BaseDAOImplement;

import LabCode.bean.admin;

import java.sql.SQLException;
import java.util.List;
public class adminDAOImpl extends BaseDAOImpl implements adminDAO
{    //继承自BaseDAOImpl公共的DAO实现类

    //验证管理员登录（根据id查询返回password，然后与用户输入密码的进行比对）
    @Override
    public String check_password(int admin_id)
    {
        String sql = "select admin_password from admin where admin_id = ?";
        try
        {
            admin return_password = getBean(admin.class, sql, admin_id);
            return return_password.getAdmin_password();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    //普通管理员修改（只允许修改自己的所有属性值）
    @Override
    public boolean update_admin(admin admin)
    {    //只允许修改自己的属性值，想修改其他管理员信息，需使用超级管理员登录！
         String sql = "update admin set contact = ? where admin_id = ?";
         try
         {
             return  update(sql, admin.getContact(), admin.getAdmin_id()) >0;
         }
         catch (SQLException e)
         {    //编译时异常，将编译时异常转化为运行时异常，抛出给上层
             throw new RuntimeException(e);
         }
    }

    //用户和普通管理员查询全部管理员（除su外）的姓名和联系方式
    //接口+抽象方法
    @Override
    public List<admin> getAlladmin() throws RuntimeException
    {    //获取admin表中全部管理员的姓名和联系方式
        String sql = "select admin_id, admin_name, contact from admin";
        try
        {
            return getList(admin.class, sql);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    //超级管理员的增、删、改、查询：
    @Override
    public boolean su_add_admin(admin admin)
    {
        String sql = "INSERT INTO admin VALUES(?, ?, ?, ?)";
        try
        {     //大于0表示添加成功，小于0表示添加失败
            return update(sql, admin.getAdmin_id(), admin.getAdmin_password(), admin.getAdmin_name(), admin.getContact())>0;
        }
        catch (SQLException e)
        {    //SQLException是编译时异常，将编译时异常转化为运行时异常，抛出给上层
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean su_removeby_admin_id(Integer admin_id)
    {    //删除时需要按管理员ID删除
        String sql = "DELETE FROM admin WHERE admin_id = ?";
        try
        {
            return  update(sql, admin_id) >0;
        }
        catch (SQLException e)
        {    //编译时异常，将编译时异常转化为运行时异常，抛出给上层
            throw new RuntimeException(e);
        }
    }

    //超管允许修改任意管理员的信息
    @Override
    public boolean su_update_admin(admin admin)
    {
        String sql = "UPDATE admin SET admin_password = ?, admin_name = ?,  contact = ? WHERE admin_id = ?";
        try
        {
            return  update(sql, admin.getAdmin_password(), admin.getAdmin_name(), admin.getContact(), admin.getAdmin_id()) >0;
        }
        catch (SQLException e)
        {    //编译时异常，将编译时异常转化为运行时异常，抛出给上层
            throw new RuntimeException(e);
        }
    }

    //获取全部的管理员信息（包括账号和密码）
    @Override
    public List<admin> su_getAlladmin() throws RuntimeException
    {
        String sql = "SELECT * FROM admin";
        try
        {
            return getList(admin.class, sql);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    //由于管理员可能较少，因此只设置按编号查询
    @Override
    public admin su_getbyid(int admin_id)
    {
        String sql = "SELECT * FROM admin WHERE admin_id = ?";
        try
        {
            return getBean(admin.class, sql, admin_id);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
