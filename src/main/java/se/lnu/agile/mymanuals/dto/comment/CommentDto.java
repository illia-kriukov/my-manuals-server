package se.lnu.agile.mymanuals.dto.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import se.lnu.agile.mymanuals.dto.company.CompanyInfoDto;

/**
 * Created by ilyakruikov on 12/21/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentDto {

    private Long id;

    private String userEmail;

    private String userName;

    private CompanyInfoDto company;

    private String dateTime;

    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public CompanyInfoDto getCompany() {
        return company;
    }

    public void setCompany(CompanyInfoDto company) {
        this.company = company;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}