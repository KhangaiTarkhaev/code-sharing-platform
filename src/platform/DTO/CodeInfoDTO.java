package platform.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import platform.entities.CodeInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeInfoDTO {

    private String id;

    private String code;

    private String date;

    private Long time;

    private Integer views;

    public CodeInfoDTO() {
    }

    public CodeInfoDTO(CodeInfo codeInfo) {
        setId(codeInfo.getId());
        setCode(codeInfo.getCode());
        setDate(codeInfo.getLoadDateTime());
    }

    public CodeInfoDTO(UUID id, String code) {
        setId(id);
        this.code = code;
    }

    public CodeInfoDTO(String code, LocalDateTime dateTime) {
        this.code = code;
        setDate(dateTime);
    }

    public CodeInfoDTO(String code, Long time, Integer views) {
        this.code = code;
        this.time = time;
        this.views = views;
    }

    public String getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id.toString();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }
}
