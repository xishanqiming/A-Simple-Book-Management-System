package LabCode.BaseDAOImplement;

import LabCode.bean.admin;

import java.util.List;

public interface adminDAO
        //设置接口，面向接口实现类后期更容易修改和维护（例如：可以直接动态注入）
{
    String check_password(int admin_id);    //验证管理员登录（根据id查询返回password，然后与用户输入密码的进行比对）
    boolean update_admin(admin admin);    //普通管理员修改，只允许修改自己的所有属性
    boolean su_update_admin(admin admin);    //超级管理员修改，可以修改所有管理员的全部属性
    boolean su_add_admin(admin admin);    //超级管理员插入一个管理员的信息
    boolean su_removeby_admin_id(Integer admin_id);    //指定按管理员id删除
    List<admin> su_getAlladmin() throws RuntimeException;    //超级管理员查询：可以查询全部管理员的全部属性值（包括密码）
    List<admin> getAlladmin() throws RuntimeException;    //用户和普通管理员查询除su外的全部管理员的姓名和联系方式

    //超级管理员可以根据管理员ID查询admin表中的一个管理员
    admin su_getbyid(int admin_id);
}
