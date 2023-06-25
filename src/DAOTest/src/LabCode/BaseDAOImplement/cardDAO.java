package LabCode.BaseDAOImplement;

import LabCode.bean.card;

import java.util.ArrayList;
import java.util.List;

public interface cardDAO
{
    boolean addcard(card card);    //新建一个借书卡账户（自动分配卡号）
    boolean removebycno(Integer cno);    //根据借书卡号，删除一个借书卡（需要在调用时先检测是否有未归还的图书）
    boolean updatecard(card card);    //修改借书卡信息（如：姓名、院系、所属类别）
    List<card> getAllcard();    //删除方法体
    card getbycno(int cno);    //按类别查询一个借书卡信息（按借书卡号查询、按姓名查询、按院系查询、按所属类别查询）


    public class CardFilter
    {

        //按照院系查询
        public static List<card> filterByDepartment(List<card> cards, String department)
        {
            List<card> filteredCards = new ArrayList<>();
            for (card c : cards)
            {
                if (c.getDepartment().equalsIgnoreCase(department))
                {
                    filteredCards.add(c);
                }
            }
            return filteredCards;
        }

        //按照姓名查询
        public static List<card> filterByName(List<card> cards, String name)
        {
            List<card> filteredCards = new ArrayList<>();
            for (card c : cards)
            {
                if (c.getName().equalsIgnoreCase(name))
                {
                    filteredCards.add(c);
                }
            }
            return filteredCards;
        }

        //按照所属类别查询
        public static List<card> filterByType(List<card> cards, String type)
        {
            List<card> filteredCards = new ArrayList<>();
            for (card c : cards)
            {
                if (c.getType().equalsIgnoreCase(type))
                {
                    filteredCards.add(c);
                }
            }
            return filteredCards;
        }

        //按照借书卡号查询
        public static List<card> filterByCno(List<card> cards, int cno)
        {
            List<card> filteredCards = new ArrayList<>();

            for (card c : cards)
            {
                if (c.getCno() == cno)
                {
                    filteredCards.add(c);
                    break;    //找到对应卡号的借书卡后，退出循环
                }
            }

            return filteredCards;
        }
    }
}
