package sardorcreate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sardorcreate.dto.tablet.TabletCreateDto;
import sardorcreate.dto.tablet.TabletDto;
import sardorcreate.entity.Employee;
import sardorcreate.entity.Tablet;

@Component
@RequiredArgsConstructor
public class TabletMapper {

    public Tablet dtoToEntity(TabletCreateDto dto, Employee owner) {

        Tablet tablet = new Tablet();

        tablet.setOwner(owner);
        tablet.setInventoryId(dto.getInventoryId());
        tablet.setModel(dto.getModel());
        tablet.setOsType(dto.getOsType());
        tablet.setRam(dto.getRam());
        tablet.setRom(dto.getRom());

        return tablet;
    }

    public TabletDto entityToDto(Tablet tablet) {

        TabletDto dto = new TabletDto();

        dto.setId(tablet.getId());
        dto.setOwner(tablet.getOwner().getId());
        dto.setInventoryId(tablet.getInventoryId());
        dto.setModel(tablet.getModel());
        dto.setOsType(tablet.getOsType());
        dto.setRam(tablet.getRam());
        dto.setRom(tablet.getRom());

        return dto;
    }
}
