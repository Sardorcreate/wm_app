package sardorcreate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sardorcreate.dto.tablet.TabletCreateDto;
import sardorcreate.dto.tablet.TabletDto;
import sardorcreate.entity.Tablet;
import sardorcreate.enums.ToolsStatus;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class TabletMapper {

    public Tablet dtoToEntity(TabletCreateDto dto) {

        Tablet tablet = new Tablet();

        tablet.setInventoryId(dto.getInventoryId());
        tablet.setModel(dto.getModel());
        tablet.setDate(Instant.now());
        tablet.setWhereFrom(dto.getWhereFrom());
        tablet.setPrice(dto.getPrice());
        tablet.setStatus(ToolsStatus.RESERVE);
        tablet.setOsType(dto.getOsType());
        tablet.setRam(dto.getRam());
        tablet.setRom(dto.getRom());

        return tablet;
    }

    public TabletDto entityToDto(Tablet tablet) {

        TabletDto dto = new TabletDto();

        dto.setId(tablet.getId());
        if (tablet.getStatus().equals(ToolsStatus.GIVEN)) {
            dto.setOwner(tablet.getOwner().getId());
        }
        dto.setInventoryId(tablet.getInventoryId());
        dto.setModel(tablet.getModel());
        dto.setDate(tablet.getDate());
        dto.setWhereFrom(tablet.getWhereFrom());
        dto.setPrice(tablet.getPrice());
        dto.setStatus(tablet.getStatus());
        dto.setOsType(tablet.getOsType());
        dto.setRam(tablet.getRam());
        dto.setRom(tablet.getRom());

        return dto;
    }
}
