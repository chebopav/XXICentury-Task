package com.controllers;

import com.entity.Order;
import com.services.OrderLineService;
import com.services.OrderService;
import com.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService service;
    private ProductService productService;
    private OrderLineService orderLineService;

    @Autowired
    public OrderController(OrderService service, ProductService productService, OrderLineService orderLineService) {
        this.service = service;
        this.productService = productService;
        this.orderLineService = orderLineService;
    }

    @PostMapping
    public Order addOrder(@RequestBody Order order) {
        service.addOrder(order);
        return order;
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable long id, @RequestBody Order newOrder){
        Order order = service.getOrderById(id);
        order.setClientName(newOrder.getClientName());
        order.setDate(newOrder.getDate());
        order.setAddress(newOrder.getAddress());
        service.updateOrder(order);
        return order;
    }

    @DeleteMapping("/{id}")
    public Order deleteGood(@PathVariable long id){
        Order order = service.getOrderById(id);
        service.deleteOrder(order);
        return order;
    }


    @GetMapping
    public List<Order> getAllOrders(){
        return service.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable long id){
        return service.getOrderById(id);
    }
}
