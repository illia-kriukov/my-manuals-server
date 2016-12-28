package se.lnu.agile.mymanuals.converter;

import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.manual.ManualInfoDto;
import se.lnu.agile.mymanuals.model.Manual;

import java.util.function.Function;

/**
 * Created by ilyakruikov on 12/8/16.
 */
@Component
public class ManualToManualInfoDto implements Function<Manual, ManualInfoDto> {

    @Override
    public ManualInfoDto apply(Manual manual) {
        return new ManualInfoDto(manual.getId(), manual.getName());
    }

}