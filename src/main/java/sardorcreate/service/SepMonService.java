package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.FilterDto;
import sardorcreate.dto.sep_monitor.SepMonitorCreateDto;
import sardorcreate.dto.sep_monitor.SepMonitorDto;
import sardorcreate.entity.Inventory;
import sardorcreate.entity.SepMonitor;
import sardorcreate.exception.AlreadyExistsException;
import sardorcreate.exception.NotExistsException;
import sardorcreate.mapper.SepMonMapper;
import sardorcreate.repository.InventoryRepository;
import sardorcreate.repository.SepMonRepository;
import sardorcreate.util.GenericSpecification;
import sardorcreate.util.ZXingUtil;

import java.util.ArrayList;
import java.util.List;

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

        SepMonitor sepMon = sepMonRepository
                .findByInventoryId_InventoryIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                        new NotExistsException("The tool with this inventory_id does not exist")
                );

        SepMonitorDto dto = sepMonMapper.entityToDto(sepMon);

        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> deleteSepMonByInventoryId(long id) {

        SepMonitor sepMon = sepMonRepository
                .findByInventoryId_InventoryIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                            new NotExistsException("The tool with this inventory_id does not exist")
                        );

        sepMon.setDeleted(true);
        sepMonRepository.save(sepMon);

        return ResponseEntity.ok("Successfully deleted");
    }

    public ResponseEntity<?> getSepMonByFilter(FilterDto dto) {

        List<SepMonitor> all = sepMonRepository.findAll(GenericSpecification.filter(
                dto.getInventoryId(),
                dto.getDate(),
                dto.getStatus()
        ));

        List<SepMonitorDto> sepMonDtos = new ArrayList<>();

        for (SepMonitor sepMon : all) {
            SepMonitorDto sepMonDto = sepMonMapper.entityToDto(sepMon);

            sepMonDtos.add(sepMonDto);
        }

        return ResponseEntity.ok(sepMonDtos);
    }

    public ResponseEntity<?> getSepMonById(long id) {

        SepMonitor sepMon = sepMonRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotExistsException("The tool with this id does not exist")
                );

        SepMonitorDto dto = sepMonMapper.entityToDto(sepMon);

        return ResponseEntity.ok(dto);
    }
}
