package platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import platform.entities.CodeInfo;
import platform.model.CodeInfosMapModel;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Profile("map")
@Service
public class CodeInfoMapPersistenceService implements CodeInfoPersistenceService {

    CodeInfosMapModel codeInfosMapModel;

    public CodeInfoMapPersistenceService() {
    }

    @Autowired
    public CodeInfoMapPersistenceService(CodeInfosMapModel codeInfosMapModel) {
        this.codeInfosMapModel = codeInfosMapModel;
    }

    @Override
    public CodeInfo findById(UUID id) {
        return codeInfosMapModel.getCodeInfoMap().get(id);
    }

    @Override
    public CodeInfo save(CodeInfo codeInfo) {
        codeInfosMapModel.saveInMap(codeInfo);
        return codeInfo;
    }

    @Override
    public CodeInfo saveAfterView(CodeInfo codeInfo) {
        return null;
    }

    @Override
    public <T extends CodeInfo> Iterable<T> saveListAfterView(Iterable<T> codeInfos) {
        return null;
    }

    @Override
    public List<CodeInfo> getLast10List() {
        List<CodeInfo> codeInfos = codeInfosMapModel.getCodeInfoMap().values().stream().sorted(new Comparator<CodeInfo>() {
            @Override
            public int compare(CodeInfo o1, CodeInfo o2) {
                if (o1.getLoadDateTime().isBefore(o2.getLoadDateTime())) {
                    return 1;
                } else if (o2.getLoadDateTime().isBefore(o1.getLoadDateTime())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }).collect(Collectors.toList());
        for (int i = codeInfos.size(); i > 10; i--) {
            codeInfos.remove(i - 1);
        }
        return codeInfos;
    }
}
