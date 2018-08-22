package cn.lau1yach.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    //    总计/积分

    private double total=0;
//    个数不确定的购物项商品的PID<===>CartItem

    Map<String,CartItem> map= new HashMap<>();
//    添加购物项到购物车

//    当用户点击加入购物车按钮，可以将当前要购买的商品id，商品数量发送到服务端，服务端根据商品id查询到商品信息
//    有了商品信息Product对象，有了要购买商品数量，当前的购物项也就获取到了

    public void addCartItemToCart(CartItem cartItem){
//        获取商品的PID
        String pid=cartItem.getProduct().getPid();


//        加入购物车之前判断之前是否添加过购物车
//        如果没有买过，直接list.add(cartItem);
//        如果买过，获取原先的数量，获取到本次的数量，相加之后设置到原先的购物项上

        if (map.containsKey(pid)){
//            获取到原先的购物项
            CartItem oldItem=map.get(pid);
            oldItem.setNum(oldItem.getNum()+cartItem.getNum());
        }else {
            map.put(pid,cartItem);
        }
    }
//    返回map中所有的值

    public  Collection<CartItem> getCartItems(){
        return map.values();
    }
//    移除购物项
    public void removeCartItem(String pid){
        map.remove(pid);
    }
//    清空购物项
    public  void clearCart(){
        map.clear();
    }

    public double getTotal() {
//        先让总计清零
        total=0;
//        获取到map中所有的购物项
        Collection<CartItem> values=map.values();
//        遍历所有的购物项，将购物项上的小计相加
        for (CartItem cartItem:values) {
            total+=cartItem.getSubTotal();
        }
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }
}
