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

@Profile("h2db")
@Service
public class CodeInfoDatabaseService implements CodeInfoService {

    CodeRepository codeRepository;

    public CodeInfoDatabaseService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    public CodeInfo findById(Long id) {
        Optional<CodeInfo> codeInfoOptional = codeRepository.findById(id);
        if (codeInfoOptional.isPresent()) {
            return codeInfoOptional.get();
        } else {
            throw new RuntimeException("Cannot find CodeInfo by Id");
        }
    }

    @Override
    public CodeInfo save(CodeInfo codeInfo) {
        return codeRepository.save(codeInfo);
    }

    @Override
    public List<CodeInfo> getLast10List() {
        Page<CodeInfo> codeInfosPage = codeRepository.findAll(PageRequest.of(0,10, Sort.by(Sort.Direction.DESC, "loadDateTime")));
        List<CodeInfo> latest10CodeInfosList = new LinkedList<>();
        if (codeInfosPage.hasContent()) {
            latest10CodeInfosList = codeInfosPage.getContent();
        }
        return latest10CodeInfosList;
    }
}
