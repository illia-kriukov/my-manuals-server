package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.manual.ManualDto;
import se.lnu.agile.mymanuals.dto.video.VideoDto;
import se.lnu.agile.mymanuals.model.Manual;
import se.lnu.agile.mymanuals.model.Video;

import java.util.function.Function;

/**
 * Created by ilyakruikov on 12/20/16.
 */
@Component
public class VideoToVideoDto implements Function<Video, VideoDto> {

    @Override
    public VideoDto apply(Video video) {
        VideoDto videoDto = new VideoDto();
        BeanUtils.copyProperties(video, videoDto);
        return videoDto;
    }

}