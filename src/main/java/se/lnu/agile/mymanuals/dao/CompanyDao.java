package se.lnu.agile.mymanuals.dao;

import org.springframework.data.repository.CrudRepository;
import se.lnu.agile.mymanuals.model.Company;

/**
 * Created by ilyakruikov on 11/11/16.
 */
public interface CompanyDao extends CrudRepository<Company, Long> {

    Company findByEmail(String email);

}
