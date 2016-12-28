package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.annotation.ManualAnnotationDto;
import se.lnu.agile.mymanuals.model.ManualAnnotation;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Daniel on 16.12.2016.
 */
@Component
public class ManualAnnotationListToManualAnnotationDtoList
        implements Function<List<ManualAnnotation>, List<ManualAnnotationDto>> {

    @Override
    public List<ManualAnnotationDto> apply(List<ManualAnnotation> manualAnnotations) {
        List<ManualAnnotationDto> manualAnnotationDtoList = new LinkedList<>();

        for (ManualAnnotation manualAnnotation : manualAnnotations){
            ManualAnnotationDto manualAnnotationDto = new ManualAnnotationDto();
            BeanUtils.copyProperties(manualAnnotation, manualAnnotationDto);
            manualAnnotationDtoList.add(manualAnnotationDto);
        }

        return manualAnnotationDtoList;
    }

}