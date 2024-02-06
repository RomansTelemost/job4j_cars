package ru.job4j.cars.repository;

import ru.job4j.cars.model.AutoPost;
import ru.job4j.cars.model.Brand;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Optional<AutoPost> findById(int id);

    List<AutoPost> getAutoPostFromDate(LocalDate localDate);

    List<AutoPost> getAutoPostWithPhoto();

    List<AutoPost> getAutoPostByBrand(Brand brand);

}
