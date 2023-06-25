package LabCode.BaseDAOImplement;

import LabCode.bean.book;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface bookDAO
    //设置接口，面向接口实现类后期更容易修改和维护（例如：可以直接动态注入）
{
    boolean addbook(book book);    //添加书籍
    boolean updatebook(book book);    //修改书籍信息（一般修改库存余量）
    boolean removebybno(Integer bno);    //指定按书籍编号删除

    //查询book表中的所有书籍的方法
    List<book> getAllbook() throws RuntimeException;    //接口+抽象方法

    //查询book表中一个书籍的方法
    book getbybno(int bno);

    public class BookFilter
    {
        public static List<book> filterByCategory(List<book> books, String category)
        {
            List<book> filteredBooks = new ArrayList<>();
            for (book b : books)
            {
                if (b.getCategory().equalsIgnoreCase(category))
                {
                    filteredBooks.add(b);
                }
            }
            return filteredBooks;
        }

        public static List<book> filterByTitle(List<book> books, String title)
        {
            List<book> filteredBooks = new ArrayList<>();
            for (book b : books)
            {
                if (b.getTitle().equalsIgnoreCase(title))
                {
                    filteredBooks.add(b);
                }
            }
            return filteredBooks;
        }

        public static List<book> filterByPress(List<book> books, String press)
        {
            List<book> filteredBooks = new ArrayList<>();
            for (book b : books)
            {
                if (b.getPress().equalsIgnoreCase(press))
                {
                    filteredBooks.add(b);
                }
            }
            return filteredBooks;
        }

        public static List<book> filterByYearRange(List<book> books, int minYear, int maxYear)
        {
            List<book> filteredBooks = new ArrayList<>();
            for (book b : books)
            {
                if (b.getYear() >= minYear && b.getYear() <= maxYear)
                {
                    filteredBooks.add(b);
                }
            }
            return filteredBooks;
        }

        public static List<book> filterByAuthor(List<book> books, String author)
        {
            List<book> filteredBooks = new ArrayList<>();
            for (book b : books)
            {
                if (b.getAuthor().equalsIgnoreCase(author))
                {
                    filteredBooks.add(b);
                }
            }
            return filteredBooks;
        }

        public static List<book> filterByPriceRange(List<book> books, BigDecimal minPrice, BigDecimal maxPrice)
        {
            List<book> filteredBooks = new ArrayList<>();
            for (book b : books)
            {
                if (b.getPrice().compareTo(minPrice) >= 0 && b.getPrice().compareTo(maxPrice) <= 0)
                {
                    filteredBooks.add(b);
                }
            }
            return filteredBooks;
        }

        public static List<book> filterByBno(List<book> books, int bno)
        {
            List<book> filteredBooks = new ArrayList<>();

            for (book b : books)
            {
                if (b.getBno() == bno)
                {
                    filteredBooks.add(b);
                    break; // 找到对应书号的书籍后，退出循环
                }
            }

            return filteredBooks;
        }
    }
}
