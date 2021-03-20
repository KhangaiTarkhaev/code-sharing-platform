package platform.model;

import org.springframework.stereotype.Component;
import platform.entities.CodeInfo;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CodeModel {

    private static volatile CodeModel instance;

    private Long currentId = 0L;

    private Map<Long, CodeInfo> codeInfoMap = new HashMap<>();

    public CodeModel() {

    }

//    @PostConstruct
//    public void init() {
//        codeInfoMap.put(incrementAndGetCurrentId(), new CodeInfo("public static void ..."));
//    }

    private Long incrementAndGetCurrentId() {
        currentId++;
        return currentId;
    }

    public Map<Long, CodeInfo> getCodeInfoMap() {
        return codeInfoMap;
    }

    public CodeInfo putInEntryMapAndAutoGenerateId(CodeInfo codeInfo) {
        Long id = incrementAndGetCurrentId();
        codeInfo.setId(id);
        codeInfoMap.put(id ,codeInfo);
        return codeInfo;
    }

}


