package platform.services;

import org.springframework.stereotype.Service;
import platform.DTO.CodeInfoDTO;
import platform.entities.CodeInfo;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodeInfoDTOService {

    public CodeInfoDTOService() {
    }

    public List<CodeInfoDTO> convertListCodeInfoToCodeDTOList(List<CodeInfo> codeInfos) {
        List<CodeInfoDTO> codeInfoDTOList = new ArrayList<>();
        for (CodeInfo codeInfo : codeInfos) {
            codeInfoDTOList.add(new CodeInfoDTO(codeInfo.getCode(), codeInfo.getDate()));
        }
        return codeInfoDTOList;
    }

    public CodeInfoDTO convertCodeInfoToCodeDTO(CodeInfo codeInfo) {
        return new CodeInfoDTO(codeInfo.getCode(), codeInfo.getDate());
    }
}
