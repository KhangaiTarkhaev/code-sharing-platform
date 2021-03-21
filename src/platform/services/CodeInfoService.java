package platform.services;

import platform.entities.CodeInfo;

import java.util.List;
import java.util.UUID;

public interface CodeInfoService {

    CodeInfo findById(UUID id);

    List<CodeInfo> getLast10List();

    CodeInfo save(CodeInfo codeInfo);
}
