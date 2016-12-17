package se.lnu.agile.mymanuals.dto.annotation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by Daniel on 17.12.2016.
 */
public class AnnotationCreateDto {

    @NotNull(message = "Name should not be empty.")
    //@Pattern(regexp = "^[A-Z]([a-zA-Z0-9]|[- ])*$", message = "Incorrect category name format.")
    private String annotation;

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
}
