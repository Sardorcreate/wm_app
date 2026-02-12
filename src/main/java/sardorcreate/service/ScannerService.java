package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.FilterDto;
import sardorcreate.dto.scanner.ScannerCreateDto;
import sardorcreate.dto.scanner.ScannerDto;
import sardorcreate.entity.Inventory;
import sardorcreate.entity.Scanner;
import sardorcreate.exception.AlreadyExistsException;
import sardorcreate.exception.NotExistsException;
import sardorcreate.mapper.ScannerMapper;
import sardorcreate.repository.InventoryRepository;
import sardorcreate.repository.ScannerRepository;
import sardorcreate.util.GenericSpecification;
import sardorcreate.util.ZXingUtil;

import java.util.ArrayList;
import java.util.List;

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

        Scanner scan = scannerRepository
                .findByInventoryId_InventoryIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                        new NotExistsException("The tool with this inventory_id does not exist")
                );

        ScannerDto dto = scannerMapper.entityToDto(scan);

        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> deleteScannerByInventoryId(long id) {

        Scanner scan = scannerRepository
                .findByInventoryId_InventoryIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                            new NotExistsException("The tool with this inventory_id does not exist")
                        );

        scan.setDeleted(true);
        scannerRepository.save(scan);

        return ResponseEntity.ok("Successfully deleted");
    }

    public ResponseEntity<?> getScanByFilter(FilterDto dto) {

        List<Scanner> all = scannerRepository.findAll(GenericSpecification.filter(
                dto.getInventoryId(),
                dto.getDate(),
                dto.getStatus()
        ));

        List<ScannerDto> scanDtos = new ArrayList<>();

        for (Scanner scan : all) {
            ScannerDto scanDto = scannerMapper.entityToDto(scan);

            scanDtos.add(scanDto);
        }

        return ResponseEntity.ok(scanDtos);
    }

    public ResponseEntity<?> getScanById(long id) {

        Scanner scan = scannerRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotExistsException("The tool with this id does not exist")
                );

        ScannerDto scanDto = scannerMapper.entityToDto(scan);

        return ResponseEntity.ok(scanDto);
    }
}
