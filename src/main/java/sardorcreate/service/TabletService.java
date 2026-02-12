package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.FilterDto;
import sardorcreate.dto.tablet.TabletCreateDto;
import sardorcreate.dto.tablet.TabletDto;
import sardorcreate.entity.Inventory;
import sardorcreate.entity.Tablet;
import sardorcreate.exception.AlreadyExistsException;
import sardorcreate.exception.NotExistsException;
import sardorcreate.mapper.TabletMapper;
import sardorcreate.repository.InventoryRepository;
import sardorcreate.repository.TabletRepository;
import sardorcreate.util.GenericSpecification;
import sardorcreate.util.ZXingUtil;

import java.util.ArrayList;
import java.util.List;

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

        Tablet tablet = tabletRepository
                .findByInventoryId_InventoryIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                        new NotExistsException("The tool with this inventory_id does not exist")
                );

        TabletDto dto = tabletMapper.entityToDto(tablet);

        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> deleteTabletByInventoryId(long id) {

        Tablet tablet = tabletRepository
                .findByInventoryId_InventoryIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                            new NotExistsException("The tool with this inventory_id does not exist")
                        );

        tablet.setDeleted(true);
        tabletRepository.save(tablet);

        return ResponseEntity.ok("Successfully deleted");
    }

    public ResponseEntity<?> getTabletByFilter(FilterDto dto) {

        List<Tablet> all = tabletRepository.findAll(GenericSpecification.filter(
                dto.getInventoryId(),
                dto.getDate(),
                dto.getStatus()
        ));

        List<TabletDto> tabDtos = new ArrayList<>();

        for (Tablet tab : all) {
            TabletDto tabDto = tabletMapper.entityToDto(tab);

            tabDtos.add(tabDto);
        }

        return ResponseEntity.ok(tabDtos);
    }

    public ResponseEntity<?> getTabletById(long id) {

        Tablet tablet = tabletRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotExistsException("The tool with this id does not exist")
                );

        TabletDto tabDto = tabletMapper.entityToDto(tablet);

        return ResponseEntity.ok(tabDto);
    }
}
