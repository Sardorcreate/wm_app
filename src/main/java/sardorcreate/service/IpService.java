package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.ip.IpCreateDto;
import sardorcreate.dto.ip.IpDto;
import sardorcreate.entity.Employee;
import sardorcreate.entity.IpPhone;
import sardorcreate.repository.IpRepository;
import sardorcreate.util.ZXingUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IpService {

    private final IpRepository ipRepository;
    private final EmployeeService employeeService;

    public ResponseEntity<?> createIp(IpCreateDto dto) {

        Optional<IpPhone> byInventoryId = ipRepository.findByInventoryId(dto.getInventoryId());

        if (byInventoryId.isPresent()) {
            throw new RuntimeException("The tool with this inventory_id already exists");
        }

        Employee owner = employeeService.getEmployee(dto.getOwner());

        IpPhone ip = new IpPhone();

        ip.setOwner(owner);
        ip.setInventoryId(dto.getInventoryId());
        ip.setModel(dto.getModel());

        IpPhone save = ipRepository.save(ip);

        byte[] zip;

        try {
            zip = ZXingUtil.generateZip(String.valueOf(save.getInventoryId()), "ip_phone");
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

    public ResponseEntity<?> getIp(long id) {

        Optional<IpPhone> byInventoryId = ipRepository.findByInventoryId(id);

        if (byInventoryId.isEmpty()) {
            throw new RuntimeException("The tool with this inventory_id does not exist");
        }

        IpPhone ip = byInventoryId.get();

        IpDto dto = new IpDto();

        dto.setId(ip.getId());
        dto.setOwner(ip.getOwner().getId());
        dto.setInventoryId(ip.getInventoryId());
        dto.setModel(ip.getModel());

        return ResponseEntity.ok(dto);
    }
}
