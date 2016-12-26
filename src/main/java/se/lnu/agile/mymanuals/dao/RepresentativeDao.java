package se.lnu.agile.mymanuals.dao;

import se.lnu.agile.mymanuals.model.Representative;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by ilyakruikov on 11/10/16.
 */
public interface RepresentativeDao extends CrudRepository<Representative, Long> {

    Representative findByEmail(String email);

    List<Representative> findByEmailIn(Set<String> emailSet);

}