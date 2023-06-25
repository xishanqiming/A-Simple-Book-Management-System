package LabCode.test;

import LabCode.bean.book;
import com.alibaba.druid.pool.DruidDataSource;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileIOTest
{
    private static DruidDataSource druidDataSource;

    public static void main(String[] args)
    {
        FileIOTest fileIOTest = new FileIOTest();
        fileIOTest.executeAll();
    }

    public void executeAll()
    {
        initDruidDataSource();
        String filePath = readFilePath();
        List<book> books = readBooksFromFile(filePath);
        if (books != null)
        {
            try {
                insertBooksToDatabase(books);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }


    public static void initDruidDataSource()
    {
        druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/JDBCTest");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("qiming123");
    }

    public static String readFilePath()
    {
        System.out.println("请输入要读取的文件的绝对路径（不包括双引号）: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String filePath = null;
        try
        {
            filePath = bufferedReader.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return filePath;
    }

    public static List<book> readBooksFromFile(String filePath)
    {
        List<book> books = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists() && file.isFile())
        {
            System.out.println("文件已找到，继续进行下一步操作。");
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file)))
            {
                String line;
                int lineNumber = 1;
                while ((line = bufferedReader.readLine()) != null)
                {
                    book book = parseBook(line, lineNumber);
                    if (book != null)
                    {
                        books.add(book);
                    }
                    lineNumber++;
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("文件不存在或不是一个文件。请检查输入的路径。");
        }
        return books;
    }

    public static book parseBook(String line, int lineNumber)
    {
        Pattern pattern = Pattern.compile("^\\(\\s*(\\d+)\\s*,\\s*([^,]+)\\s*,\\s*([^,]+)\\s*,\\s*([^,]+)\\s*,\\s*(\\d+)\\s*,\\s*([^,]+)\\s*,\\s*([\\d.]+)\\s*,\\s*(\\d+)\\s*\\)$");
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            int bno = Integer.parseInt(matcher.group(1));
            String category = matcher.group(2).trim();
            String title = matcher.group(3).trim();
            String press = matcher.group(4).trim();
            int year = Integer.parseInt(matcher.group(5));
            String author = matcher.group(6).trim().trim();
            BigDecimal price = new BigDecimal(matcher.group(7).trim());
            int total = Integer.parseInt(matcher.group(8));

            return new book(bno, category, title, press, year, author, price, total);
        }
        else
        {
            System.out.println("第 " + lineNumber + " 行格式错误: " + line);
            return null;
        }
    }

    public static void insertBooksToDatabase(List<book> books) throws SQLException
    {
        String sql = "INSERT INTO book (bno, category, title, press, year, author, price, total, stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = druidDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {

            for (book book : books)
            {
                preparedStatement.setInt(1, book.getBno());
                preparedStatement.setString(2, book.getCategory());
                preparedStatement.setString(3, book.getTitle());
                preparedStatement.setString(4, book.getPress());
                preparedStatement.setInt(5, book.getYear());
                preparedStatement.setString(6, book.getAuthor());
                preparedStatement.setBigDecimal(7, book.getPrice());
                preparedStatement.setInt(8, book.getTotal());
                preparedStatement.setInt(9, book.getStock());

                preparedStatement.addBatch();
            }

            int[] results = preparedStatement.executeBatch();
            int totalInserted = 0;
            for (int result : results) {
                totalInserted += (result > 0) ? 1 : 0;
            }
            System.out.println("成功插入 " + totalInserted + " 条记录。");
        }
    }
}