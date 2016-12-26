package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.category.CategoryDto;
import se.lnu.agile.mymanuals.dto.comment.CommentDto;
import se.lnu.agile.mymanuals.dto.company.CompanyInfoDto;
import se.lnu.agile.mymanuals.dto.product.ProductListDto;
import se.lnu.agile.mymanuals.model.Category;
import se.lnu.agile.mymanuals.model.Comment;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by ilyakruikov on 12/21/16.
 * A converter from Company to CompanyDto.
 */
@Component
public class CommentToCommentDto implements Function<Comment, CommentDto> {

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public CommentDto apply(Comment comment) {
        CommentDto commentDto = new CommentDto();
        BeanUtils.copyProperties(comment, commentDto);
        commentDto.setDateTime(dateFormatter.format(comment.getDateTime()));
        return commentDto;
    }

}