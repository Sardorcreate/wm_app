package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.scanner.ScannerCreateDto;
import sardorcreate.dto.scanner.ScannerDto;
import sardorcreate.entity.Inventory;
import sardorcreate.entity.Scanner;
import sardorcreate.exception.AlreadyExistsException;
import sardorcreate.exception.NotExistsException;
import sardorcreate.mapper.ScannerMapper;
import sardorcreate.repository.InventoryRepository;
import sardorcreate.repository.ScannerRepository;
import sardorcreate.util.ZXingUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScannerService {

    private final ScannerRepository scannerRepository;
    private final ScannerMapper scannerMapper;
    private final InventoryRepository inventoryRepository;

    public ResponseEntity<?> createScanner(ScannerCreateDto dto) {

        Inventory inventory = new Inventory();

        inventory.setInventoryId(dto.getInventoryId());

        try {
            inventoryRepository.save(inventory);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException("The tool with this inventory_id already exists");
        }

        Scanner scan = scannerMapper.dtoToEntity(dto, inventory);

        Scanner save = scannerRepository.save(scan);

        byte[] pdf;

        try {
            pdf = ZXingUtil.generatePdf(String.valueOf(save.getInventoryId().getInventoryId()), "scanner");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + save.getInventoryId().getInventoryId() + "_codes.pdf\"")
                .contentType(MediaType.parseMediaType("application/pdf"))
                .contentLength(pdf.length)
                .body(pdf);
    }

    public ResponseEntity<?> getScannerByInventoryId(long id) {

        Optional<Scanner> byInventoryId = scannerRepository.findByInventoryId_InventoryId(id);

        if (byInventoryId.isEmpty()) {
            throw new NotExistsException("The tool with this inventory_id does not exist");
        }

        Scanner scan = byInventoryId.get();

        ScannerDto dto = scannerMapper.entityToDto(scan);

        return ResponseEntity.ok(dto);
    }
}
