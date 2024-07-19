package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.AutoPost;
import ru.job4j.cars.model.Brand;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class HqlPostRepository implements PostRepository {

    private CrudRepository cr;

    @Override
    public Optional<AutoPost> findById(int id) {
        return cr.optional(
                "FROM AutoPost p WHERE p.id = :id",
                AutoPost.class,
                Map.of("id", id));
    }

    @Override
    public List<AutoPost> getAutoPostFromDate(LocalDate localDate) {
        return cr.query("FROM AutoPost p " +
                        "JOIN FETCH p.car " +
                        "WHERE p.created >= :created",
                AutoPost.class,
                Map.of("created",
                        localDate.atStartOfDay()));
    }

    @Override
    public List<AutoPost> getAutoPostFromDateByCriteria(LocalDate localDate) {
        Session session = cr.sf.openSession();
        CriteriaBuilder criteriaBuilder = cr.sf.getCriteriaBuilder();
        CriteriaQuery<AutoPost> criteriaQuery = criteriaBuilder.createQuery(AutoPost.class);
        Root<AutoPost> root = criteriaQuery.from(AutoPost.class);
        criteriaQuery.select(root).where(criteriaBuilder.greaterThan(root.get("created"), localDate.atStartOfDay()));

        Query<AutoPost> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<AutoPost> getAutoPostWithPhoto() {
        return cr.query("FROM AutoPost p " +
                "JOIN FETCH p.car " +
                "LEFT JOIN FETCH p.files " +
                "WHERE size(p.files) <> 0",
                AutoPost.class);
    }

    @Override
    public List<AutoPost> getAutoPostByBrand(Brand brand) {
        return null;
    }
}
