package ru.job4j.cars.conf;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.job4j.cars.repository.CrudRepository;
import ru.job4j.cars.repository.UserRepositoryByCrudRepo;

@Configuration
@ComponentScan
public class HibernateConfiguration {

    @Bean(destroyMethod = "close")
    public SessionFactory sf() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Bean
    public UserRepositoryByCrudRepo crudRepo() {
        return new UserRepositoryByCrudRepo(new CrudRepository(sf()));
    }

}
