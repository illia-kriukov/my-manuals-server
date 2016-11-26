package se.lnu.agile.mymanuals.dto.representative;

import se.lnu.agile.mymanuals.dto.company.CompanyDto;
import se.lnu.agile.mymanuals.dto.company.CompanyInfoDto;

/**
 * Created by ilyakruikov on 11/11/16.
 */
public class RepresentativeInfoDto {

    private String email;

    private String name;

    private CompanyInfoDto company;

    public RepresentativeInfoDto(Long id, String email, String password, String name, CompanyInfoDto company) {
        this.email = email;
        this.name = name;
        this.company = company;
    }

    public RepresentativeInfoDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanyInfoDto getCompany() {
        return company;
    }

    public void setCompany(CompanyInfoDto company) {
        this.company = company;
    }

}