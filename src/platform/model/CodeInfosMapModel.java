package platform.model;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import platform.entities.CodeInfo;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Profile("map")
public class CodeInfosMapModel {

    private static volatile CodeInfosMapModel instance;

    private Long currentId = 0L;

    private final Map<Long, CodeInfo> codeInfoMap = new HashMap<>();

    public CodeInfosMapModel() {

    }

    private Long incrementAndGetCurrentId() {
        currentId++;
        return currentId;
    }

    public Map<Long, CodeInfo> getCodeInfoMap() {
        return codeInfoMap;
    }

    public CodeInfo saveInMap(CodeInfo codeInfo) {
        Long id = incrementAndGetCurrentId();
        codeInfo.setId(id);
        codeInfo.setLoadDate(LocalDateTime.now());
        codeInfoMap.put(id ,codeInfo);
        return codeInfo;
    }

}

