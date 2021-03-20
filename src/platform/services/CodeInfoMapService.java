package platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.entities.CodeInfo;
import platform.model.CodeModel;
import platform.model.CodeRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeInfoService {

    CodeModel codeModel;

    CodeRepository codeRepository;

    public CodeInfoService() {
    }

    @Autowired
    public CodeInfoService(CodeModel codeModel) {
        this.codeModel = codeModel;
    }

    public CodeInfo findCodeInfoById(Long id) {
        return codeModel.getCodeInfoMap().get(id);
    }

    public CodeInfo saveCodeInfo(CodeInfo codeInfo) {
        codeInfo.setLoadDate(LocalDateTime.now());
        codeModel.putInEntryMapAndAutoGenerateId(codeInfo);
        return codeInfo;
    }

    public List<CodeInfo> getLatest10CodeInfo() {
        List<CodeInfo> codeInfos = codeModel.getCodeInfoMap().values().stream().sorted(new Comparator<CodeInfo>() {
            @Override
            public int compare(CodeInfo o1, CodeInfo o2) {
                if (o1.getDateTime().isBefore(o2.getDateTime())) {
                    return 1;
                } else if (o2.getDateTime().isBefore(o1.getDateTime())) {
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