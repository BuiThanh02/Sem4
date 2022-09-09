package com.example.springbootassignment.service;

import com.example.springbootassignment.entity.Order;
import com.example.springbootassignment.entity.OrderDetail;
import com.example.springbootassignment.entity.Product;
import com.example.springbootassignment.entity.search.*;
import com.example.springbootassignment.repository.OrderRepository;
import com.example.springbootassignment.repository.ProductRepository;
import com.example.springbootassignment.util.ConvertDateHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

import static com.example.springbootassignment.entity.search.SearchCriteriaOperator.*;

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

    public Map<String, Object> findAll(SearchBody searchBody){
        Specification specification = Specification.where(null);

        if (searchBody.getNameUser() != null && searchBody.getNameUser().length() > 0 ){
            specification = specification.and(new OrderSpecification(new SearchCriteria("fullName", JOIN_USER, searchBody.getNameUser())));
        }
        if (searchBody.getPhone() != null && searchBody.getPhone().length() > 0){
            specification = specification.and(new OrderSpecification(new SearchCriteria("phone",JOIN_USER, searchBody.getPhone())));
        }
        if (searchBody.getNameProduct() != null && searchBody.getNameProduct().length() > 0){
            specification = specification.and(new OrderSpecification(new SearchCriteria("name",JOIN_DETAIL_PRODUCT, searchBody.getNameProduct())));
        }
        if (searchBody.getOrderId() != null && searchBody.getOrderId().length() > 0){
            specification = specification.and(new OrderSpecification(new SearchCriteria("id", LIKE,searchBody.getOrderId())));
        }
        if (searchBody.getStart() != null && searchBody.getStart().length() > 0){
//            log.info("check start: " + orderSearchBody.getStart() );
//            log.info("Check Start begin" + searchBody.getStart());

            LocalDateTime date = ConvertDateHelper.convertStringToLocalDateTime(searchBody.getStart());
//            log.info("Check Start" + date);
//            log.info("check start convert date: " + date );
            specification = specification.and(new OrderSpecification(new SearchCriteria("createdAt", GREATER_THAN_OR_EQUALS,date)));
        }
        if (searchBody.getEnd() != null && searchBody.getEnd().length() > 0){
            LocalDateTime date = ConvertDateHelper.convertStringToLocalDateTime(searchBody.getEnd());
            specification = specification.and(new OrderSpecification(new SearchCriteria("createdAt", LESS_THAN_OR_EQUALS,date)));
        }

        Sort sort= Sort.by(Sort.Order.asc("id"));
        if (searchBody.getSort() !=null && searchBody.getSort().length() >0){
            if (searchBody.getSort().contains("desc")){
                sort = Sort.by(Sort.Order.desc("id"));
            }
        }
        Pageable pageable = PageRequest.of(searchBody.getPage() -1, searchBody.getLimit(),sort );
        Page<Order> pageOrder = orderRepository.findAll(specification,pageable);
        List<Order> orderList = pageOrder.getContent();
        Map<String, Object> responses = new HashMap<>();
        responses.put("content",orderList);
        responses.put("currentPage",pageOrder.getNumber() + 1);
        responses.put("totalItems",pageOrder.getTotalElements());
        responses.put("totalPage",pageOrder.getTotalPages());
        return responses;
    }

}
