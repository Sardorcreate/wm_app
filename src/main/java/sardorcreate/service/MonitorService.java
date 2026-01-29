package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.monitor.MonitorCreateDto;
import sardorcreate.dto.monitor.MonitorDto;
import sardorcreate.entity.Monitor;
import sardorcreate.mapper.MonitorMapper;
import sardorcreate.repository.MonitorRepository;

@Service
@RequiredArgsConstructor
public class MonitorService {

    private final MonitorMapper monitorMapper;
    private final MonitorRepository monitorRepository;

    public ResponseEntity<?> createMonitor(MonitorCreateDto dto) {

        Monitor monitor = monitorMapper.dtoToEntity(dto);

        Monitor save = monitorRepository.save(monitor);

        MonitorDto newDto = monitorMapper.entityToDto(save);

        return ResponseEntity.ok(newDto);
    }
}
