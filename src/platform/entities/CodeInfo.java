package platform.entities;

import com.fasterxml.jackson.annotation.JsonView;

import java.time.LocalDateTime;

@JsonView(Views.PublicView.class)
public class CodeInfo {

    @JsonView(Views.OnlyIdView.class)
    private Long id;

    @JsonView(Views.CodeInfoAndDateView.class)
    private String code;

    @JsonView({Views.CodeInfoAndDateView.class, Views.OnlyDateView.class})
    private LocalDateTime date;

    public CodeInfo() {
    }

    public CodeInfo(String code) {
        this.code = code;
        this.date = LocalDateTime.now();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setLoadDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CodeInfo{" +
                "code='" + code + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

