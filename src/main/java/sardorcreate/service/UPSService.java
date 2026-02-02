package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.ups.UPSCreateDto;
import sardorcreate.dto.ups.UPSDto;
import sardorcreate.entity.Employee;
import sardorcreate.entity.UPS;
import sardorcreate.mapper.UPSMapper;
import sardorcreate.repository.UPSRepository;
import sardorcreate.util.ZXingUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UPSService {

    private final UPSRepository upsRepository;
    private final UPSMapper upsMapper;
    private final EmployeeService employeeService;

    public ResponseEntity<?> createUps(UPSCreateDto dto) {

        Optional<UPS> byInventoryId = upsRepository.findByInventoryId(dto.getInventoryId());

        if (byInventoryId.isPresent()) {
            throw new RuntimeException("The tool with this inventory_id already exists");
        }

        Employee owner = employeeService.getEmployee(dto.getOwner());

        UPS ups = upsMapper.dtoToEntity(dto, owner);

        UPS save = upsRepository.save(ups);

        byte[] zip;

        try {
            zip = ZXingUtil.generateZip(String.valueOf(save.getInventoryId()), "ups");
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

    public ResponseEntity<?> getUpsByInventoryId(long id) {

        Optional<UPS> byInventoryId = upsRepository.findByInventoryId(id);

        if (byInventoryId.isEmpty()) {
            throw new RuntimeException("The tool with this inventory_id does not exist");
        }

        UPS ups = byInventoryId.get();

        UPSDto dto = upsMapper.entityToDto(ups);

        return ResponseEntity.ok(dto);
    }
}
