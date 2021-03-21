package platform.services;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import platform.entities.CodeInfo;
import platform.model.CodeRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Profile("h2db")
@Service
public class CodeInfoDatabasePersistenceService implements CodeInfoPersistenceService {

    CodeRepository codeRepository;

    public CodeInfoDatabasePersistenceService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    public CodeInfo findById(UUID id) {
        Optional<CodeInfo> codeInfoOptional = codeRepository.findById(id);
        if (codeInfoOptional.isPresent()) {
            CodeInfo codeInfo = codeInfoOptional.get();
         //   codeRepository.save(decrementViews(codeInfoOptional.get()));
            return codeInfo;
        } else {
            throw new RuntimeException("Cannot find CodeInfo by Id");
        }
    }

//    private CodeInfo decrementViews(CodeInfo codeInfo) {
//        codeInfo.setViews(codeInfo.getViews() - 1);
//        return codeInfo;
//    }

    public CodeInfo saveAfterView(CodeInfo codeInfo) {
        decrementViews(codeInfo);
        save(codeInfo);
        return codeInfo;
    }

    public<T extends CodeInfo> Iterable<T> saveListAfterView(Iterable<T> codeInfos) {
        for (CodeInfo codeInfo : codeInfos) {
            decrementViews(codeInfo);
        }
        return codeRepository.saveAll(codeInfos);
    }

    private void decrementViews(CodeInfo codeInfo) {
        codeInfo.setViews(codeInfo.getViews() - 1);
    }

    @Override
    public CodeInfo save(CodeInfo codeInfo) {
        return codeRepository.save(codeInfo);
    }

    @Override
    public List<CodeInfo> getLast10List() {
        PageRequest pageRequest = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC, "loadDateTime"));
        Page<CodeInfo> codeInfosPage = codeRepository.findAll(pageRequest);
        List<CodeInfo> latest10CodeInfosList = new LinkedList<>();
        if (codeInfosPage.hasContent()) {
            latest10CodeInfosList = codeInfosPage.getContent();
        }
        return latest10CodeInfosList;
    }


}
