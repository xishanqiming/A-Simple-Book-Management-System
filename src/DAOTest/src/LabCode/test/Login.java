package LabCode.test;

import LabCode.BaseDAOImplement.*;
import LabCode.bean.admin;
import LabCode.bean.book;
import LabCode.bean.borrow;
import LabCode.bean.card;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Login
{
    private static boolean judge_su = false;    //定义一个布尔变量用于判断是否为超级管理员
    private static int login_admin_id = 0;      //定义一个整型变量用于记录登录管理员的ID

    //显示登录菜单的方法
    private static int displayLoginMenu(Scanner scanner)
    {
        int selectRole;
        while(true)
        {
            //提示用户输入登录身份
            System.out.println("请输入数字1或2，选择您的登录身份。\n1：管理员登录\n2：普通用户登录");
            try
            {
                int input = scanner.nextInt();
                if(input == 1 || input == 2)
                {
                    selectRole = input;
                    break;
                }
                else
                {
                    System.out.println("身份选择输入错误！");
                }
            }
            catch(Exception e)
            {
                System.out.println("身份选择输入错误！");
                scanner.nextLine();
            }
        }
        return selectRole;
    }

    //管理员身份登录菜单的方法
    private static int adminLogin(Scanner scanner)
    {
        adminDAOImpl adminDao = new adminDAOImpl();
        int inputAccount;
        String inputCode;
        while(true)
        {
            //提示用户输入管理员账号和密码
            System.out.println("您已选择管理员身份登录，请输入您的账号和密码：");
            try
            {
                System.out.print("账号：");
                inputAccount = scanner.nextInt();
                System.out.print("密码：");
                inputCode = scanner.next();
            }
            catch(Exception e)
            {
                System.out.println("您的输入格式有误，账号应为全数字格式！");
                scanner.nextLine();
                continue;
            }
            try
            {
                if(inputCode.equals(adminDao.check_password(inputAccount)))
                {
                    System.out.println("密码正确！");
                    login_admin_id = inputAccount;
                    break;
                }
                else
                {
                    System.out.println("密码输入错误！请重新输入密码！");
                }
            }
            catch(Exception e)
            {
                System.out.println("账号不存在或密码输入错误！请重新输入！");
            }
        }
        return inputAccount;
    }

    //用户登录菜单的方法
    private static void userMenu(Scanner scanner)
    {
        while(true)
        {
            //提示用户选择操作
            System.out.println("请输入数字1-3，选择您要执行的操作。\n1：查询当前库存的全部书图书信息\n2：按条件范围查询图书的信息\n3：查询所有管理员的信息及联系方式");
            try
            {
                int input = scanner.nextInt();
                if(input >= 1 && input <= 3)
                {
                    handleUserAction(scanner, input);
                    if(exitOrContinue(scanner))
                    {
                        break;
                    }
                }
                else
                {
                    System.out.println("用户操作输入错误！");
                }
            }
            catch(Exception e)
            {
                System.out.println("用户操作输入错误！");
                scanner.nextLine();
            }
        }
    }

    //处理用户操作：查询全部图书、按条件查询图书、查询管理员联系电话
    private static void handleUserAction(Scanner scanner, int input)
    {
        bookDAOImpl bookDao = new bookDAOImpl();
        adminDAOImpl adminDao = new adminDAOImpl();
        switch(input)
        {
            case 1 ->
            {
                System.out.println("当前数据库中全部库存图书的信息如下：");
                List <book> allBook = bookDao.getAllbook();
                allBook.forEach(System.out::println);
            }
            case 2 ->
            {
                while (true)
                {
                    List <book> books = bookDao.getAllbook();
                    System.out.println("请输入数字1-8，选择查询图书的筛选条件：");
                    System.out.println("1. 按图书类别查询");
                    System.out.println("2. 按书名查询");
                    System.out.println("3. 按出版社查询");
                    System.out.println("4. 按年份区间查询");
                    System.out.println("5. 按作者查询");
                    System.out.println("6. 按价格区间查询");
                    System.out.println("7. 按书号查询（推荐）");
                    System.out.println("8. 退出当前查询菜单");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // 清除换行符

                    if (choice == 8)
                    {
                        break;
                    }

                    List<book> filteredBooks;
                    switch (choice)
                    {
                        case 1 ->
                        {
                            System.out.print("请输入图书类别：");
                            String category = scanner.nextLine();
                            filteredBooks = bookDAO.BookFilter.filterByCategory(books, category);
                        }
                        case 2 ->
                        {
                            System.out.print("请输入书名：");
                            String title = scanner.nextLine();
                            filteredBooks = bookDAO.BookFilter.filterByTitle(books, title);
                        }
                        case 3 ->
                        {
                            System.out.print("请输入出版社：");
                            String press = scanner.nextLine();
                            filteredBooks = bookDAO.BookFilter.filterByPress(books, press);
                        }
                        case 4 ->
                        {
                            System.out.print("请输入最小年份：");
                            int minYear = scanner.nextInt();
                            System.out.print("请输入最大年份：");
                            int maxYear = scanner.nextInt();
                            scanner.nextLine(); // 清除换行符
                            filteredBooks = bookDAO.BookFilter.filterByYearRange(books, minYear, maxYear);
                        }
                        case 5 ->
                        {
                            System.out.print("请输入作者：");
                            String author = scanner.nextLine();
                            filteredBooks = bookDAO.BookFilter.filterByAuthor(books, author);
                        }
                        case 6 ->
                        {
                            System.out.print("请输入最低价格：");
                            BigDecimal minPrice = scanner.nextBigDecimal();
                            System.out.print("请输入最高价格：");
                            BigDecimal maxPrice = scanner.nextBigDecimal();
                            scanner.nextLine(); // 清除换行符
                            filteredBooks = bookDAO.BookFilter.filterByPriceRange(books, minPrice, maxPrice);
                        }
                        case 7 ->
                        {
                            System.out.print("请输入书号：");
                            int bno = scanner.nextInt();
                            scanner.nextLine(); // 清除换行符
                            filteredBooks = bookDAO.BookFilter.filterByBno(books, bno);
                        }
                        default ->
                        {
                            System.out.println("无效选项，请重新输入！");
                            continue;
                        }
                    }

                    System.out.println("筛选结果：");
                    for (book b : filteredBooks)
                    {
                        System.out.println(b);
                    }
                }
            }
            case 3 ->
            {
                System.out.println("全部管理员的姓名及其联系方式如下：");
                List<admin> all_admin = adminDao.getAlladmin();
                all_admin.forEach(System.out::println);
            }
            default -> System.out.println("用户操作输入错误！");
        }
    }

    //退出请求询问菜单
    private static boolean exitOrContinue(Scanner scanner)
    {
        while(true)
        {
            System.out.println("请输入数字1或2，选择退出登录或返回上级查询目录。\n1：退出登录\n2：返回上级查询目录");
            try
            {
                int structureInput = scanner.nextInt();
                if(structureInput == 1)
                {
                    return true;
                }
                else if(structureInput == 2)
                {
                    return false;
                }
                else
                {
                    System.out.println("返回菜单输入错误！");
                }
            }
            catch(Exception e)
            {
                System.out.println("返回菜单输入错误！");
                scanner.nextLine();
            }
        }
    }

    //管理员操作菜单
    private static void displayAdminMenu(Scanner scanner, int inputAccount)
    {
        if(inputAccount == 1)
        {
            judge_su = true;
            System.out.println("欢迎您，您已以超级管理员（su）身份登录！");
        }
        else
        {
            System.out.println("欢迎您，您已以管理员身份登录！");
        }
        while (true)
        {
            System.out.println("请输入数字1-4，选择您要执行的操作。");
            System.out.println("1：对图书进行操作");
            System.out.println("2：对借书记录进行操作");
            System.out.println("3：对借书卡进行操作");
            System.out.println("4：对管理员进行操作");

            try
            {
                int input = scanner.nextInt();
                if (input >= 1 && input <= 4)
                {
                    switch (input)
                    {
                        case 1 -> handleBookOperations(scanner);
                        case 2 -> handleBorrowRecordOperations(scanner);
                        case 3 -> handleBorrowCardOperations(scanner);
                        case 4 -> handleAdminOperations(scanner, inputAccount);
                    }
                    if (exitOrContinue(scanner))
                    {
                        break;
                    }
                }
                else
                {
                    System.out.println("管理员操作输入错误！");
                }
            }
            catch (Exception e)
            {
                System.out.println("管理员操作输入错误！");
                scanner.nextLine();
            }
        }
    }

    //管理员（包括超级管理员）的操作方法：
    //对图书进行操作
    private static void handleBookOperations(Scanner scanner)
    {
        bookDAOImpl bookDao = new bookDAOImpl();
        while(true)
        {
            System.out.println("请输入数字1-5，选择您要对图书执行的操作。");
            System.out.println("1：插入单本图书");
            System.out.println("2：批量插入图书");
            System.out.println("3：按书号删除图书");
            System.out.println("4：修改图书信息");
            System.out.println("5：查询图书信息");
            System.out.println("6：退出当前查询菜单");

            int input = scanner.nextInt();

            if (input == 6)
            {
                break;
            }
            if (input >= 1 && input <= 6)
            {
                switch (input)
                {
                    case 1 ->
                    {
                        //插入单本图书
                        Scanner assert_book = new Scanner(System.in);
                        System.out.print("请输入要插入的图书的类别：");
                        String b_category = assert_book.next();
                        System.out.print("请输入要插入的图书的名称：");
                        String b_title= assert_book.next();
                        System.out.print("请输入要插入的图书的出版社：");
                        String b_press = assert_book.next();
                        System.out.print("请输入要插入的图书的出版年份：");
                        int b_year = assert_book.nextInt();
                        System.out.print("请输入要插入的图书的作者：");
                        String b_author = assert_book.next();
                        System.out.print("请输入要插入的图书的价格：");
                        BigDecimal b_price = assert_book.nextBigDecimal();
                        System.out.print("请输入要插入的图书的总数：");
                        int b_total = assert_book.nextInt();

                        book book = new book(b_category, b_title, b_press, b_year, b_author, b_price, b_total);

                        bookDAOImpl dao = new bookDAOImpl();
                        boolean result = dao.addbook(book);    //依据boolean类型的返回值来判断是否添加成功
                        System.out.println(result?"添加成功":"添加失败");
                    }
                    case 2 ->
                    {
                        //调用LabCode包中的FileIOTest类
                        FileIOTest input_file = new FileIOTest();
                        input_file.executeAll();
                    }

                    case 3 ->
                    {
                        //按书号删除图书
                        Scanner delete_book = new Scanner(System.in);
                        System.out.print("请输入希望删除的图书的图书编号：");
                        Integer bno = delete_book.nextInt();
                        bookDAOImpl dao = new bookDAOImpl();
                        boolean result = dao.removebybno(bno);
                        System.out.println(result?"修改成功":"修改失败");
                    }

                    case 4 ->
                    {
                        //修改图书信息（只允许修改库存量/总量）
                        //如果想修改图书的基本信息（如：名称、作者、出版社、出版时间、价格），则必须删除旧书，另添加新书！
                        bookDAOImpl dao = new bookDAOImpl();
                        Scanner alter_book = new Scanner(System.in);
                        System.out.print("请输入要修改的图书编号：");
                        int bno = alter_book.nextInt();
                        book book = dao.getbybno(bno);
                        System.out.print("请输入修改后图书的库存量：");
                        int b_stock = alter_book.nextInt();    //如果不想修改，可以直接输入回车结束会话
                        if (b_stock<0)
                            System.out.println("修改库存量错误，库存量不能为负数！");
                        else if (b_stock > book.getTotal())    //修改后库存量若大于总数量，则相应修改总数量
                            System.out.println("修改库存量错误，库存量不能大于书图书总数，请先修改图书总数！");
                        else
                        {
                            book = new book(book.getBno(), book.getCategory(), book.getTitle(), book.getPress(), book.getYear(), book.getAuthor(), book.getPrice(), book.getTotal(), b_stock);    //用修改后新的数据重新new对象
                            boolean result = dao.updatebook(book);
                            System.out.println(result ? "修改成功" : "修改失败");
                        }
                    }
                    case 5 ->
                    {
                        //查询图书信息（包括查询全部和按条件查询）
                        while (true)
                        {
                            List <book> books = bookDao.getAllbook();
                            System.out.println("请输入数字1-9，选择查询图书的筛选条件：");
                            System.out.println("1. 按图书类别查询");
                            System.out.println("2. 按书名查询");
                            System.out.println("3. 按出版社查询");
                            System.out.println("4. 按年份区间查询");
                            System.out.println("5. 按作者查询");
                            System.out.println("6. 按价格区间查询");
                            System.out.println("7. 按书号查询（推荐）");
                            System.out.println("8. 查询当前库存的全部图书信息");
                            System.out.println("9. 退出当前查询菜单");
                            int choice = scanner.nextInt();
                            scanner.nextLine(); // 清除换行符

                            if (choice == 9)
                            {
                                break;
                            }

                            List<book> filteredBooks = null;
                            switch (choice)
                            {
                                case 1 ->
                                {
                                    System.out.print("请输入图书类别：");
                                    String category = scanner.nextLine();
                                    filteredBooks = bookDAO.BookFilter.filterByCategory(books, category);
                                }
                                case 2 ->
                                {
                                    System.out.print("请输入书名：");
                                    String title = scanner.nextLine();
                                    filteredBooks = bookDAO.BookFilter.filterByTitle(books, title);
                                }
                                case 3 ->
                                {
                                    System.out.print("请输入出版社：");
                                    String press = scanner.nextLine();
                                    filteredBooks = bookDAO.BookFilter.filterByPress(books, press);
                                }
                                case 4 ->
                                {
                                    System.out.print("请输入最小年份：");
                                    int minYear = scanner.nextInt();
                                    System.out.print("请输入最大年份：");
                                    int maxYear = scanner.nextInt();
                                    scanner.nextLine(); // 清除换行符
                                    filteredBooks = bookDAO.BookFilter.filterByYearRange(books, minYear, maxYear);
                                }
                                case 5 ->
                                {
                                    System.out.print("请输入作者：");
                                    String author = scanner.nextLine();
                                    filteredBooks = bookDAO.BookFilter.filterByAuthor(books, author);
                                }
                                case 6 ->
                                {
                                    System.out.print("请输入最低价格：");
                                    BigDecimal minPrice = scanner.nextBigDecimal();
                                    System.out.print("请输入最高价格：");
                                    BigDecimal maxPrice = scanner.nextBigDecimal();
                                    scanner.nextLine(); // 清除换行符
                                    filteredBooks = bookDAO.BookFilter.filterByPriceRange(books, minPrice, maxPrice);
                                }
                                case 7 ->
                                {
                                    System.out.print("请输入书号：");
                                    int bno = scanner.nextInt();
                                    scanner.nextLine(); // 清除换行符
                                    filteredBooks = bookDAO.BookFilter.filterByBno(books, bno);
                                }
                                case 8 ->
                                {
                                    System.out.println("当前数据库中全部库存图书的信息如下：");
                                    List<book> allBook = bookDao.getAllbook();
                                    allBook.forEach(System.out::println);
                                }
                                default ->
                                {
                                    System.out.println("无效选项，请重新输入！");
                                    continue;
                                }
                            }

                            System.out.println("筛选结果：");
                            for (book b : filteredBooks)
                            {
                                System.out.println(b);
                            }
                        }
                    }
                }
                if (exitOrContinue(scanner))
                {
                    break;
                }
            }
            else
            {
                System.out.println("选择图书操作输入错误！");
            }
        }
    }

    //对借书记录进行操作
    private static void handleBorrowRecordOperations(Scanner scanner)
    {
        borrowDAOImpl borrowDao = new borrowDAOImpl();
        while(true)
        {
            System.out.println("请输入数字1-4，选择您要对借书记录执行的操作。");
            System.out.println("1：借出一本书");
            System.out.println("2：归还一本书");
            System.out.println("3：查询数据库中所有的借书记录");
            System.out.println("4：查询所有已归还的借书记录");
            System.out.println("5：查询所有未归还的借书记录");
            System.out.println("6：根据借书证编号查找借书记录");
            System.out.println("7：根据图书编号查找借书记录");
            System.out.println("8：按主管的管理员分类查询借书记录");
            System.out.println("9：按借出时间的区间分类查询借书记录");
            System.out.println("10：退出当前查询菜单");

            int input_BorrowSelect = scanner.nextInt();
            if(input_BorrowSelect == 10)
            {
                break;
            }
            if (input_BorrowSelect >= 1 && input_BorrowSelect <= 9)
            {
                switch (input_BorrowSelect)
                {
                    case 1 ->
                    {
                        //借出一本书（添加一个借书记录）
                        //需要判断当前图书的剩余库存量是否为0，为0则不允许外借！
                        Scanner assert_borrow = new Scanner(System.in);
                        System.out.print("请输入借出者的借书卡的卡号：");
                        int br_cno = assert_borrow.nextInt();
                        System.out.print("请输入要借出的图书编号：");
                        int br_bno = assert_borrow.nextInt();

                        bookDAOImpl bookDao = new bookDAOImpl();
                        List<book> filteredBooks;
                        List<book> books = bookDao.getAllbook();
                        filteredBooks = bookDAO.BookFilter.filterByBno(books, br_bno);
                        book singleBook = filteredBooks.get(0);

                        if (singleBook.getStock() <= 0)
                        {
                            System.out.println("对不起，您要借出的书籍目前库存量为0，无法借出！");
                        }

                        else
                        {
                            System.out.print("请输入当前新建借书记录的借出时间：（格式：YYYY-MM-DD）");
                            String dateString = assert_borrow.next();    //设置日期格式
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date br_borrow_date = null;
                            try
                            {
                                br_borrow_date = dateFormat.parse(dateString);
                            }
                            catch (ParseException e)
                            {
                                System.out.println("输入日期的格式不正确，请按照正确的格式输入（如：2023-05-10）");
                            }

                            System.out.print("请输入当前新建借书记录的负责管理员的ID：");
                            int br_admin_id = assert_borrow.nextInt();
                            borrow borrow = new borrow(br_cno, br_bno, br_borrow_date, br_admin_id);
                            borrowDAOImpl dao = new borrowDAOImpl();
                            boolean result = dao.addborrow(borrow);    //依据boolean类型的返回值来判断是否添加成功
                            System.out.println(result ? "一条借出记录添加成功" : "一条借出记录添加失败");

                            if (result == true)    //如果添加成功，则book表中对应书籍的库存量-1
                            {
                                bookDAOImpl up_dao = new bookDAOImpl();
                                book book = up_dao.getbybno(br_bno);
                                int b_stock = book.getStock()-1;
                                book = new book(book.getBno(), book.getCategory(), book.getTitle(), book.getPress(), book.getYear(), book.getAuthor(), book.getPrice(), book.getTotal(), b_stock);    //用修改后新的数据重新new对象
                                boolean up_result = up_dao.updatebook(book);
                                System.out.println("借出后当前该图书的库存量为：" + (singleBook.getStock()-1));
                            }
                        }
                    }
                    case 2 ->
                    {
                        //归还一本书：如果该书在已借书籍列表内, 则还书成功, 同时库存加一；否则输出出错信息
                        //即：如果bno和cno确定的借书记录的归还时间不为空，则还书失败
                        //两次筛选：先筛选bno和cno，再筛选return_date属性值为空的记录
                        //即：只修改借书记录的归还时间（return_date）字段的值
                        Scanner input = new Scanner(System.in);
                        System.out.print("请输入归还者的借书卡的卡号：");
                        int rt_cno = input.nextInt();
                        input.nextLine();
                        System.out.print("请输入要归还的图书编号：");
                        int rt_bno = input.nextInt();
                        input.nextLine();

                        borrowDAOImpl rt_borrowDao = new borrowDAOImpl();
                        List<borrow> filteredBorrows;
                        List<borrow> borrows = rt_borrowDao.getAllborrows();
                        filteredBorrows = borrowDAO.BorrowFilter.filterByCno(borrows, rt_cno);
                        filteredBorrows = borrowDAO.BorrowFilter.filterByBno(filteredBorrows, rt_bno);
                        filteredBorrows = borrowDAO.BorrowFilter.filterByNotReturn(filteredBorrows, null);
                        boolean hasUnreturnedBook = !filteredBorrows.isEmpty();

                        System.out.println("hasUnreturnedBook: " + hasUnreturnedBook);
                        if (hasUnreturnedBook)
                        {
                            borrow singleBorrow = filteredBorrows.get(0);
                            Date rt_return_date = null;
                            do
                            {
                                System.out.print("请输入当前归还书籍的归还时间：（格式：YYYY-MM-DD）");
                                String dateString = input.nextLine();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                try
                                {
                                    rt_return_date = dateFormat.parse(dateString);
                                }
                                catch (ParseException e)
                                {
                                    System.out.println("输入日期的格式不正确，请按照正确的格式输入（如：2023-05-10）");
                                }
                            }
                            while (rt_return_date == null);

                            borrow updatedBorrow = new borrow(singleBorrow.getBorrow_id(), singleBorrow.getCno(), singleBorrow.getBno(), singleBorrow.getBorrow_date(), rt_return_date, singleBorrow.getAdmin_id());
                            boolean updateResult = borrowDao.updateborrow(updatedBorrow);
                            System.out.println(updateResult ? "一条归还记录更新成功" : "一条归还记录更新失败");

                            System.out.println("updateResult: " + updateResult);
                            if (updateResult)
                            {
                                bookDAOImpl up_dao = new bookDAOImpl();
                                book book = up_dao.getbybno(rt_bno);
                                int b_stock = book.getStock() + 1;
                                book = new book(book.getBno(), book.getCategory(), book.getTitle(), book.getPress(), book.getYear(), book.getAuthor(), book.getPrice(), book.getTotal(), b_stock);
                                boolean up_result = up_dao.updatebook(book);
                                System.out.println("归还后当前该图书的库存量为：" + (book.getStock() + 1));
                            }
                        }
                        else
                        {
                            System.out.println("对不起，您没有尚未归还的图书，无法进行归还操作！");
                        }
                        input.nextLine();
                    }
                    case 3 ->
                    {
                        //查询数据库中所有的借书记录
                        System.out.println("当前数据库中全部库存图书的信息如下：");
                        List<borrow> allBorrow = borrowDao.getAllborrows();
                        allBorrow.forEach(System.out::println);
                    }
                    case 4 ->
                    {
                        // 查询所有已归还的借书记录
                        List<borrow> allBorrows = borrowDao.getAllborrows();
                        List<borrow> returnedBorrows = borrowDAO.BorrowFilter.filterByIsReturn(allBorrows, null);
                        System.out.println("所有已归还的借书记录：");
                        for (borrow br : returnedBorrows)
                        {
                            System.out.println(br.toString());
                        }
                    }
                    case 5 ->
                    {
                        // 查询所有未归还的借书记录
                        List<borrow> allBorrows = borrowDao.getAllborrows();
                        List<borrow> unreturnedBorrows = borrowDAO.BorrowFilter.filterByNotReturn(allBorrows, null);
                        System.out.println("所有未归还的借书记录：");
                        for (borrow br : unreturnedBorrows)
                        {
                            System.out.println(br.toString());
                        }
                    }
                    case 6 ->
                    {
                        //根据借书证编号查找借书记录
                        Scanner input = new Scanner(System.in);
                        System.out.print("请输入要查询的借书证编号：");
                        int search_cno = input.nextInt();

                        List<borrow> allBorrows = borrowDao.getAllborrows();
                        List<borrow> searchedBorrows = borrowDAO.BorrowFilter.filterByCno(allBorrows, search_cno);

                        System.out.println("当前借书证名下的借书记录如下：");
                        for (borrow br : searchedBorrows)
                        {
                            System.out.println(br.toString());
                        }
                    }
                    case 7 ->
                    {
                        //根据图书编号查找借书记录
                        Scanner input = new Scanner(System.in);
                        System.out.print("请输入要查询的图书编号：");
                        int search_bno = input.nextInt();

                        List<borrow> allBorrows = borrowDao.getAllborrows();
                        List<borrow> searchedBorrows = borrowDAO.BorrowFilter.filterByBno(allBorrows, search_bno);

                        System.out.println("当前图书的全部借阅记录如下：");
                        for (borrow br : searchedBorrows)
                        {
                            System.out.println(br.toString());
                        }
                    }
                    case 8 ->
                    {
                        //按主管的管理员分类查询借书记录
                        Scanner input = new Scanner(System.in);
                        System.out.print("请输入要查询的管理员ID：");
                        int search_admin_id = input.nextInt();

                        List<borrow> allBorrows = borrowDao.getAllborrows();
                        List<borrow> searchedBorrows = borrowDAO.BorrowFilter.filterByadmin_id(allBorrows, search_admin_id);

                        System.out.println("当前管理员负责的全部借书记录如下：");
                        for (borrow br : searchedBorrows)
                        {
                            System.out.println(br.toString());
                        }
                    }
                    case 9 ->
                    {
                        //按借出时间的区间分类查询借书记录
                        Scanner input = new Scanner(System.in);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date startDate = null;
                        Date endDate = null;
                        do
                        {
                            System.out.print("请输入要查询借出时间的区间起始日期：（格式：YYYY-MM-DD）");
                            String startString = input.nextLine();
                            try
                            {
                                startDate = dateFormat.parse(startString);
                            }
                            catch (ParseException e)
                            {
                                System.out.println("输入日期的格式不正确，请按照正确的格式输入（如：2023-05-10）");
                            }
                        }
                        while (startDate == null);

                        do
                        {
                            System.out.print("请输入要查询借出时间的区间结束日期：（格式：YYYY-MM-DD）");
                            String endString = input.nextLine();
                            try
                            {
                                endDate = dateFormat.parse(endString);
                            }
                            catch (ParseException e)
                            {
                                System.out.println("输入日期的格式不正确，请按照正确的格式输入（如：2023-05-10）");
                            }
                        }
                        while (endDate == null);

                        List<borrow> allBorrows = borrowDao.getAllborrows();
                        List<borrow> searchedBorrows = borrowDAO.BorrowFilter.filterByBorrowTimeRange(allBorrows, startDate, endDate);

                        System.out.println("按借出时间区间查询到的借书记录如下：");
                        for (borrow br : searchedBorrows)
                        {
                            System.out.println(br.toString());
                        }
                    }
                }

                if (exitOrContinue(scanner))
                {
                    break;
                }
            }
            else
            {
                System.out.println("选择借书记录操作输入错误！");
            }
        }
    }

    //对借书卡进行操作
    private static void handleBorrowCardOperations(Scanner scanner)
    {
        cardDAOImpl cardDao = new cardDAOImpl();
        while(true)
        {
            System.out.println("请输入数字1-4，选择您要对借书卡执行的操作。");
            System.out.println("1：新建一个借书卡账户");
            System.out.println("2：删除一个借书卡账户");
            System.out.println("3：修改借书卡账户信息");
            System.out.println("4：分类查询借书卡账户");
            System.out.println("5： 退出当前查询菜单");

            int input_handleBorrow = scanner.nextInt();
            if(input_handleBorrow==5)
            {
                break;
            }
            if (input_handleBorrow >= 1 && input_handleBorrow <= 4)
            {
                switch (input_handleBorrow)
                {
                    case 1 ->
                    {
                        //新建一个借书卡账户
                        Scanner assert_card = new Scanner(System.in);
                        System.out.print("请输入新建借书卡的持有者姓名：");
                        String c_name = assert_card.next();
                        System.out.print("请输入新建借书卡的持有者所属院系：");
                        String c_department= assert_card.next();
                        System.out.print("请输入新建借书卡的持有者的类别（U-本科生，G-研究生，T-教授，O-职工）");
                        String c_type = assert_card.next();
                        card card = new card(c_name, c_department, c_type);
                        cardDAOImpl dao = new cardDAOImpl();
                        boolean result = dao.addcard(card);    //依据boolean类型的返回值来判断是否添加成功
                        System.out.println(result?"添加成功":"添加失败");
                    }
                    case 2 ->
                    {
                        // 删除一个借书卡账户（首先需要检查该账户是否有未归还的图书）
                        // 按卡号删除借书卡账户
                        Scanner delete_card = new Scanner(System.in);
                        System.out.print("请输入希望删除的借书卡编号：");
                        int cno = delete_card.nextInt();
                        cardDAOImpl dao = new cardDAOImpl();

                        borrowDAOImpl borrowDao = new borrowDAOImpl();
                        List<borrow> allBorrows = borrowDao.getAllborrows();
                        List<borrow> unreturnedBorrows = borrowDAO.BorrowFilter.filterByCno(allBorrows, cno);
                        unreturnedBorrows = borrowDAO.BorrowFilter.filterByNotReturn(unreturnedBorrows, null);

                        if (!unreturnedBorrows.isEmpty())
                        {    // 如果有尚未归还的书籍，则阻止删除，并返回提示信息！
                             // 指出尚未归还书籍的信息（对borrow表进行查询来实现）
                            System.out.println("无法删除借书卡，因为该账户尚有未归还的图书：");
                            for (borrow br : unreturnedBorrows)
                            {
                                System.out.println(br.toString());
                            }
                        }
                        else
                        {
                            //需要先删borrow，再删card
                            borrowDAOImpl del_dao = new borrowDAOImpl();
                            boolean borrow_result = del_dao.removeby_cno(cno);
                            System.out.println(borrow_result?"图书记录删除成功":"图书记录删除失败");
                            boolean result = dao.removebycno(cno);
                            System.out.println(result ? "借书卡删除成功" : "借书卡删除失败");
                        }
                    }
                    case 3 ->
                    {
                        //修改借书卡账户信息（修改姓名、部门、类别）
                        cardDAOImpl dao = new cardDAOImpl();
                        Scanner alter_card = new Scanner(System.in);
                        System.out.print("请输入要修改的借书卡的编号：");
                        int cno = alter_card.nextInt();
                        card card = dao.getbycno(cno);
                        System.out.print("请输入修改后借书卡所属者的姓名：");
                        String c_name = alter_card.next();
                        System.out.print("请输入修改后借书卡所属者的院系：");
                        String c_department = alter_card.next();
                        System.out.print("请输入修改后借书卡所属者的类别（U-本科生，G-研究生，T-教授，O-职工）：");
                        String c_type = alter_card.next();
                        card.setName(c_name);
                        card.setDepartment(c_department);
                        card.setType(c_type);
                        boolean result = dao.updatecard(card);    //使用updatecard方法更新记录
                        System.out.println(result ? "修改成功" : "修改失败");
                    }
                    case 4 ->
                    {
                        //分类查询借书卡账户（包括查询全部和按条件查询）
                        while (true)
                        {
                            List <card> cards = cardDao.getAllcard();
                            System.out.println("请输入数字1-6，选择查询借书卡账户的筛选条件：");
                            System.out.println("1：按持卡人所属学院查询");
                            System.out.println("2：按持卡人所属类别查询");
                            System.out.println("3：按持卡人姓名查询");
                            System.out.println("4：按借书卡的卡号查询（推荐）");
                            System.out.println("5：查询当前全部借书卡信息");
                            System.out.println("6：退出当前查询菜单");
                            int choice = scanner.nextInt();
                            scanner.nextLine(); // 清除换行符

                            if (choice == 6)
                            {
                                break;
                            }

                            List<card> filteredCards = null;

                            switch (choice)
                            {
                                case 1:
                                    System.out.print("请输入持卡人所属学院：");
                                    String department = scanner.nextLine();
                                    filteredCards = cardDAO.CardFilter.filterByDepartment(cards, department);
                                    break;
                                case 2:
                                    System.out.print("请输入持卡人所属类别：（U-本科生，G-研究生，T-教授，O-职工）");
                                    String type = scanner.nextLine();
                                    filteredCards = cardDAO.CardFilter.filterByType(cards, type);
                                    break;
                                case 3:
                                    System.out.print("请输入持卡人的姓名：");
                                    String name = scanner.nextLine();
                                    filteredCards = cardDAO.CardFilter.filterByName(cards, name);
                                    break;
                                case 4:
                                    System.out.print("请输入借书卡的卡号：");
                                    int cno = scanner.nextInt();
                                    scanner.nextLine(); // 清除换行符
                                    filteredCards = cardDAO.CardFilter.filterByCno(cards, cno);
                                    break;
                                case 5:
                                    System.out.println("当前数据库中的全部借书卡信息如下：");
                                    List<card> allCard = cardDao.getAllcard();
                                    allCard.forEach(System.out::println);
                                    break;
                                default:
                                    System.out.println("无效选项，请重新输入！");
                                    continue;
                            }

                            System.out.println("筛选结果：");
                            for (card c : filteredCards)
                            {
                                System.out.println(c);
                            }
                        }
                    }
                }
                if (exitOrContinue(scanner))
                {
                    break;
                }
            }
            else
            {
                System.out.println("选择借书卡操作输入错误！");
            }
        }
    }

    //对管理员进行操作（区分管理员和超管）
    private static void handleAdminOperations(Scanner scanner, int inputAccount)
    {
        adminDAOImpl adminDao = new adminDAOImpl();
        while(true)
        {
            List <admin> admins = adminDao.getAlladmin();
            List <admin> su_admins = adminDao.su_getAlladmin();
            System.out.println("请输入数字1-5，选择您要对管理员执行的操作。");
            System.out.println("1：新建一个管理员账号（仅限超管）");
            System.out.println("2：删除一个管理员账号（仅限超管）");
            System.out.println("3：修改其他管理员账号（仅限超管）");
            System.out.println("4：修改自己管理员账号的信息");
            System.out.println("5：查询全体管理员账号的信息");
            System.out.println("6：退出当前查询菜单");

            int input = scanner.nextInt();
            if (input == 6)
            {
                break;
            }
            if (input >= 1 && input <= 6)
            {
                switch (input)
                {
                    case 1 ->
                    {
                        //新建一个管理员账号（仅限超管）
                        if (judge_su)
                        {
                            Scanner assert_admin = new Scanner(System.in);
                            System.out.print("请输入新建管理员的账号ID（全数字）：");
                            int a_admin_id = assert_admin.nextInt();
                            System.out.print("请输入新建管理员的账号密码：");
                            String a_admin_password = assert_admin.next();
                            System.out.print("请输入新建管理员的姓名：");
                            String a_admin_name = assert_admin.next();
                            System.out.print("请输入新建管理员的联系电话：");
                            String a_contact = assert_admin.next();

                            admin admin = new admin(a_admin_id, a_admin_password, a_admin_name, a_contact);
                            adminDAOImpl dao = new adminDAOImpl();
                            boolean result = dao.su_add_admin(admin);    //依据boolean类型的返回值来判断是否添加成功
                            System.out.println(result ? "添加成功" : "添加失败");
                        }
                        else
                        {
                            System.out.println("对不起，您没有权限进行此操作，请使用超级管理员登录！");
                        }
                    }
                    case 2 ->
                    {
                        //删除一个管理员账号（仅限超管）
                        if (judge_su)
                        {
                            // 根据管理员的ID删除该管理员账号
                            // （如果该管理员对应一个未归还的借书记录，则不能删除，而是提示该借书记录）
                            Scanner delete_admin = new Scanner(System.in);
                            System.out.print("请输入希望删除的管理员ID：");
                            int a_admin_id = delete_admin.nextInt();
                            adminDAOImpl dao = new adminDAOImpl();
                            borrowDAOImpl borrowDao = new borrowDAOImpl();
                            List<borrow> allBorrows = borrowDao.getAllborrows();
                            List<borrow> unreturnedBorrows = borrowDAO.BorrowFilter.filterByadmin_id(allBorrows, a_admin_id);
                            unreturnedBorrows = borrowDAO.BorrowFilter.filterByNotReturn(unreturnedBorrows, null);

                            if (!unreturnedBorrows.isEmpty())
                            {
                                // 如果有尚未归还的书籍，则阻止删除，并返回提示信息！
                                System.out.println("无法删除管理员账号，因为该管理员尚有未归还的图书：");
                                for (borrow br : unreturnedBorrows)
                                {
                                    System.out.println(br.toString());
                                }
                            }
                            else
                            {
                                adminDAOImpl rm_dao = new adminDAOImpl();
                                boolean result = rm_dao.su_removeby_admin_id(a_admin_id);
                                System.out.println(result ? "删除成功" : "删除失败");
                            }
                        }
                        else
                        {
                            System.out.println("对不起，您没有权限进行此操作，请使用超级管理员登录！");
                        }
                    }

                    case 3 ->
                    {
                        //修改管理员账号（仅限超管）
                        if (judge_su)
                        {
                            //修改管理员账号信息（密码、姓名、联系电话）
                            adminDAOImpl dao = new adminDAOImpl();
                            Scanner alter_admin = new Scanner(System.in);
                            System.out.print("请输入要修改的管理员账号的ID：");
                            int a_admin_id = alter_admin.nextInt();
                            admin admin = dao.su_getbyid(a_admin_id);
                            System.out.print("请输入修改后该管理员账号的密码：");
                            String a_admin_password = alter_admin.next();
                            System.out.print("请输入修改后该管理员账号所属者的姓名：");
                            String a_admin_name = alter_admin.next();
                            System.out.print("请输入修改后该管理员账号的联系电话：");
                            String a_contact = alter_admin.next();
                            admin.setAdmin_password(a_admin_password);
                            admin.setAdmin_name(a_admin_name);
                            admin.setContact(a_contact);
                            boolean result = dao.su_update_admin(admin); // 使用updatecard方法更新记录
                            System.out.println(result ? "修改成功" : "修改失败");
                        }
                        else
                        {
                            System.out.println("对不起，您没有权限进行此操作，请使用超级管理员登录！");
                        }
                    }
                    case 4 ->
                    {
                        //修改自己的管理员账号（密码、姓名、联系电话）
                        //系统会自动识别当前登录的管理员的ID
                        adminDAOImpl dao = new adminDAOImpl();
                        Scanner alter_admin = new Scanner(System.in);
                        admin admin = dao.su_getbyid(login_admin_id);
                        System.out.print("请输入修改后您账号的密码：");
                        String a_admin_password = alter_admin.next();
                        System.out.print("请输入修改后您账号所属者的姓名：");
                        String a_admin_name = alter_admin.next();
                        System.out.print("请输入修改后您账号的联系电话：");
                        String a_contact = alter_admin.next();
                        admin.setAdmin_password(a_admin_password);
                        admin.setAdmin_name(a_admin_name);
                        admin.setContact(a_contact);
                        boolean result = dao.su_update_admin(admin); // 使用updatecard方法更新记录
                        System.out.println(result ? "修改成功" : "修改失败");
                    }
                    case 5 ->
                    {
                        //用于管理员进行查询全体管理员信息的操作
                        //对于超管，可以看到其他管理员的密码
                        //但对于普通管理员，则不可以看到其他管理员的密码，只能看到id，name，contact
                        if (judge_su)
                        {
                            System.out.println("当前数据库中的全体管理员的信息如下：");
                            List<admin> allAdmin = adminDao.su_getAlladmin();
                            allAdmin.forEach(System.out::println);
                        }
                        else
                        {
                            System.out.println("当前数据库中的全体管理员的信息如下：");
                            List<admin> allAdmin = adminDao.getAlladmin();
                            allAdmin.forEach(System.out::println);
                        }
                    }
                }
                if (exitOrContinue(scanner))
                {
                    break;
                }
            }

            else
            {
                System.out.println("选择对管理员操作输入错误！");
            }
        }
    }

    //main方法
    public static void main(String[] args)
    {
        System.out.println("欢迎使用数据库管理信息系统！");
        Scanner scanner = new Scanner(System.in);
        int selectRole = displayLoginMenu(scanner);
        if(selectRole == 1)
        {
            int inputAccount = adminLogin(scanner);
            displayAdminMenu(scanner, inputAccount);
        }
        else if(selectRole == 2)
        {
            System.out.println("您已选择普通用户身份登录。");
            System.out.println("普通用户仅支持查询图书和管理员信息，更多权限请使用管理员登录。");
            userMenu(scanner);
        }
    }
}