package cn.lau1yach.store.domain;

public class CartItem {
    //目的携带购物项3种参数（图片路径，商品名称，商品价格）
    private  Product product=new Product();
    //当前类别商品数量
    private  int num;
    //小计
    private double subTotal;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
//小计是经过计算可以获取到的
    public double getSubTotal() {
        return product.getShop_price()*num;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public CartItem() {
    }

    public CartItem(Product product, int num, double subTotal) {
        this.product = product;
        this.num = num;
        this.subTotal = subTotal;
    }
}
