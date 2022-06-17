package com.t2010a.productapi.seeder;

import com.github.javafaker.Faker;
import com.t2010a.productapi.entity.Category;
import com.t2010a.productapi.entity.Product;
import com.t2010a.productapi.entity.User;
import com.t2010a.productapi.repository.CategoryRepository;
import com.t2010a.productapi.repository.OrderRepository;
import com.t2010a.productapi.repository.ProductRepository;
import com.t2010a.productapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ApplicationSeeder implements CommandLineRunner {
    boolean createSeederData = true;
    final ProductRepository productRepository;
    final OrderRepository orderRepository;
    final UserRepository userRepository;
    final CategoryRepository categoryRepository;
    Faker faker;
    int numberOfCategory = 10;
    int numberOfUser = 70;
    int numberOfProduct = 200;
    int numberOfOrder = 10000;

    public ApplicationSeeder(ProductRepository productRepository, OrderRepository orderRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) throws Exception {
        if (createSeederData){
            orderRepository.deleteAll();
            productRepository.deleteAll();
            seedCategory();
            seedProduct();
            seedUser();
            deedOrder();
        }
    }

    private void seedCategory(){
        List<Category> categories = new ArrayList<>();
        for(int i = 0; i < numberOfCategory; i++){
            Category category = new Category();
            category.setId(UUID.randomUUID());
            category.setName(faker.name().title());
            category.setStatus(faker.number().numberBetween(-1,1));
            categories.add(category);
        }
        categoryRepository.saveAll(categories);
    }

    private void seedProduct(){
        List<Product> products = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for (int i = 0; i < numberOfProduct; i++){
            Category randomCategory = categories.get(
                    faker.number().numberBetween(0, categories.size() - 1));
            Product product = new Product();
            product.setId(UUID.randomUUID());
            product.setName(faker.name().title());
            product.setQty(faker.number().numberBetween(1, 1000));
            product.setStatus(faker.number().numberBetween(-1,1));
            product.setThumbnail(faker.avatar().image());
            product.setDetail(faker.lorem().sentence());
            product.setDescribe(faker.lorem().sentence());
            product.setCategoryId(randomCategory.getId());
            product.setCategory(randomCategory);
            product.setPrice(new BigDecimal(faker.number().numberBetween(10, 300) * 100000));
            products.add(product);
        }
        productRepository.saveAll(products);
    }

    private void seedUser(){
        List<User> users = new ArrayList<>();
        for (int i = 0; i < numberOfUser; i++){
            User user = new User();
            user.setId(UUID.randomUUID());
            user.setName(faker.name().fullName());
            user.setAddress(faker.address().fullAddress());
            user.setPhone(faker.phoneNumber().cellPhone());
            user.setEmail(faker.internet().emailAddress());
        }
    }
}
