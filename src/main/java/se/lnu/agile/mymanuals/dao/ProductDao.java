package se.lnu.agile.mymanuals.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import se.lnu.agile.mymanuals.model.Product;

import java.util.List;

/**
 * Created by ilyakruikov on 11/20/16.
 */
public interface ProductDao extends PagingAndSortingRepository<Product, Long> {

    @Query("SELECT p.model " +
            "FROM Product p JOIN p.company " +
            "WHERE p.company.id = :companyId " +
            "AND p.model = :model")
    String getModelByCompanyId(@Param("companyId") long companyId, @Param("model") String model);

    @Query ("Select p FROM Product p JOIN p.category pc WHERE pc.id IN (:categoriesIds )")
    Page<Product> findProductByCategories(@Param("categoriesIds")List<Long> categoriesIds, Pageable pageable);

    List<Product> findAll();

}