package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.sep_monitor.SepMonitorCreateDto;
import sardorcreate.dto.sep_monitor.SepMonitorDto;
import sardorcreate.entity.Employee;
import sardorcreate.entity.SepMonitor;
import sardorcreate.mapper.SepMonMapper;
import sardorcreate.repository.SepMonRepository;
import sardorcreate.util.ZXingUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SepMonService {

    private final SepMonRepository sepMonRepository;
    private final SepMonMapper sepMonMapper;
    private final EmployeeService employeeService;

    public ResponseEntity<?> createSepMon(SepMonitorCreateDto dto) {

        Optional<SepMonitor> byInventoryId = sepMonRepository.findByInventoryId(dto.getInventoryId());

        if (byInventoryId.isPresent()) {
            throw new RuntimeException("The tool with this inventory_id already exists");
        }

        Employee owner = employeeService.getEmployee(dto.getOwner());

        SepMonitor sepMon = sepMonMapper.dtoToEntity(dto, owner);

        SepMonitor save = sepMonRepository.save(sepMon);

        byte[] zip;

        try {
            zip = ZXingUtil.generateZip(String.valueOf(save.getInventoryId()), "sep_monitor");
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

    public ResponseEntity<?> getSepMonByInventoryId(long id) {

        Optional<SepMonitor> byInventoryId = sepMonRepository.findByInventoryId(id);

        if (byInventoryId.isEmpty()) {
            throw new RuntimeException("The tool with this inventory_id does not exist");
        }

        SepMonitor sepMon = byInventoryId.get();

        SepMonitorDto dto = sepMonMapper.entityToDto(sepMon);

        return ResponseEntity.ok(dto);
    }
}
