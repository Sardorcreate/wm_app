package sardorcreate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sardorcreate.dto.ups.UPSCreateDto;
import sardorcreate.dto.ups.UPSDto;
import sardorcreate.entity.Employee;
import sardorcreate.entity.UPS;

@Component
@RequiredArgsConstructor
public class UPSMapper {

    public UPS dtoToEntity(UPSCreateDto dto, Employee owner) {

        UPS ups = new UPS();

        ups.setOwner(owner);
        ups.setInventoryId(dto.getInventoryId());
        ups.setModel(dto.getModel());
        ups.setType(dto.getType());
        ups.setBatteryType(dto.getBatteryType());

        return ups;
    }

    public UPSDto entityToDto(UPS ups) {

        UPSDto dto = new UPSDto();

        dto.setId(ups.getId());
        dto.setOwner(ups.getOwner().getId());
        dto.setInventoryId(ups.getInventoryId());
        dto.setModel(ups.getModel());
        dto.setType(ups.getType());
        dto.setBatteryType(ups.getBatteryType());

        return dto;
    }
}
