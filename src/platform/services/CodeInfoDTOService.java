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

    public CodeInfoDTOService() {
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
        codeInfoDTO.setTime(Duration.between(LocalDateTime.now(), codeInfo.getExpireTime()).getSeconds());
        codeInfo.setViews();
        return ;
    }

    public CodeInfo convertDTOtoCodeInfo(CodeInfoDTO codeInfoDTO) {
        CodeInfo codeInfo = new CodeInfo();
        codeInfo.setCode(codeInfoDTO.getCode());
        codeInfo.setExpireTime(LocalDateTime.now().plusSeconds(codeInfoDTO.getTime()));
        codeInfo.setViews(codeInfoDTO.getViews());
        return codeInfo;
    }
}
