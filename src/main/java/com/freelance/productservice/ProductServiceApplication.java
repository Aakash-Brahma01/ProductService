package com.freelance.productservice;


import com.freelance.productservice.Inheritancedemo.Joinedtable.Mentor;
import com.freelance.productservice.Inheritancedemo.Joinedtable.MentorRepository;
import com.freelance.productservice.Inheritancedemo.Joinedtable.User;
import com.freelance.productservice.Inheritancedemo.Joinedtable.UserRepository;
import com.freelance.productservice.models.Category;
import com.freelance.productservice.models.Price;
import com.freelance.productservice.models.Product;
import com.freelance.productservice.repositories.CategoryRepository;
import com.freelance.productservice.repositories.PriceRepository;
import com.freelance.productservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

    private MentorRepository mentorRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private PriceRepository priceRepository;

    public ProductServiceApplication(@Qualifier(value = "jt_mr") MentorRepository mentorRepository, @Qualifier(value = "jt_ur") UserRepository userRepository,ProductRepository productRepository,CategoryRepository categoryRepository,PriceRepository priceRepository) {
        this.mentorRepository = mentorRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // Table inheritance concept

//        Mentor mentor = new Mentor();
//        mentor.setName("Naman");
//        mentor.setEmail("Naman@sacalwer.com");
//        mentor.setAvg_rating(4.65);
//        mentorRepository.save(mentor);
//
//        User user = new User();
//        user.setName("Aakash");
//        user.setEmail("akash@sacalwer.com");
//        userRepository.save(user);
//
//        List<User> users = userRepository.findAll();
//        for (User user1: users) {
//            System.out.println(user1);
//        }


        // Cardinality types and Queries

          Category category=new Category();
          category.setName("Washing Machine");
          //Category savedCatgory=categoryRepository.save(category);

          Price price = new Price();
          price.setCurrency("Rupee");
          price.setPrice(1500);
          //Price savedPrice=priceRepository.save(price);

          Product product=new Product();
          product.setTitle("Washing Machine");
          product.setImage("LG WM image url");
          product.setDescription("LG WM description");
          product.setCategory(category);
          product.setPrice(price);
          //productRepository.save(product);

          //productRepository.deleteById(UUID.fromString("e0392682-6306-44b8-be7f-060423372887"));

        Optional<Product> savedProduct=productRepository.findById(UUID.fromString("bc0389ef-f26b-4bcb-861a-a7dc2c5aa52c"));
        Product p=savedProduct.get();
        p.setDescription("Amazon basics Branded New Washing machine");
        p.setImage("3D images for WM");
        Price newPrice=p.getPrice();
        newPrice.setPrice(1800);
       // productRepository.save(p);

        //Product prod=productRepository.findByTitle("Phone");
        List<Product> prod=productRepository.findByTitle("Washing Machine");

        Product pd=productRepository.findByTitleAndPrice_Price("Phone",100d);

        List<Product> p1=productRepository.findAllByTitleOrderByPrice_PriceDesc("Washing Machine");

        Long count=productRepository.countAllByPrice_Currency("Dollar");
        System.out.println("Count is "+count);

        List<Product> p2=productRepository.findByTitleLike("%mac%");
        System.out.println("tiltle like desc size"+p2.size());

        List<Product> p3=productRepository.findAByTitle("Washing Machine");
        System.out.println("Title size"+p3.size());

        List<Product> p4=productRepository.doSomething("Phone","Rupee");
        System.out.println("Title size"+p4.size());

        Optional<Category> category1=categoryRepository.findById(UUID.fromString("18c3452a-c6db-4b17-a021-98f1b86462b8"));
        List<Product> products=category1.get().getProducts();
    }
}
