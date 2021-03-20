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
import platform.services.CodeInfoService;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/code")
public class CodeController {

    CodeInfoService codeInfoService;

    CodeInfoDTOService codeInfoDTOService;

    @Autowired
    public CodeController(CodeInfoService codeInfoService, CodeInfoDTOService codeInfoDTOService) {
        this.codeInfoService = codeInfoService;
        this.codeInfoDTOService = codeInfoDTOService;
    }

    @GetMapping("/new")
    public String newCode(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        return "code/new";
    }

    @GetMapping("/{id}")
    public String getCodeById(@PathVariable Long id, Model model) {
        CodeInfo codeInfo = codeInfoService.findCodeInfoById(id);
        CodeInfoDTO codeInfoDTOResponse =codeInfoDTOService.convertCodeInfoToCodeDTO(codeInfo);
        model.addAttribute("date", codeInfoDTOResponse.getDate());
        model.addAttribute("code", codeInfoDTOResponse.getCode());
        return "code/code";
    }

    @GetMapping("/latest")
    public String getLatestCodeInfos(Model model) {
        model.addAttribute("last10",
                codeInfoDTOService.convertListCodeInfoToCodeDTOList(codeInfoService.getLatest10CodeInfo()));
        return "code/latest";
    }

}
