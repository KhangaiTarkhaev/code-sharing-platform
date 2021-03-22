package platform.services;

import org.springframework.stereotype.Service;
import platform.DTO.CodeInfoDTO;
import platform.entities.CodeInfo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CodeInfoDTOService {

    CodeInfoValidationService codeInfoValidationService;

    public CodeInfoDTOService(CodeInfoValidationService codeInfoValidationService) {
        this.codeInfoValidationService = codeInfoValidationService;
    }

    public List<CodeInfoDTO> convertListCodeInfoToCodeDTOList(List<CodeInfo> codeInfos) {
        List<CodeInfoDTO> codeInfoDTOList = new ArrayList<>();
        for (CodeInfo codeInfo : codeInfos) {
            codeInfoDTOList.add(new CodeInfoDTO(codeInfo.getCode(), codeInfo.getLoadDateTime()));
        }
        return codeInfoDTOList;
    }

    public CodeInfoDTO convertCodeInfoToCodeDTO(CodeInfo codeInfo) {
        CodeInfoDTO codeInfoDTO = new CodeInfoDTO(codeInfo.getCode(), codeInfo.getLoadDateTime());
        if (codeInfo.isLimitedViews()) {
            codeInfoDTO.setViews(codeInfo.getViews());
        } else {
            codeInfoDTO.setViews(0);
        }
        if (codeInfo.isLimitedTime()) {
            codeInfoDTO.setTime(Duration.between(LocalDateTime.now(), codeInfo.getExpireTime()).getSeconds());
        } else {
            codeInfoDTO.setTime(0L);
        }
        return codeInfoDTO;
    }

    public CodeInfo convertDTOtoCodeInfo(CodeInfoDTO codeInfoDTO) {
        CodeInfo codeInfo = new CodeInfo();
        codeInfo.setCode(codeInfoDTO.getCode());
        codeInfo.setExpireTimeInSeconds(codeInfoDTO.getTime());
        codeInfo.setViews(codeInfoDTO.getViews());
        return codeInfo;
    }
}
