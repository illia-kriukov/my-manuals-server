package se.lnu.agile.mymanuals.converter;

import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.manual.ManualDto;
import se.lnu.agile.mymanuals.model.Manual;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Lo.Gas_2 on 7/12/2016.
 */
@Component
public class ManualToManualListDto implements Function<List<Manual>, List<ManualDto> > {

    @Override
    public List<ManualDto> apply(List<Manual> manuals) {
        List<ManualDto> manualsDto = new ArrayList<>();
        for(Manual manual : manuals){
            ManualDto manualDto = new ManualDto(manual.getId(), manual.getName());
            manualsDto.add(manualDto);
        }
        return manualsDto;
    }
}
