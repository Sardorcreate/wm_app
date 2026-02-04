package sardorcreate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sardorcreate.dto.ups.UPSCreateDto;
import sardorcreate.dto.ups.UPSDto;
import sardorcreate.entity.UPS;
import sardorcreate.enums.ToolsStatus;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class UPSMapper {

    public UPS dtoToEntity(UPSCreateDto dto) {

        UPS ups = new UPS();

        ups.setInventoryId(dto.getInventoryId());
        ups.setModel(dto.getModel());
        ups.setDate(Instant.now());
        ups.setWhereFrom(dto.getWhereFrom());
        ups.setPrice(dto.getPrice());
        ups.setStatus(ToolsStatus.RESERVE);
        ups.setType(dto.getType());
        ups.setBatteryType(dto.getBatteryType());

        return ups;
    }

    public UPSDto entityToDto(UPS ups) {

        UPSDto dto = new UPSDto();

        dto.setId(ups.getId());
        if (!ups.getStatus().equals(ToolsStatus.RESERVE)) {
            dto.setOwner(ups.getOwner().getId());
        }
        dto.setInventoryId(ups.getInventoryId());
        dto.setModel(ups.getModel());
        dto.setDate(ups.getDate());
        dto.setWhereFrom(ups.getWhereFrom());
        dto.setPrice(ups.getPrice());
        dto.setStatus(ups.getStatus());
        dto.setType(ups.getType());
        dto.setBatteryType(ups.getBatteryType());

        return dto;
    }
}
