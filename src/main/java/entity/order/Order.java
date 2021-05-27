package entity.order;

import controller.SessionInformation;
import entity.cart.Cart;
import entity.cart.CartItem;
import entity.shipping.DeliveryInfo;
import views.screen.ViewsConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private State state;
    private int shippingFees;
    private int subtotal;
    private int tax;
    private List orderMediaList;

    protected DeliveryInfo deliveryInfo;     // common coupling

    public Order() {
        this.state = new DefaultState();
        this.shippingFees = 0;
        this.subtotal = 0;
        this.tax = 0;
    }

    public void setState(State state){
        this.state = state;
    }

    public void applyChange(){
        this.state.handlingState();
    }


    // Stamp coupling: chỉ lấy subtottal nhưng dùng tham số là cart

    /**
     * Clean code: Multi abstract level --> separate code to get all item from cart to another method
     * Create a new method getAllItemsFromCart
     */
    public Order(Cart cart) {
        this.state = new DefaultState();
        List<OrderItem> orderItems = getAllItemsFromCart(cart);
        this.orderMediaList = Collections.unmodifiableList(orderItems);
        this.subtotal = cart.calSubtotal();
        this.tax = (int) (ViewsConfig.PERCENT_VAT/100) * subtotal;
    }
    
    public List<OrderItem> getAllItemsFromCart(Cart cart) {
    	List<OrderItem> orderItems = new ArrayList<>();
    	for (Object object : Cart.getCart().getListMedia()) {
            CartItem cartItem = (CartItem) object;
            OrderItem orderItem = new OrderItem(cartItem.getMedia(),
                    cartItem.getQuantity(),
                    cartItem.getPrice());
            orderItems.add(orderItem);
        }
    	return orderItems;
    }

    public List getListOrderMedia() {
        return this.orderMediaList;
    }

    public int getShippingFees() {
        if (deliveryInfo == null) return 0;
        return this.shippingFees;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }   

    // SOLID: DIP do phu thuoc vao deliveryInfo khong phai la Abstract/Interface
    // Data coupling
    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
        this.shippingFees = deliveryInfo.calculateShippingFee(this);
    }

    public List getOrderMediaList() {
        return orderMediaList;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public int getTax() {
        return tax;
    }

    public int getTotal() {
        return this.subtotal + this.tax + this.shippingFees;
    }

    //communicational cohesion: một vài phương thức dùng dung thuộc tính
    //logical cohesion: các phương thức đều liên quan đến tác vụ order
}
