package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sardorcreate.dto.monitor.MonitorCreateDto;
import sardorcreate.dto.monitor.MonitorDto;
import sardorcreate.entity.Monitor;
import sardorcreate.repository.MonitorRepository;

@Service
@RequiredArgsConstructor
public class MonitorService {

    private final MonitorRepository monitorRepository;

    public Monitor createMonitor(MonitorCreateDto dto) {

        Monitor monitor = new Monitor();
        monitor.setSize(dto.getSize());
        monitor.setRefreshRate(dto.getRefreshRate());
        monitor.setMonType(dto.getMonType());

        return monitorRepository.save(monitor);
    }

    public MonitorDto getMonDto(Monitor monitor) {

        MonitorDto dto = new MonitorDto();

        dto.setId(monitor.getId());
        dto.setSize(monitor.getSize());
        dto.setRefreshRate(monitor.getRefreshRate());
        dto.setType(monitor.getMonType());

        return dto;
    }
}
