package se.lnu.agile.mymanuals.dao;

import org.springframework.data.repository.CrudRepository;
import se.lnu.agile.mymanuals.model.Category;

import java.util.List;

/**
 * Created by Daniel on 17.11.2016.
 */
public interface CategoryDao extends CrudRepository<Category, Long> {

    Category findByName(String name);

    List<Category> findAll();

}