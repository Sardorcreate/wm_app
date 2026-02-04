package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.scanner.ScannerCreateDto;
import sardorcreate.dto.scanner.ScannerDto;
import sardorcreate.entity.Scanner;
import sardorcreate.mapper.ScannerMapper;
import sardorcreate.repository.ScannerRepository;
import sardorcreate.util.ZXingUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScannerService {

    private final ScannerRepository scannerRepository;
    private final ScannerMapper scannerMapper;

    public ResponseEntity<?> createScanner(ScannerCreateDto dto) {

        Optional<Scanner> byInventoryId = scannerRepository.findByInventoryId(dto.getInventoryId());

        if (byInventoryId.isPresent()) {
            throw new RuntimeException("The tool with this inventory_id already exists");
        }

        Scanner scan = scannerMapper.dtoToEntity(dto);

        Scanner save = scannerRepository.save(scan);

        byte[] zip;

        try {
            zip = ZXingUtil.generateZip(String.valueOf(save.getInventoryId()), "scanner");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + save.getInventoryId() + "_codes.zip\"")
                .contentType(MediaType.parseMediaType("application/zip"))
                .contentLength(zip.length)
                .body(zip);
    }

    public ResponseEntity<?> getScannerByInventoryId(long id) {

        Optional<Scanner> byInventoryId = scannerRepository.findByInventoryId(id);

        if (byInventoryId.isEmpty()) {
            throw new RuntimeException("The tool with this inventory_id does not exist");
        }

        Scanner scan = byInventoryId.get();

        ScannerDto dto = scannerMapper.entityToDto(scan);

        return ResponseEntity.ok(dto);
    }
}
