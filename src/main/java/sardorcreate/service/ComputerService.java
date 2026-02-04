package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.GetCompProcType;
import sardorcreate.dto.computer.ComputerCreateDto;
import sardorcreate.dto.computer.ComputerDto;
import sardorcreate.dto.monitor.MonitorDto;
import sardorcreate.entity.Computer;
import sardorcreate.entity.Monitor;
import sardorcreate.enums.ProcessorVariant;
import sardorcreate.enums.ROMType;
import sardorcreate.enums.ROMVariant;
import sardorcreate.mapper.ComputerMapper;
import sardorcreate.repository.ComputerRepository;
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

        Optional<Computer> byInventoryId = computerRepository.findByInventoryId(dto.getInventoryId());

        if (byInventoryId.isPresent()) {
            throw new RuntimeException("Device with this inventory ID already exists!!!");
        }

        Monitor monitor = monitorService.createMonitor(dto.getDto());

        Computer comp = computerMapper.dtoToEntity(dto, monitor);

        Computer save = computerRepository.save(comp);

        byte[] zip;

        try {
            zip = ZXingUtil.generateZip(String.valueOf(save.getInventoryId()), "computer");
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

    public ResponseEntity<?> getComputerByInventoryId(long id) {

        Optional<Computer> byInventoryId = computerRepository.findByInventoryId(id);

        if (byInventoryId.isEmpty()) {
            throw new RuntimeException("This computer does not exist!!!");
        }

        Computer computer = byInventoryId.get();
        MonitorDto monDto = monitorService.getMonDto(computer.getMonitor());

        ComputerDto computerDto = computerMapper.entityToDto(computer, monDto);

        return ResponseEntity.ok(computerDto);
    }
}
