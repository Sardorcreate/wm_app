package sardorcreate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sardorcreate.dto.sep_monitor.SepMonitorCreateDto;
import sardorcreate.dto.sep_monitor.SepMonitorDto;
import sardorcreate.entity.Employee;
import sardorcreate.entity.SepMonitor;

@Component
@RequiredArgsConstructor
public class SepMonMapper {

    public SepMonitor dtoToEntity(SepMonitorCreateDto dto, Employee owner) {

        SepMonitor sepMon = new SepMonitor();

        sepMon.setOwner(owner);
        sepMon.setInventoryId(dto.getInventoryId());
        sepMon.setModel(dto.getModel());
        sepMon.setSize(dto.getSize());
        sepMon.setRefreshRate(dto.getRefreshRate());
        sepMon.setType(dto.getType());

        return sepMon;
    }


    public SepMonitorDto entityToDto(SepMonitor sepMon) {

        SepMonitorDto dto = new SepMonitorDto();

        dto.setId(sepMon.getId());
        dto.setOwner(sepMon.getOwner().getId());
        dto.setInventoryId(sepMon.getInventoryId());
        dto.setModel(sepMon.getModel());
        dto.setSize(sepMon.getSize());
        dto.setRefreshRate(sepMon.getRefreshRate());
        dto.setType(sepMon.getType());

        return dto;
    }
}
