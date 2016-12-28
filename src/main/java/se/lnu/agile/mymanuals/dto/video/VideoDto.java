package se.lnu.agile.mymanuals.dto.video;

/**
 * Created by ilyakruikov on 12/20/16.
 */
public class VideoDto {

    private Long id;

    private String link;

    public VideoDto(Long id, String link) {
        this.id = id;
        this.link = link;
    }

    public VideoDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}