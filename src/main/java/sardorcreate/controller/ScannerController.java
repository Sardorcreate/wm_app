package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sardorcreate.dto.scanner.ScannerCreateDto;
import sardorcreate.service.ScannerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scanner")
public class ScannerController {

    private final ScannerService scannerService;

    @PostMapping("/create")
    public ResponseEntity<?> createScanner(@RequestBody ScannerCreateDto dto) {

        return scannerService.createScanner(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getScannerByInventoryId(@PathVariable long id) {

        return scannerService.getScannerByInventoryId(id);
    }
}
