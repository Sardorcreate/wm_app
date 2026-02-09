package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.ip.IpCreateDto;
import sardorcreate.dto.ip.IpDto;
import sardorcreate.entity.Inventory;
import sardorcreate.entity.IpPhone;
import sardorcreate.enums.ToolsStatus;
import sardorcreate.exception.AlreadyExistsException;
import sardorcreate.repository.InventoryRepository;
import sardorcreate.repository.IpRepository;
import sardorcreate.util.ZXingUtil;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IpService {

    private final IpRepository ipRepository;
    private final InventoryRepository inventoryRepository;

    public ResponseEntity<?> createIp(IpCreateDto dto) {

        Inventory inventory = new Inventory();

        inventory.setInventoryId(dto.getInventoryId());

        try {
            inventoryRepository.save(inventory);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException("The tool with this inventory_id already exists");
        }

        IpPhone ip = new IpPhone();

        ip.setInventoryId(inventory);
        ip.setModel(dto.getModel());
        ip.setDate(Instant.now());
        ip.setWhereFrom(dto.getWhereFrom());
        ip.setPrice(dto.getPrice());
        ip.setStatus(ToolsStatus.RESERVE);

        IpPhone save = ipRepository.save(ip);

        byte[] pdf;

        try {
            pdf = ZXingUtil.generatePdf(String.valueOf(save.getInventoryId().getInventoryId()), "ip_phone");
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

    public ResponseEntity<?> getIp(long id) {

        Optional<IpPhone> byInventoryId = ipRepository.findByInventoryId_InventoryId(id);

        if (byInventoryId.isEmpty()) {
            throw new RuntimeException("The tool with this inventory_id does not exist");
        }

        IpPhone ip = byInventoryId.get();

        IpDto dto = new IpDto();

        dto.setId(ip.getId());

        if (ip.getStatus().equals(ToolsStatus.GIVEN)) {
            dto.setOwner(ip.getOwner().getId());
        }
        dto.setInventoryId(ip.getInventoryId().getInventoryId());
        dto.setModel(ip.getModel());
        dto.setDate(ip.getDate());
        dto.setWhereFrom(ip.getWhereFrom());
        dto.setPrice(ip.getPrice());
        dto.setStatus(ip.getStatus());

        return ResponseEntity.ok(dto);
    }
}
