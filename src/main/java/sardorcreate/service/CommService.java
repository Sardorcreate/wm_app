package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.GetCommPortCount;
import sardorcreate.dto.commutator.CommCreateDto;
import sardorcreate.dto.commutator.CommDto;
import sardorcreate.entity.Commutator;
import sardorcreate.entity.Inventory;
import sardorcreate.enums.CommutatorType;
import sardorcreate.enums.PortCount;
import sardorcreate.enums.PortSpeed;
import sardorcreate.enums.PortType;
import sardorcreate.exception.AlreadyExistsException;
import sardorcreate.exception.NotExistsException;
import sardorcreate.mapper.CommMapper;
import sardorcreate.repository.CommRepository;
import sardorcreate.repository.InventoryRepository;
import sardorcreate.util.PortTypeAndCountUtils;
import sardorcreate.util.PortTypeSpeedUtils;
import sardorcreate.util.PortTypesUtils;
import sardorcreate.util.ZXingUtil;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommService {

    private final InventoryRepository inventoryRepository;
    private final CommMapper commMapper;
    private final CommRepository commRepository;

    public ResponseEntity<?> getPortType(CommutatorType cType) {

        List<PortType> allowedPortTypes = PortTypesUtils.getAllowedPortTypes(cType);

        return ResponseEntity.ok(allowedPortTypes);
    }

    public ResponseEntity<?> getPortSpeed(PortType pType) {

        List<PortSpeed> allowedPortSpeeds = PortTypeSpeedUtils.getAllowedPortSpeeds(pType);

        return ResponseEntity.ok(allowedPortSpeeds);
    }

    public ResponseEntity<?> getPortCount(GetCommPortCount dto) {

        List<PortCount> allowedPortCounts =
                PortTypeAndCountUtils
                        .getAllowedPortCounts(
                                dto.getCType(),
                                dto.getPType()
                        );

        return ResponseEntity.ok(allowedPortCounts);
    }

    public ResponseEntity<?> createComm(CommCreateDto dto) {

        Inventory inventory = new Inventory();

        inventory.setInventoryId(dto.getInventoryId());

        try {
            inventoryRepository.save(inventory);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException("The tool with this inventory_id already exists");
        }

        Commutator comm = commMapper.dtoToEntity(dto, inventory);

        Commutator save = commRepository.save(comm);

        byte[] pdf;

        try {
            pdf = ZXingUtil.generatePdf(String.valueOf(save.getInventoryId().getInventoryId()), "commutator");
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

    public ResponseEntity<?> getCommByInventoryId(long id) {

        Optional<Commutator> byInventoryIdInventoryId = commRepository.findByInventoryId_InventoryId(id);

        if (byInventoryIdInventoryId.isEmpty()) {
            throw new NotExistsException("The tool with this inventory_id does not exist");
        }

        Commutator comm = byInventoryIdInventoryId.get();
        CommDto commDto = commMapper.entityToDto(comm);

        return ResponseEntity.ok(commDto);
    }
}
