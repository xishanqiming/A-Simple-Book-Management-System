package LabCode.bean;

public class card
{
    private Integer cno;
    private String name;
    private String department;
    private String type;

    public card()
    {

    }

    public card(String name, String department, String type)
    {
        this.cno = null;
        this.name = name;
        this.department = department;
        this.type = type;
    }

    public card(Integer cno, String name, String department, String type)
    {
        this.cno = cno;
        this.name = name;
        this.department = department;
        this.type = type;
    }

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "card{" +
                "cno=" + cno +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
