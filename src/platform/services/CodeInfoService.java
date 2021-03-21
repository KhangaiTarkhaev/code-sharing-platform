package platform.services;

import platform.entities.CodeInfo;

import java.util.List;

public interface CodeInfoService {

    CodeInfo findById(Long id);

    List<CodeInfo> getLast10List();

    CodeInfo save(CodeInfo codeInfo);
}
