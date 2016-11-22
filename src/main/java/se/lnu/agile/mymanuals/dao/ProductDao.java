package se.lnu.agile.mymanuals.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lnu.agile.mymanuals.model.Company;
import se.lnu.agile.mymanuals.model.Product;

/**
 * Created by ilyakruikov on 11/20/16.
 */
public interface ProductDao extends CrudRepository<Product, Long> {

    @Query("SELECT p.model " +
            "FROM Product p JOIN p.company " +
            "WHERE p.company.id = :companyId " +
            "AND p.model = :model")
    String getModelByCompanyId(@Param("companyId") long companyId, @Param("model") String model);

}