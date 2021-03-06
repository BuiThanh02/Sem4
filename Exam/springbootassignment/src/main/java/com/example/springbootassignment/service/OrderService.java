package com.example.springbootassignment.service;

import com.example.springbootassignment.entity.Order;
import com.example.springbootassignment.entity.OrderDetail;
import com.example.springbootassignment.entity.Product;
import com.example.springbootassignment.entity.search.FilterParameter;
import com.example.springbootassignment.entity.search.OrderSpecification;
import com.example.springbootassignment.entity.search.SearchCriteria;
import com.example.springbootassignment.entity.search.SearchCriteriaOperator;
import com.example.springbootassignment.repository.OrderRepository;
import com.example.springbootassignment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService{
    final OrderRepository orderRepository;

    final ProductRepository productRepository;


    public Order findShoppingCartByUserId(int userId){
       return orderRepository.getShoppingCart(userId);
    }

    public Order addShoppingCart(int userId, String productId, int quantity) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(!optionalProduct.isPresent()){
            return null;
        }
        Order order = orderRepository.getShoppingCart(userId);
        Set<OrderDetail> orderDetails = order.getOrderDetails();
//        HashMap<Long, OrderDetail> map = new HashMap<Long, OrderDetail>();
//        //fill in map
//        for(OrderDetail entry : orderDetails)
//        {
//            map.put(entry.getProduct().getId(), entry);
//        }
//
//        for (int i =0 ; i < orderDetails.size() - 1 ;i++){
//            //neu chua co sp trong gio hang thi them vao
//            // con co roi thi cong don ok
//        }
        boolean exist = false;
        for(OrderDetail entry : orderDetails)
        {
            if(entry.getProduct().getId().equals(productId)){
                entry.setQuantity(entry.getQuantity() + quantity);
                exist = true;
            }
        }
        if(!exist){
            OrderDetail orderDetail = new OrderDetail();
            orderDetails.add(orderDetail);
        }
        //save
         return order;
    }

    public Page<Order> findAll(FilterParameter param) {
        Specification<Order> specification = Specification.where(null);
        if (param.getKeyword() != null && param.getKeyword().length() > 0) {
            SearchCriteria searchCriteria = new SearchCriteria("keyword", SearchCriteriaOperator.JOIN, param.getKeyword());
            OrderSpecification filter = new OrderSpecification(searchCriteria);
            specification = specification.and(filter);
        }
        if (param.getStatus() != 0) {
            SearchCriteria searchCriteria
                    = new SearchCriteria("status", SearchCriteriaOperator.EQUALS, param.getStatus());
            OrderSpecification filter = new OrderSpecification(searchCriteria);
            specification = specification.and(filter);
        }
        if (param.getUserId() != null) {
            SearchCriteria searchCriteria
                    = new SearchCriteria("userId", SearchCriteriaOperator.EQUALS, param.getUserId());
            OrderSpecification filter = new OrderSpecification(searchCriteria);
            specification = specification.and(filter);
        }
        return orderRepository.findAll(
                specification, PageRequest.of(param.getPage() - 1, param.getLimit()));
    }
}
