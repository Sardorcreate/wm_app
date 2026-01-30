package sardorcreate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sardorcreate.dto.computer.ComputerCreateDto;
import sardorcreate.dto.computer.ComputerDto;
import sardorcreate.dto.monitor.MonitorDto;
import sardorcreate.entity.Computer;
import sardorcreate.entity.Employee;
import sardorcreate.entity.Monitor;

@Component
@RequiredArgsConstructor
public class ComputerMapper {

    public Computer dtoToEntity(ComputerCreateDto dto, Monitor monitor, Employee owner) {

        Computer comp = new Computer();

        comp.setOwner(owner);
        comp.setInventoryId(dto.getInventoryId());
        comp.setModel(dto.getModel());
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
        dto.setOwner(save.getOwner().getFullName());
        dto.setInventoryId(save.getInventoryId());
        dto.setModel(save.getModel());
        dto.setType(save.getType());
        dto.setProcType(save.getProcessorType());
        dto.setProcVariant(save.getProcessorVariant());
        dto.setRamType(save.getRamType());
        dto.setRomType(save.getRomType());
        dto.setRomVariant(save.getRomVariant());
        dto.setDto(monDto);

        return dto;
    }
}
