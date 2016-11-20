package se.lnu.agile.mymanuals.dao;

import org.springframework.data.repository.CrudRepository;
import se.lnu.agile.mymanuals.model.Product;

/**
 * Created by ilyakruikov on 11/20/16.
 */
public interface ProductDao extends CrudRepository<Product, Long> {
}