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

    @Query ("SELECT p " +
            "FROM Product p JOIN p.category pc " +
            "WHERE pc.id IN :categoryIds")
    Page<Product> findAllByCategoryIds(@Param("categoryIds") List<Long> categoryIds, Pageable pageable);

    @Query ("Select p " +
            "FROM Product p " +
            "Where p.name LIKE :query")
    Page<Product> findAllBySearchQuery(@Param("query") String query, Pageable pageable);

}