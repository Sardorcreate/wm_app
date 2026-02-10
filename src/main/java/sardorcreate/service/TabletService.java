package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.tablet.TabletCreateDto;
import sardorcreate.dto.tablet.TabletDto;
import sardorcreate.entity.Inventory;
import sardorcreate.entity.Tablet;
import sardorcreate.exception.AlreadyExistsException;
import sardorcreate.exception.NotExistsException;
import sardorcreate.mapper.TabletMapper;
import sardorcreate.repository.InventoryRepository;
import sardorcreate.repository.TabletRepository;
import sardorcreate.util.ZXingUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TabletService {

    private final TabletRepository tabletRepository;
    private final TabletMapper tabletMapper;
    private final InventoryRepository inventoryRepository;

    public ResponseEntity<?> createTablet(TabletCreateDto dto) {

        Inventory inventory = new Inventory();

        inventory.setInventoryId(dto.getInventoryId());

        try {
            inventoryRepository.save(inventory);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException("The tool with this inventory_id already exists");
        }

        Tablet tablet = tabletMapper.dtoToEntity(dto, inventory);

        Tablet save = tabletRepository.save(tablet);

        byte[] pdf;

        try {
            pdf = ZXingUtil.generatePdf(String.valueOf(save.getInventoryId().getInventoryId()), "tablet");
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

    public ResponseEntity<?> getTabletByInventoryId(long id) {

        Optional<Tablet> byInventoryId = tabletRepository.findByInventoryId_InventoryId(id);

        if (byInventoryId.isEmpty()) {
            throw new NotExistsException("The tool with this inventory_id does not exist");
        }

        Tablet tablet = byInventoryId.get();

        TabletDto dto = tabletMapper.entityToDto(tablet);

        return ResponseEntity.ok(dto);
    }
}
