package sardorcreate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sardorcreate.dto.ip.IpCreateDto;
import sardorcreate.dto.ip.IpDto;
import sardorcreate.entity.Inventory;
import sardorcreate.entity.IpPhone;
import sardorcreate.enums.ToolsStatus;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class IpMapper {

    public IpPhone dtoToEntity(IpCreateDto dto, Inventory inventory) {

        IpPhone ip = new IpPhone();

        ip.setInventoryId(inventory);
        ip.setModel(dto.getModel());
        ip.setDate(LocalDate.now());
        ip.setWhereFrom(dto.getWhereFrom());
        ip.setPrice(dto.getPrice());
        ip.setStatus(ToolsStatus.RESERVE);

        return ip;
    }

    public IpDto entityToDto(IpPhone ip) {

        IpDto dto = new IpDto();

        dto.setId(ip.getId());

        if (ip.getStatus().equals(ToolsStatus.GIVEN)) {
            dto.setOwner(ip.getOwner().getId());
        }
        dto.setInventoryId(ip.getInventoryId().getInventoryId());
        dto.setModel(ip.getModel());
        dto.setDate(ip.getDate());
        dto.setWhereFrom(ip.getWhereFrom());
        dto.setPrice(ip.getPrice());
        dto.setStatus(ip.getStatus());

        return dto;
    }
}
