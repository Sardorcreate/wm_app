package sardorcreate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sardorcreate.dto.sep_monitor.SepMonitorCreateDto;
import sardorcreate.dto.sep_monitor.SepMonitorDto;
import sardorcreate.entity.Inventory;
import sardorcreate.entity.SepMonitor;
import sardorcreate.enums.ToolsStatus;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class SepMonMapper {

    public SepMonitor dtoToEntity(SepMonitorCreateDto dto, Inventory inventory) {

        SepMonitor sepMon = new SepMonitor();

        sepMon.setInventoryId(inventory);
        sepMon.setModel(dto.getModel());
        sepMon.setDate(Instant.now());
        sepMon.setWhereFrom(dto.getWhereFrom());
        sepMon.setPrice(dto.getPrice());
        sepMon.setStatus(ToolsStatus.RESERVE);
        sepMon.setSize(dto.getSize());
        sepMon.setRefreshRate(dto.getRefreshRate());
        sepMon.setType(dto.getMonType());

        return sepMon;
    }


    public SepMonitorDto entityToDto(SepMonitor sepMon) {

        SepMonitorDto dto = new SepMonitorDto();

        dto.setId(sepMon.getId());
        if (sepMon.getStatus().equals(ToolsStatus.GIVEN)) {
            dto.setOwner(sepMon.getOwner().getId());
        }
        dto.setInventoryId(sepMon.getInventoryId().getInventoryId());
        dto.setModel(sepMon.getModel());
        dto.setDate(sepMon.getDate());
        dto.setWhereFrom(sepMon.getWhereFrom());
        dto.setPrice(sepMon.getPrice());
        dto.setStatus(sepMon.getStatus());
        dto.setSize(sepMon.getSize());
        dto.setRefreshRate(sepMon.getRefreshRate());
        dto.setType(sepMon.getType());

        return dto;
    }
}
