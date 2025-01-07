package com.jana.service;

import com.jana.model.*;
import com.jana.repository.*;
import com.jana.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService  cartService;


    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {

        Address shippingAddress= order.getDeliveryAddress();

        Address savedAddress= addressRepository.save(shippingAddress);

        if(!user.getAddresses().contains(savedAddress)){
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }

        Restaurant restaurant= restaurantService.findRestaurantById(order.getRestaurantId());

        Order createdOrder= new Order();
        createdOrder.setCustomer(user);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setRestaurant(restaurant);

        Cart cart= cartService.findCartByUserId(user.getId());

        List<Orderitem> orderItems= new ArrayList<>();

        for(CartItem cartItem: cart.getItems()){
            Orderitem orderitem= new Orderitem();
            orderitem.setFood(cartItem.getFood());
            orderitem.setIngredients(cartItem.getIngredients());
            orderitem.setQuantity(cartItem.getQuantity());
            orderitem.setTotalPrice(cartItem.getTotalPrice());

            Orderitem savedOrderItem = orderItemRepository.save(orderitem);
            orderItems.add(savedOrderItem);
        }

        Long totalPrice = cartService.calculateCartTotals(cart);

        createdOrder.setItems(orderItems);
        createdOrder.setTotalPrice(cart.getTotal());

        Order savedOrder = orderRepository.save(createdOrder);
        restaurant.getOrders().add(savedOrder);

        return createdOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {

        Order order= findOrderById(orderId);
        if (orderStatus.equals("OUT_FOR_DELIVERY")
                ||  orderStatus.equals("DELIVERED")
                ||  orderStatus.equals("COMPLETED")
                ||  orderStatus.equals("PENDING")
        ){
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("please select a valid order status");

    }

    @Override
    public void cancellOrder(Long orderId) throws Exception {

        Order order= findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUserOrder(Long userId) throws Exception {
        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus) throws Exception {

        List<Order> orders= orderRepository.findByRestaurantId(restaurantId);
        if(orderStatus!=null){
            orders= orders.stream().filter(order ->
                    order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {

        Optional<Order> optionalOrder= orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new Exception("order not found");
        }
        return optionalOrder.get();
    }
}
