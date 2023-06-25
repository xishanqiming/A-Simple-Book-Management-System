package LabCode.BaseDAOImplement;

import LabCode.utils.JDBCTools;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//每张表最好都有一个自己的DAO类
//实现通用的增、删、改操作
public abstract class BaseDAOImpl    //写成抽象类，表示仅仅被继承而使用
{
    //sql中包含问号参数，需要设置?的值（设置可变参数）
    //如果SQL中有若干个 ?，则调用该方法时，需要传args对应的若干个实际参数
    //如果SQL中没有 ?，则调用该方法时，不需要传args对应的实际参数
    public int update(String sql, Object... args) throws SQLException    //设置可变参数，Object可以接受任何类型
    {
        //获取连接
        Connection connection = JDBCTools.getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);

        //设置 ? 的值：先判断是否需要传参
        if (args != null && args.length>0)    //判断是否传入sql语句
        {
            for (int i=1; i<=args.length; i++)
            {
                pst.setObject(i, args[i-1]);    //虽然?的序号是从1开始，args[i-1]因为数组的下标是从0开始
            }
        }

        //执行SQL语句：增删改对应的方法都是executeUpdate
        int len = pst.executeUpdate();
        //返回值表示成功/失败

        //释放连接
        JDBCTools.freeConnection(connection);

        return len;
    }

    //查询：多个对象？单个对象？

    //查询多个对象的通用方法
    protected <T> List<T> getList(Class<T> clazz, String sql, Object... args) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {    //使用一个集合接受查询的结果集，其中clazz是一个方法参数
        //获取连接：
        Connection connection = JDBCTools.getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);

        //设置 ? 的值：先判断是否需要传参
        if (args != null && args.length>0)    //判断是否传入sql语句
        {
            for (int i=0; i<args.length; i++)
            {
                pst.setObject(i+1, args[i]);
            }
        }

        //返回结果集，遍历结果集（每次返回的是一个对象）
        List<T> list = new ArrayList<>();
        ResultSet rs = pst.executeQuery();

        //结果集的元数据对象
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();    //不知道要查的是哪个表，需要根据结果集进行判定（依据列数）

        while (rs.next())
        {    //每行是一个对象
            T t = clazz.newInstance();   //反射，得到一个T对象
            for (int i=1; i<=columnCount; i++)
            {
                Object value = rs.getObject(i);    //得到每一行的某一列，value是t对象的某个属性值（但不知道具体是哪个对象）
                String columnName = metaData.getColumnName(i);    //获取第i列的列名称（与JavaBean的属性名是一一对应的）
                Field field = clazz.getDeclaredField(columnName);  //反射的Field可以根据属性名得到反射字段，根据列名称获取某张表对应类的某个属性
                field.setAccessible(true);    //反射时允许访问private的
                // 实例字段
                field.set(t, value);    //设置t对象的field属性值
            }
            list.add(t);    //每返回一个对象，就将其添加到集合中
        }
        //释放连接：
        JDBCTools.freeConnection(connection);

        return list;
    }

    //查询单个对象的通用方法
    protected <T> T getBean(Class<T> clazz, String sql, Object... args) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<T> list = getList(clazz, sql, args);
        //如果没有查到对象
        if (list != null && list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }
}