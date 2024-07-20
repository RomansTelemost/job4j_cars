package ru.job4j.cars.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.job4j.cars.conf.HibernateConfiguration;
import ru.job4j.cars.model.AutoPost;
import ru.job4j.cars.model.User;

import java.time.LocalDate;

@Component
public class UserUsage {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            ApplicationContext applicationContext =
                    new AnnotationConfigApplicationContext(HibernateConfiguration.class);
            UserRepositoryByCrudRepo userRepository = applicationContext.getBean(UserRepositoryByCrudRepo.class);
            HqlPostRepository hqlPostRepository = applicationContext.getBean(HqlPostRepository.class);

            testUserUsage(userRepository);
            testPostRepository(hqlPostRepository);

        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void testUserUsage(UserRepositoryByCrudRepo userRepository) {
        var user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        userRepository.create(user);
        userRepository.findAllOrderById()
                .forEach(System.out::println);
        userRepository.findByLikeLogin("e")
                .forEach(System.out::println);
        userRepository.findById(user.getId())
                .ifPresent(System.out::println);
        userRepository.findByLogin("admin")
                .ifPresent(System.out::println);
        user.setPassword("password");
        userRepository.update(user);
        userRepository.findById(user.getId())
                .ifPresent(System.out::println);
        System.out.println("***1");
        userRepository.delete(user.getId());
        userRepository.findAllOrderById()
                .forEach(System.out::println);
    }

    public static void testPostRepository(HqlPostRepository hqlPostRepository) {
        for (AutoPost autoPost : hqlPostRepository.getAutoPostFromDateByCriteria(LocalDate.now())) {
            System.out.println("Post for today");
            System.out.println("id: " + autoPost.getId());
            System.out.println("Description: " + autoPost.getDescription());
        }
        for (AutoPost autoPost : hqlPostRepository.getAutoPostWithPhoto()) {
            System.out.println("Post with photo");
            System.out.println("id: " + autoPost.getId());
            System.out.println("Description: " + autoPost.getDescription());
        }
    }
}