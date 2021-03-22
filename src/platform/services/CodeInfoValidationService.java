package platform.services;

import org.springframework.stereotype.Service;
import platform.entities.CodeInfo;

import java.time.LocalDateTime;

@Service
public class CodeInfoValidationService {

    public CodeInfoValidationService() {
    }

    public boolean isValid(CodeInfo codeInfo) {
        if (codeInfo.isLimitedViews()) {
            if (codeInfo.getViews() <= 0) return false;
        }
        if (codeInfo.isLimitedTime()) {
            return !LocalDateTime.now().isAfter(codeInfo.getExpireTime());
        }
        return true;
    }



}
