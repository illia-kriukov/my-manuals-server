package se.lnu.agile.mymanuals.dto;

/**
 * Created by ilyakruikov on 11/11/16.
 */
public class RepresentativeDto {

    private Long id;

    private String email;

    private String password;

    private String name;

    private CompanyDto company;

    public RepresentativeDto(Long id, String email, String password, String name, CompanyDto company) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.company = company;
    }

    public RepresentativeDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

}