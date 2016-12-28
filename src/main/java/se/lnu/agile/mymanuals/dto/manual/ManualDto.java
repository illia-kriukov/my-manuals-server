package se.lnu.agile.mymanuals.dto.manual;

/**
 * Created by Lo.Gas_2 on 7/12/2016.
 */
public class ManualDto {

    private Long id;

    private String name;

    private byte[] file;

    public ManualDto(Long id, String name, byte[] file) {
        this.id = id;
        this.name = name;
        this.file = file;
    }

    public ManualDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

}