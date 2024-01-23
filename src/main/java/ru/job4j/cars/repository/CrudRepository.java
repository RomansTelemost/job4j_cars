package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

@AllArgsConstructor
public class CrudRepository {

    private final SessionFactory sf;

    public void run(Consumer<Session> command) {
        tx(session -> {
                    command.accept(session);
                    return null;
                }
        );
    }

    public void run(String query, Map<String, Object> args) {
        Consumer<Session> command = session -> {
            var sq = session
                    .createQuery(query);
            for (Map.Entry<String, Object> arg : args.entrySet()) {

            }
        };
    }

}
