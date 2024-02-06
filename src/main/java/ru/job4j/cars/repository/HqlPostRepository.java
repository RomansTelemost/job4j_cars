package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.AutoPost;
import ru.job4j.cars.model.Brand;

import javax.persistence.criteria.CriteriaQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class HqlPostRepository implements PostRepository {

    CrudRepository cr;

    @Override
    public Optional<AutoPost> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<AutoPost> getAutoPostFromDate(LocalDate localDate) {
        /**
        CriteriaQuery<AutoPost> criteriaQuery =
                riteriaBuilder.createQuery(DeptEmployee.class);
        Root<DeptEmployee> root = criteriaQuery.from(DeptEmployee.class);
        In<String> inClause = criteriaBuilder.in(root.get("title"));
        for (String title : titles) {
            inClause.value(title);
        }
        criteriaQuery.select(root).where(inClause);

        return null;
         */
        return new ArrayList<>();
    }

    @Override
    public List<AutoPost> getAutoPostWithPhoto() {
        return null;
    }

    @Override
    public List<AutoPost> getAutoPostByBrand(Brand brand) {
        return null;
    }
}
