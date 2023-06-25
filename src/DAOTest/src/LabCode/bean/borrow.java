package LabCode.bean;

import java.util.Date;

public class borrow
{
    private Integer borrow_id;
    private Integer cno;
    private Integer bno;
    private Date borrow_date;
    private Date return_date;
    private int admin_id;

    public borrow()
    {

    }

    public borrow(Integer cno, Integer bno, Date borrow_date, int admin_id)
    {
        this.borrow_id = null;
        this.cno = cno;
        this.bno = bno;
        this.borrow_date = borrow_date;
        this.return_date = null;
        this.admin_id = admin_id;
    }

    public borrow(Integer cno, Integer bno, Date borrow_date, Date return_date, int admin_id)
    {
        this.borrow_id = null;
        this.cno = cno;
        this.bno = bno;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
        this.admin_id = admin_id;
    }

    public borrow(Integer borrow_id, Integer cno, Integer bno, Date borrow_date, Date return_date, int admin_id)
    {
        this.borrow_id = borrow_id;
        this.cno = cno;
        this.bno = bno;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
        this.admin_id = admin_id;
    }

    public Integer getBorrow_id() { return borrow_id; }

    public void setBorrow_id(Integer borrow_id) { this.borrow_id = borrow_id; }

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public Integer getBno() {
        return bno;
    }

    public void setBno(Integer bno) {
        this.bno = bno;
    }

    public Date getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(Date borrow_date) {
        this.borrow_date = borrow_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    @Override
    public String toString()
    {
        return "borrow{" +
                "cno=" + cno +
                ", bno=" + bno +
                ", borrow_date=" + borrow_date +
                ", return_date=" + return_date +
                ", admin_id=" + admin_id +
                '}';
    }
}
