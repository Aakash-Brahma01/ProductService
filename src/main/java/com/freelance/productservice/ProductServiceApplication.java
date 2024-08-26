package com.freelance.productservice;

import com.freelance.productservice.Inheritancedemo.Singletable.Mentor;
import com.freelance.productservice.Inheritancedemo.Singletable.MentorRepository;
import com.freelance.productservice.Inheritancedemo.Singletable.User;
import com.freelance.productservice.Inheritancedemo.Singletable.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

    private MentorRepository mentorRepository;
    private UserRepository userRepository;

    public ProductServiceApplication(MentorRepository mentorRepository, UserRepository userRepository) {
        this.mentorRepository = mentorRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Mentor mentor = new Mentor();
        mentor.setName("Naman");
        mentor.setEmail("Naman@sacalwer.com");
        mentor.setAvg_rating(4.65);
        mentorRepository.save(mentor);

        User user = new User();
        user.setName("Aakash");
        user.setEmail("akash@sacalwer.com");
        userRepository.save(user);
    }
}
