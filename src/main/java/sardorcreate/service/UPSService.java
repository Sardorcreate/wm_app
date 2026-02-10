package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.ups.UPSCreateDto;
import sardorcreate.dto.ups.UPSDto;
import sardorcreate.entity.Inventory;
import sardorcreate.entity.UPS;
import sardorcreate.exception.AlreadyExistsException;
import sardorcreate.exception.NotExistsException;
import sardorcreate.mapper.UPSMapper;
import sardorcreate.repository.InventoryRepository;
import sardorcreate.repository.UPSRepository;
import sardorcreate.util.ZXingUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UPSService {

    private final UPSRepository upsRepository;
    private final UPSMapper upsMapper;
    private final InventoryRepository inventoryRepository;

    public ResponseEntity<?> createUps(UPSCreateDto dto) {

        Inventory inventory = new Inventory();

        inventory.setInventoryId(dto.getInventoryId());

        try {
            inventoryRepository.save(inventory);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException("The tool with this inventory_id already exists");
        }

        UPS ups = upsMapper.dtoToEntity(dto, inventory);

        UPS save = upsRepository.save(ups);

        byte[] pdf;

        try {
            pdf = ZXingUtil.generatePdf(String.valueOf(save.getInventoryId().getInventoryId()), "ups");
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

    public ResponseEntity<?> getUpsByInventoryId(long id) {

        Optional<UPS> byInventoryId = upsRepository.findByInventoryId_InventoryId(id);

        if (byInventoryId.isEmpty()) {
            throw new NotExistsException("The tool with this inventory_id does not exist");
        }

        UPS ups = byInventoryId.get();

        UPSDto dto = upsMapper.entityToDto(ups);

        return ResponseEntity.ok(dto);
    }
}
