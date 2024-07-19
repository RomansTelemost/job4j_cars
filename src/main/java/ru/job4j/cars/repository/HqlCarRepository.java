package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class HqlCarRepository implements CarRepository {

    private CrudRepository cr;

    @Override
    public Optional<Car> findById(int id) {
        return cr.optional(
                "FROM Car where id = :id",
                Car.class,
                Map.of("id", id));
    }
}
