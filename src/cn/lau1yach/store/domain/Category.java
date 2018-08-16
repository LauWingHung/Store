package cn.lau1yach.store.domain;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2018/8/16
 * Time: 17:37
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Category {
    private  String cid;
    private  String cname;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Category() {
    }

    public Category(String cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }
}