package platform.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.entities.CodeInfo;
import platform.DTO.CodeInfoDTO;
import platform.entities.Views;
import platform.services.CodeInfoDTOService;
import platform.services.CodeInfoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CodeApiController {

    CodeInfoService codeInfoService;

    CodeInfoDTOService codeInfoDTOService;

    @Autowired
    public CodeApiController(CodeInfoService codeInfoService, CodeInfoDTOService codeInfoDTOService) {
        this.codeInfoService = codeInfoService;
        this.codeInfoDTOService = codeInfoDTOService;
    }

    @PostMapping(value = "/code/new", consumes = "application/json")
   // @JsonView(Views.OnlyIdView.class)
    public ResponseEntity<CodeInfoDTO> apiCodeNew(@RequestBody CodeInfo code) {
        CodeInfoDTO responseCodeInfo = new CodeInfoDTO();
        codeInfoService.saveCodeInfo(code);
        responseCodeInfo.setId(code.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(responseCodeInfo, headers, HttpStatus.OK);
    }

    @GetMapping("/code/{id}")
 //   @JsonView(Views.CodeInfoAndDateView.class)
    public ResponseEntity<CodeInfoDTO> apiCodeById(@PathVariable Long id) {
        CodeInfoDTO responseCodeInfo = codeInfoDTOService.convertCodeInfoToCodeDTO(codeInfoService.findCodeInfoById(id));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(responseCodeInfo, headers, HttpStatus.OK);
    }

    @GetMapping("code/latest")
    public ResponseEntity<List<CodeInfoDTO>> getLatestCodeInfo() {
        List<CodeInfoDTO> codeInfoDTOList = codeInfoDTOService.
                convertListCodeInfoToCodeDTOList(codeInfoService.
                        getLatest10CodeInfo());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(codeInfoDTOList, headers, HttpStatus.OK);
    }
}
