package sardorcreate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sardorcreate.dto.monitor.MonitorCreateDto;
import sardorcreate.dto.monitor.MonitorDto;
import sardorcreate.entity.Monitor;

@Component
@RequiredArgsConstructor
public class MonitorMapper {

    public Monitor dtoToEntity(MonitorCreateDto dto) {

        Monitor monitor = new Monitor();

        monitor.setModel(dto.getModel());
        monitor.setSize(dto.getSize());
        monitor.setRefreshRate(dto.getRefreshRate());
        monitor.setType(dto.getType());

        return monitor;
    }

    public MonitorDto entityToDto(Monitor save) {

        MonitorDto dto = new MonitorDto();

        dto.setId(save.getId());
        dto.setModel(save.getModel());
        dto.setSize(save.getSize());
        dto.setRefreshRate(save.getRefreshRate());
        dto.setType(save.getType());

        return dto;
    }
}
