package platform.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
public class CodeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String code;

    @Column
    private LocalDateTime loadDateTime;

    @Column
    private LocalDateTime expireTime;

    @Transient
    private Long expireTimeInSeconds;

    @Column
    private Integer views;

    @PrePersist
    protected void prePersist() {
        if (this.loadDateTime == null) {
            loadDateTime = LocalDateTime.now();
        }
        if (this.expireTime == null && expireTimeInSeconds != null) {
            expireTime = loadDateTime.plusSeconds(expireTimeInSeconds);
        }
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setLoadDateTime(LocalDateTime date) {
        this.loadDateTime = date;
    }

    public Long getExpireTimeInSeconds() {
        return expireTimeInSeconds;
    }

    public void setExpireTimeInSeconds(Long expireTimeInSeconds) {
        this.expireTimeInSeconds = expireTimeInSeconds;
    }

    @Override
    public String toString() {
        return "CodeInfo{" +
                "code='" + code + '\'' +
                ", date='" + loadDateTime + '\'' +
                '}';
    }

}

