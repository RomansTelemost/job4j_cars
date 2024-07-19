package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Owner;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class HqlOwnerRepository implements OwnerRepository {

    private CrudRepository cr;

    @Override
    public Optional<Owner> findById(int id) {
        return cr.optional(
                "FROM Owner where id = :id",
                Owner.class,
                Map.of("id", id));
    }

}
