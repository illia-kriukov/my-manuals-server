package se.lnu.agile.mymanuals.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import se.lnu.agile.mymanuals.model.Comment;
import se.lnu.agile.mymanuals.model.Product;

/**
 * Created by Lo.Gas_2 on 21/12/2016.
 */
public interface CommentDao extends PagingAndSortingRepository<Comment, Long> {

    Page<Comment> findAllByProduct(Product product, Pageable pageable);

}