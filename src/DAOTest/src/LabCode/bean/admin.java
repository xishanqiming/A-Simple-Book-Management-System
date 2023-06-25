package LabCode.bean;

public class admin
{
    private Integer admin_id;
    private String admin_password;
    private String admin_name;
    private String contact;

    public admin()
    {

    }

    public admin(String admin_password, String admin_name, String contact)
    {
        this.admin_id = null;
        this.admin_password = admin_password;
        this.admin_name = admin_name;
        this.contact = contact;
    }

    public admin(int admin_id, String admin_password, String admin_name, String contact)
    {
        this.admin_id = admin_id;
        this.admin_password = admin_password;
        this.admin_name = admin_name;
        this.contact = contact;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString()
    {
        if (admin_password==null)
        {
            return "admin{" +
                    "admin_name='" + admin_name + '\'' +
                    ", contact='" + contact + '\'' +
                    '}';
        }
        else
        {
            return "admin{" +
                    "admin_id=" + admin_id +
                    ", admin_password='" + admin_password + '\'' +
                    ", admin_name='" + admin_name + '\'' +
                    ", contact='" + contact + '\'' +
                    '}';
        }
    }
}
