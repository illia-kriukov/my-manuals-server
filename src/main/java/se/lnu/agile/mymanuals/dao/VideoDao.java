package se.lnu.agile.mymanuals.dao;

import org.springframework.data.repository.CrudRepository;
import se.lnu.agile.mymanuals.model.Category;
import se.lnu.agile.mymanuals.model.Video;

import java.util.List;

/**
 * Created by ilyakruikov on 11/20/16.
 */
public interface VideoDao extends CrudRepository<Video, Long> {
}