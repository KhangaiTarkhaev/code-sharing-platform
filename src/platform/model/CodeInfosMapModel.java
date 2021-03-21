package platform.model;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import platform.entities.CodeInfo;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Profile("map")
public class CodeInfosMapModel {

    private final Map<UUID, CodeInfo> codeInfoMap = new HashMap<>();

    public CodeInfosMapModel() {

    }

    public Map<UUID, CodeInfo> getCodeInfoMap() {
        return codeInfoMap;
    }

    public CodeInfo saveInMap(CodeInfo codeInfo) {
        UUID id = UUID.randomUUID();
        codeInfo.setId(id);
        codeInfo.setLoadDate(LocalDateTime.now());
        codeInfoMap.put(id ,codeInfo);
        return codeInfo;
    }

}


