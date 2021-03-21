package platform.services;

import platform.entities.CodeInfo;

import java.util.List;
import java.util.UUID;

public interface CodeInfoPersistenceService {

    CodeInfo findById(UUID id);

    List<CodeInfo> getLast10List();

    CodeInfo save(CodeInfo codeInfo);

    CodeInfo saveAfterView(CodeInfo codeInfo);

    <T extends CodeInfo> Iterable<T> saveListAfterView(Iterable<T> codeInfos);
}
