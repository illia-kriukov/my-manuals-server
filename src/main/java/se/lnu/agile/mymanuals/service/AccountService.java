package se.lnu.agile.mymanuals.service;

import se.lnu.agile.mymanuals.dto.*;

import java.util.List;

/**
 * Created by ilyakruikov on 11/10/16.
 */
public interface AccountService {

    void createCompany(CompanySignUpDto dto);

    RepresentativeDto getRepresentative(String email);

    void createRepresentative(RepresentativeSignUpDto dto);

    void createCategory(CategorySignUpDto dto);

    List<CategoryDto> listCategories();

    void createProduct(ProductDto dto);

}