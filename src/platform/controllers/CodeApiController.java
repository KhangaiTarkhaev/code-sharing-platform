package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.entities.CodeInfo;
import platform.DTO.CodeInfoDTO;
import platform.services.CodeInfoDTOService;
import platform.services.CodeInfoService;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<CodeInfoDTO> apiCodeNew(@RequestBody CodeInfo code) {
        CodeInfoDTO responseCodeInfo = new CodeInfoDTO();
        codeInfoService.save(code);
        responseCodeInfo.setId(code.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(responseCodeInfo, headers, HttpStatus.OK);
    }

    @GetMapping("/code/{id}")
    public ResponseEntity<CodeInfoDTO> apiCodeById(@PathVariable UUID id) {
        CodeInfoDTO responseCodeInfo = codeInfoDTOService.convertCodeInfoToCodeDTO(codeInfoService.findById(id));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(responseCodeInfo, headers, HttpStatus.OK);
    }

    @GetMapping("code/latest")
    public ResponseEntity<List<CodeInfoDTO>> getLatestCodeInfo() {
        List<CodeInfoDTO> codeInfoDTOList = codeInfoDTOService.
                convertListCodeInfoToCodeDTOList(codeInfoService.getLast10List());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(codeInfoDTOList, headers, HttpStatus.OK);
    }
}
