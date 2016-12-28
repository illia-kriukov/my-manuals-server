package se.lnu.agile.mymanuals.dto.annotation;

/**
 * Created by Daniel on 16.12.2016.
 */
public class ManualAnnotationDto {

    private Long id;

    private String annotation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

}