package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.entities.CodeInfo;
import platform.DTO.CodeInfoDTO;
import platform.services.CodeInfoDTOService;
import platform.services.CodeInfoPersistenceService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/code")
public class CodeController {

    CodeInfoPersistenceService codeInfoPersistenceService;

    CodeInfoDTOService codeInfoDTOService;

    @Autowired
    public CodeController(CodeInfoPersistenceService codeInfoPersistenceService, CodeInfoDTOService codeInfoDTOService) {
        this.codeInfoPersistenceService = codeInfoPersistenceService;
        this.codeInfoDTOService = codeInfoDTOService;
    }

    @GetMapping("/new")
    public String newCode(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        return "code/new";
    }

    @GetMapping("/{id}")
    public String getCodeById(@PathVariable UUID id, Model model) {
        CodeInfo codeInfo = codeInfoPersistenceService.findById(id);
        CodeInfoDTO codeInfoDTOResponse =codeInfoDTOService.convertCodeInfoToCodeDTO(codeInfo);
        codeInfoPersistenceService.saveAfterView(codeInfo);
        model.addAttribute("date", codeInfoDTOResponse.getDate());
        model.addAttribute("code", codeInfoDTOResponse.getCode());
        return "code/code";
    }

    @GetMapping("/latest")
    public String getLatestCodeInfos(Model model) {
        List<CodeInfo> codeInfoList = codeInfoPersistenceService.getLast10List();
        model.addAttribute("last10",
                codeInfoDTOService.convertListCodeInfoToCodeDTOList(codeInfoList));
        codeInfoPersistenceService.saveListAfterView(codeInfoList);
        return "code/latest";
    }

}
