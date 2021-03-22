package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.entities.CodeInfo;
import platform.DTO.CodeInfoDTO;
import platform.services.CodeInfoDTOService;
import platform.services.CodeInfoDataAccessService;
import platform.services.CodeInfoValidationService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CodeApiController {

    CodeInfoDataAccessService codeInfoDataAccessService;

    CodeInfoDTOService codeInfoDTOService;

    CodeInfoValidationService codeInfoValidationService;

    @Autowired
    public CodeApiController(CodeInfoDataAccessService codeInfoDataAccessService, CodeInfoDTOService codeInfoDTOService,
                             CodeInfoValidationService codeInfoValidationService) {
        this.codeInfoDataAccessService = codeInfoDataAccessService;
        this.codeInfoDTOService = codeInfoDTOService;
        this.codeInfoValidationService = codeInfoValidationService;
    }

    @PostMapping(value = "/code/new", consumes = "application/json")
    public ResponseEntity<CodeInfoDTO> apiCodeNew(@RequestBody CodeInfoDTO codeInfoDTO) {
        CodeInfoDTO responseCodeInfoDTO = new CodeInfoDTO();
        CodeInfo codeInfo = codeInfoDTOService.convertDTOtoCodeInfo(codeInfoDTO);
        codeInfoDataAccessService.save(codeInfo);
        responseCodeInfoDTO.setId(codeInfo.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(responseCodeInfoDTO, headers, HttpStatus.OK);
    }

    @GetMapping("/code/{id}")
    public ResponseEntity<CodeInfoDTO> apiCodeById(@PathVariable UUID id) {
        Optional<CodeInfo> codeInfoOptional = codeInfoDataAccessService.findById(id);
        if (codeInfoOptional.isPresent()) {
            CodeInfo codeInfo = codeInfoOptional.get();
            if (!codeInfoValidationService.isValid(codeInfo)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            CodeInfoDTO responseCodeInfo = codeInfoDTOService.convertCodeInfoToCodeDTO(codeInfo);
            codeInfoDataAccessService.saveAfterView(codeInfo);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            return new ResponseEntity<>(responseCodeInfo, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("code/latest")
    public ResponseEntity<List<CodeInfoDTO>> getLatestCodeInfo() {
        List<CodeInfo> codeInfoList = codeInfoDataAccessService.getLast10List();
        List<CodeInfoDTO> codeInfoDTOList = new ArrayList<>();
        for (CodeInfo codeInfo : codeInfoList) {
            if (codeInfoValidationService.isValid(codeInfo)) codeInfoDTOList.add(codeInfoDTOService.convertCodeInfoToCodeDTO(codeInfo));
        }
        codeInfoDataAccessService.saveListAfterView(codeInfoList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(codeInfoDTOList, headers, HttpStatus.OK);
    }
}
