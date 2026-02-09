package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.GetCompProcType;
import sardorcreate.dto.computer.ComputerCreateDto;
import sardorcreate.dto.computer.ComputerDto;
import sardorcreate.dto.monitor.MonitorDto;
import sardorcreate.entity.Computer;
import sardorcreate.entity.Inventory;
import sardorcreate.entity.Monitor;
import sardorcreate.enums.ProcessorVariant;
import sardorcreate.enums.ROMType;
import sardorcreate.enums.ROMVariant;
import sardorcreate.exception.AlreadyExistsException;
import sardorcreate.mapper.ComputerMapper;
import sardorcreate.repository.ComputerRepository;
import sardorcreate.repository.InventoryRepository;
import sardorcreate.util.ComputerTypeUtils;
import sardorcreate.util.RomTypeUtils;
import sardorcreate.util.ZXingUtil;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComputerService {

    private final ComputerRepository computerRepository;
    private final ComputerMapper computerMapper;
    private final MonitorService monitorService;
    private final InventoryRepository inventoryRepository;

    public ResponseEntity<?> getProcType(GetCompProcType dto) {

        List<ProcessorVariant> procs =
                ComputerTypeUtils
                        .getAllowedProcVariants(
                                dto.getCompType(),
                                dto.getProcType()
                        );

        return ResponseEntity.ok(procs);
    }

    public ResponseEntity<?> getRomType(ROMType type) {

        List<ROMVariant> roms = RomTypeUtils.getAllowedRomVariants(type);

        return ResponseEntity.ok(roms);
    }

    public ResponseEntity<?> createComputer(ComputerCreateDto dto) {

        Inventory inventory = new Inventory();

        inventory.setInventoryId(dto.getInventoryId());

        try {
            inventoryRepository.save(inventory);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException("The tool with this inventory_id already exists");
        }

        Monitor monitor = monitorService.createMonitor(dto.getDto());

        Computer comp = computerMapper.dtoToEntity(dto, monitor, inventory);

        Computer save = computerRepository.save(comp);

        byte[] pdf;

        try {
            pdf = ZXingUtil.generatePdf(String.valueOf(save.getInventoryId().getInventoryId()), "computer");
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

    public ResponseEntity<?> getComputerByInventoryId(long id) {

        Optional<Computer> byInventoryId = computerRepository.findByInventoryId_InventoryId(id);

        if (byInventoryId.isEmpty()) {
            throw new RuntimeException("This computer does not exist!!!");
        }

        Computer computer = byInventoryId.get();
        MonitorDto monDto = monitorService.getMonDto(computer.getMonitor());

        ComputerDto computerDto = computerMapper.entityToDto(computer, monDto);

        return ResponseEntity.ok(computerDto);
    }
}
