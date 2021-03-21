package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.entities.CodeInfo;
import platform.DTO.CodeInfoDTO;
import platform.services.CodeInfoDTOService;
import platform.services.CodeInfoPersistenceService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CodeApiController {

    CodeInfoPersistenceService codeInfoPersistenceService;

    CodeInfoDTOService codeInfoDTOService;

    @Autowired
    public CodeApiController(CodeInfoPersistenceService codeInfoPersistenceService, CodeInfoDTOService codeInfoDTOService) {
        this.codeInfoPersistenceService = codeInfoPersistenceService;
        this.codeInfoDTOService = codeInfoDTOService;
    }

    @PostMapping(value = "/code/new", consumes = "application/json")
    public ResponseEntity<CodeInfoDTO> apiCodeNew(@RequestBody CodeInfoDTO codeInfoDTO) {
        CodeInfoDTO responseCodeInfoDTO = new CodeInfoDTO();
        CodeInfo codeInfo = codeInfoDTOService.convertDTOtoCodeInfo(codeInfoDTO);
        codeInfoPersistenceService.save(codeInfo);
        responseCodeInfoDTO.setId(codeInfo.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(responseCodeInfoDTO, headers, HttpStatus.OK);
    }

    @GetMapping("/code/{id}")
    public ResponseEntity<CodeInfoDTO> apiCodeById(@PathVariable UUID id) {
        CodeInfo codeInfo = codeInfoPersistenceService.findById(id);
        CodeInfoDTO responseCodeInfo = codeInfoDTOService.convertCodeInfoToCodeDTO(codeInfo);

        codeInfoPersistenceService.saveAfterView(codeInfo);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(responseCodeInfo, headers, HttpStatus.OK);
    }

    @GetMapping("code/latest")
    public ResponseEntity<List<CodeInfoDTO>> getLatestCodeInfo() {
        List<CodeInfo> codeInfoList = codeInfoPersistenceService.getLast10List();
        List<CodeInfoDTO> codeInfoDTOList = codeInfoDTOService.
                convertListCodeInfoToCodeDTOList(codeInfoList);
        codeInfoPersistenceService.saveListAfterView(codeInfoList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(codeInfoDTOList, headers, HttpStatus.OK);
    }
}
