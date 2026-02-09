package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.sep_monitor.SepMonitorCreateDto;
import sardorcreate.dto.sep_monitor.SepMonitorDto;
import sardorcreate.entity.Inventory;
import sardorcreate.entity.SepMonitor;
import sardorcreate.exception.AlreadyExistsException;
import sardorcreate.mapper.SepMonMapper;
import sardorcreate.repository.InventoryRepository;
import sardorcreate.repository.SepMonRepository;
import sardorcreate.util.ZXingUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SepMonService {

    private final SepMonRepository sepMonRepository;
    private final SepMonMapper sepMonMapper;
    private final InventoryRepository inventoryRepository;

    public ResponseEntity<?> createSepMon(SepMonitorCreateDto dto) {

        Inventory inventory = new Inventory();

        inventory.setInventoryId(dto.getInventoryId());

        try {
            inventoryRepository.save(inventory);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException("The tool with this inventory_id already exists");
        }

        SepMonitor sepMon = sepMonMapper.dtoToEntity(dto, inventory);

        SepMonitor save = sepMonRepository.save(sepMon);

        byte[] pdf;

        try {
            pdf = ZXingUtil.generatePdf(String.valueOf(save.getInventoryId().getInventoryId()), "sep_monitor");
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

    public ResponseEntity<?> getSepMonByInventoryId(long id) {

        Optional<SepMonitor> byInventoryId = sepMonRepository.findByInventoryId_InventoryId(id);

        if (byInventoryId.isEmpty()) {
            throw new RuntimeException("The tool with this inventory_id does not exist");
        }

        SepMonitor sepMon = byInventoryId.get();

        SepMonitorDto dto = sepMonMapper.entityToDto(sepMon);

        return ResponseEntity.ok(dto);
    }
}
