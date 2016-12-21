package se.lnu.agile.mymanuals.dao;

import org.springframework.data.repository.CrudRepository;
import se.lnu.agile.mymanuals.model.Comment;


/**
 * Created by Lo.Gas_2 on 21/12/2016.
 */

public interface CommentDao extends CrudRepository<Comment, Long> {

}
