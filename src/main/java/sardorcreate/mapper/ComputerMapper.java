package sardorcreate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sardorcreate.dto.computer.ComputerCreateDto;
import sardorcreate.dto.computer.ComputerDto;
import sardorcreate.dto.monitor.MonitorDto;
import sardorcreate.entity.Computer;
import sardorcreate.entity.Monitor;
import sardorcreate.enums.ToolsStatus;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class ComputerMapper {

    public Computer dtoToEntity(ComputerCreateDto dto, Monitor monitor) {

        Computer comp = new Computer();

        comp.setInventoryId(dto.getInventoryId());
        comp.setModel(dto.getModel());
        comp.setDate(Instant.now());
        comp.setWhereFrom(dto.getWhereFrom());
        comp.setPrice(dto.getPrice());
        comp.setStatus(ToolsStatus.RESERVE);
        comp.setType(dto.getType());
        comp.setProcessorType(dto.getProcType());
        comp.setProcessorVariant(dto.getProcVariant());
        comp.setRamType(dto.getRamType());
        comp.setRomType(dto.getRomType());
        comp.setRomVariant(dto.getRomVariant());
        comp.setMonitor(monitor);

        return comp;
    }

    public ComputerDto entityToDto(Computer save, MonitorDto monDto) {

        ComputerDto dto = new ComputerDto();

        dto.setId(save.getId());
        if (save.getStatus().equals(ToolsStatus.GIVEN)) {
            dto.setOwner(save.getOwner().getFullName());
        }
        dto.setInventoryId(save.getInventoryId());
        dto.setModel(save.getModel());
        dto.setDate(save.getDate());
        dto.setWhereFrom(save.getWhereFrom());
        dto.setPrice(save.getPrice());
        dto.setStatus(save.getStatus());
        dto.setType(save.getType());
        dto.setProcType(save.getProcessorType());
        dto.setProcVariant(save.getProcessorVariant());
        dto.setRamType(save.getRamType());
        dto.setRomType(save.getRomType());
        dto.setRomVariant(save.getRomVariant());
        dto.setMonitorDto(monDto);

        return dto;
    }
}
