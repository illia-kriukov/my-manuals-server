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

    @Query("select p.model from Product p join p.company where (p.company.id) = (:companyId) and (p.model) = (:model)")
    String getModelByCompany(@Param("model") String model, @Param("companyId") long companyId);
}