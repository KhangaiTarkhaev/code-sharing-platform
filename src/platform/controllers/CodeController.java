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
import platform.services.CodeInfoDataAccessService;
import platform.services.CodeInfoValidationService;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/code")
public class CodeController {

    CodeInfoDataAccessService codeInfoDataAccessService;

    CodeInfoDTOService codeInfoDTOService;

    CodeInfoValidationService codeInfoValidationService;

    @Autowired
    public CodeController(CodeInfoDataAccessService codeInfoDataAccessService,
                          CodeInfoDTOService codeInfoDTOService,
                          CodeInfoValidationService codeInfoValidationService) {
        this.codeInfoDataAccessService = codeInfoDataAccessService;
        this.codeInfoDTOService = codeInfoDTOService;
        this.codeInfoValidationService = codeInfoValidationService;
    }

    @GetMapping("/new")
    public String newCode(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        return "code/new";
    }

    @GetMapping("/{id}")
    public String getCodeById(@PathVariable UUID id, Model model) {
        Optional<CodeInfo> codeInfoOptional = codeInfoDataAccessService.findById(id);
        if (codeInfoOptional.isPresent()) {
            CodeInfo codeInfo = codeInfoOptional.get();
            CodeInfoDTO codeInfoDTOResponse = codeInfoDTOService.convertCodeInfoToCodeDTO(codeInfo);
            codeInfoDataAccessService.saveAfterView(codeInfo);
            model.addAttribute("date", codeInfoDTOResponse.getDate());
            model.addAttribute("code", codeInfoDTOResponse.getCode());
            model.addAttribute("viewsRestriction", codeInfoDTOResponse.getViews());
            model.addAttribute("timeRestriction", codeInfoDTOResponse.getTime());
        }
        return "code/code";
    }

    @GetMapping("/latest")
    public String getLatestCodeInfos(Model model) {
        List<CodeInfo> codeInfoList = codeInfoDataAccessService.getLast10List();
        List<CodeInfoDTO> codeInfoDTOList = new ArrayList<>();
        for (CodeInfo codeInfo : codeInfoList) {
            if (codeInfoValidationService.isValid(codeInfo)) codeInfoDTOList.add(codeInfoDTOService.
                    convertCodeInfoToCodeDTO(codeInfo));
        }
        model.addAttribute("last10",
                codeInfoDTOList);
        codeInfoDataAccessService.saveListAfterView(codeInfoList);
        return "code/latest";
    }

}
