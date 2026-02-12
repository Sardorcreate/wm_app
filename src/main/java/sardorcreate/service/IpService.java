package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.FilterDto;
import sardorcreate.dto.ip.IpCreateDto;
import sardorcreate.dto.ip.IpDto;
import sardorcreate.entity.Inventory;
import sardorcreate.entity.IpPhone;
import sardorcreate.exception.AlreadyExistsException;
import sardorcreate.exception.NotExistsException;
import sardorcreate.mapper.IpMapper;
import sardorcreate.repository.InventoryRepository;
import sardorcreate.repository.IpRepository;
import sardorcreate.util.GenericSpecification;
import sardorcreate.util.ZXingUtil;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IpService {

    private final IpRepository ipRepository;
    private final InventoryRepository inventoryRepository;
    private final IpMapper ipMapper;

    public ResponseEntity<?> createIp(IpCreateDto dto) {

        Inventory inventory = new Inventory();

        inventory.setInventoryId(dto.getInventoryId());

        try {
            inventoryRepository.save(inventory);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException("The tool with this inventory_id already exists");
        }

        IpPhone ip = ipMapper.dtoToEntity(dto, inventory);

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

        IpPhone ip = ipRepository
                .findByInventoryId_InventoryIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                        new NotExistsException("The tool with this inventory_id does not exist")
                        );

        IpDto dto = ipMapper.entityToDto(ip);

        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> deleteIpByInventoryId(long id) {

        IpPhone ip = ipRepository
                .findByInventoryId_InventoryIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                        new NotExistsException("The tool with this inventory_id does not exist")
                );

        ip.setDeleted(true);
        ipRepository.save(ip);

        return ResponseEntity.ok("Successfully deleted");
    }

    public ResponseEntity<?> getIpByFilter(FilterDto dto) {

        List<IpPhone> all = ipRepository.findAll(GenericSpecification.filter(
                dto.getInventoryId(),
                dto.getDate(),
                dto.getStatus()
        ));

        List<IpDto> ipDtos = new ArrayList<>();

        for (IpPhone ip : all) {
            IpDto ipDto = ipMapper.entityToDto(ip);

            ipDtos.add(ipDto);
        }

        return ResponseEntity.ok(ipDtos);
    }

    public ResponseEntity<?> getIpById(long id) {

        IpPhone ip = ipRepository
                .findById(id)
                .orElseThrow(() ->
                            new NotExistsException("The tool with this id does not exist")
                        );

        IpDto dto = ipMapper.entityToDto(ip);

        return ResponseEntity.ok(dto);
    }
}
