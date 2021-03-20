package platform.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import platform.entities.CodeInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeInfoDTO {

    private String id;

    private String code;

    private String date;


    public CodeInfoDTO() {
    }

    public CodeInfoDTO(CodeInfo codeInfo) {
        setId(codeInfo.getId());
        setCode(codeInfo.getCode());
        setDate(codeInfo.getDate());
    }

    public CodeInfoDTO(Long id, String code) {
        setId(id);
        this.code = code;
    }

    public CodeInfoDTO(String code, LocalDateTime dateTime) {
        this.code = code;
        setDate(dateTime);
    }

    public String getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = Long.toString(id);
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
}
