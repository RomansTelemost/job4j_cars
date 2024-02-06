package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class HqlEngineRepository implements EngineRepository {

    private CrudRepository cr;

    @Override
    public Optional<Engine> findById(int id) {
        return cr.optional(
                "FROM Engine where id = :id",
                Engine.class,
                Map.of("id", id));
    }

}
