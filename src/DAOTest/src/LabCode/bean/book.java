package LabCode.bean;

import java.math.BigDecimal;

public class book
{
    private Integer bno;
    private String category;
    private String title;
    private String press;
    private int year;
    private String author;
    private BigDecimal price;
    private int total;
    private int stock;

    public book()
    {

    }

    public book(String category, String title, String press, int year, String author, BigDecimal price, int total)
    {
        this.bno = null;
        this.category = category;
        this.title = title;
        this.press = press;
        this.year = year;
        this.author = author;
        this.price = price;
        this.total = total;
        this.stock = total;
    }
    public book(Integer bno, String category, String title, String press, int year, String author, BigDecimal price, int total)
    {
        this.bno = bno;
        this.category = category;
        this.title = title;
        this.press = press;
        this.year = year;
        this.author = author;
        this.price = price;
        this.total = total;
        this.stock = total;
    }

    public book(Integer bno, String category, String title, String press, int year, String author, BigDecimal price, int total, int stock)
    {
        this.bno = bno;
        this.category = category;
        this.title = title;
        this.press = press;
        this.year = year;
        this.author = author;
        this.price = price;
        this.total = total;
        this.stock = stock;
    }

    public book(int bno, String category, String title, String press, int year, String author, double price, int total) {
    }

    public Integer getBno() {
        return bno;
    }

    public void setBno(Integer bno) {
        this.bno = bno;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString()
    {
        return "book{" +
                "bno=" + bno +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", press='" + press + '\'' +
                ", year=" + year +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", total=" + total +
                ", stock=" + stock +
                '}';
    }
}
