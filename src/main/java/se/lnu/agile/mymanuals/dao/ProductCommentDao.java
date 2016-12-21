package se.lnu.agile.mymanuals.dao;

import org.springframework.data.repository.CrudRepository;
import se.lnu.agile.mymanuals.model.ProductComment;

/**
 * Created by Lo.Gas_2 on 21/12/2016.
 */
public interface ProductCommentDao extends CrudRepository<ProductComment, Long> {
}