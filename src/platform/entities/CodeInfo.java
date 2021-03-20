package platform.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class CodeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String code;

    @Column
    private LocalDateTime loadDateTime;

    @PrePersist
    protected void prePersist() {
        if (this.loadDateTime == null) {
            loadDateTime = LocalDateTime.now();
        }
    }

    public CodeInfo() {
    }


    public CodeInfo(String code) {
        this.code = code;
    }

    public LocalDateTime getLoadDateTime() {
        return loadDateTime;
    }

    public void setLoadDate(LocalDateTime date) {
        this.loadDateTime = date;
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

    public void setLoadDateTime(LocalDateTime date) {
        this.loadDateTime = date;
    }

    @Override
    public String toString() {
        return "CodeInfo{" +
                "code='" + code + '\'' +
                ", date='" + loadDateTime + '\'' +
                '}';
    }

}

