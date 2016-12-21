package se.lnu.agile.mymanuals.dto.comment;

import javax.validation.constraints.NotNull;

/**
 * Created by Lo.Gas_2 on 21/12/2016.
 */
public class CommentCreateDto {

    @NotNull(message = "Comment should not be empty.")
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String annotation) {
        this.comment = comment;
    }

}
