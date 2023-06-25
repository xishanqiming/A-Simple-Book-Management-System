package LabCode.BaseDAOImplement;

import LabCode.bean.borrow;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface borrowDAO
    //设置接口，面向接口实现类后期更容易修改和维护（例如：可以直接动态注入）
{
    boolean addborrow(borrow borrow);    //添加一条借书记录（其中归还时间设置为null，表示尚未归还）
    boolean updateborrow(borrow borrow);    //修改一条借书记录（即：修改归还时间，表示当前日期归还了一本书）
    borrow getby_borrow_id(int borrow_id);    //borrow_id 可以唯一确定一条记录

    boolean removeby_cno(Integer cno);    //指定按借书记录的编号删除

    public class BorrowFilter
    {    //分类查询：
         //（1）按借书卡的卡号分类；（2）按图书的编号分类；（3）按主管的管理员分类；
         //（4）按已归还分类；（5）按未归还分类；（6）按借出时间的区间分类；

        //（1）按借书卡的卡号分类
        public static List<borrow> filterByCno(List<borrow> borrows, int cno)
        {
            List<borrow> filterBorrows = new ArrayList<>();
            for (borrow br : borrows)
            {
                if (br.getCno() == cno)
                {
                    filterBorrows.add(br);
                }
            }
            return filterBorrows;
        }

        //（2）按图书的编号分类
        public static List<borrow> filterByBno(List<borrow> borrows, int bno)
        {
            List<borrow> filterBorrows = new ArrayList<>();
            for (borrow br : borrows)
            {
                if (br.getBno() == bno)
                {
                    filterBorrows.add(br);
                }
            }
            return filterBorrows;
        }

        //（3）按主管的管理员分类
        public static List<borrow> filterByadmin_id(List<borrow> borrows, int admin_id)
        {
            List<borrow> filterBorrows = new ArrayList<>();
            for (borrow br : borrows)
            {
                if (br.getAdmin_id() == admin_id)
                {
                    filterBorrows.add(br);
                }
            }
            return filterBorrows;
        }

        //（4）按已归还分类：归还时间不为null
        public static List<borrow> filterByIsReturn(List<borrow> borrows, Object o)
        {
            List<borrow> filterBorrows = new ArrayList<>();
            for (borrow br : borrows)
            {
                if (br.getReturn_date() != null)
                {
                    filterBorrows.add(br);
                }
            }
            return filterBorrows;
        }

        //（5）按未还分类：归还时间为null
        public static List<borrow> filterByNotReturn(List<borrow> borrows, Date return_date)
        {
            List<borrow> filterBorrows = new ArrayList<>();
            for (borrow br : borrows)
            {
                if (br.getReturn_date() == null)
                {
                    filterBorrows.add(br);
                }
            }
            return filterBorrows;
        }

        //（6）按借出时间的区间分类
        public static List<borrow> filterByBorrowTimeRange(List<borrow> borrows, Date minDate, Date maxDate)
        {
            List<borrow> filterBorrows = new ArrayList<>();
            for (borrow br : borrows)
            {
                if (!br.getBorrow_date().before(minDate) && !br.getBorrow_date().after(maxDate))
                {
                    filterBorrows.add(br);
                }
            }
            return filterBorrows;
        }
    }

    List<borrow> getAllborrows() throws RuntimeException;    //查询borrow表中所有的借书记录
}
