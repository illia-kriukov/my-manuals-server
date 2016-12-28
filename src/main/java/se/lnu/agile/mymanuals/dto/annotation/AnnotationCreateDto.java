package se.lnu.agile.mymanuals.dto.annotation;

import javax.validation.constraints.NotNull;

/**
 * Created by Daniel on 17.12.2016.
 */
public class AnnotationCreateDto {

    @NotNull(message = "Annotation text should not be empty.")
    private String annotation;

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

}