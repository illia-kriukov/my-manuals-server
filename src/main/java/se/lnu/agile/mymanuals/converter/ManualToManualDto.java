package se.lnu.agile.mymanuals.converter;

import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.manual.ManualDto;
import se.lnu.agile.mymanuals.model.Manual;

import java.util.function.Function;

/**
 * Created by Lo.Gas_2 on 7/12/2016.
 */
@Component
public class ManualToManualDto implements Function<Manual, ManualDto> {

    @Override
    public ManualDto apply(Manual manual) {
        return new ManualDto(manual.getId(), manual.getName());
    }

}