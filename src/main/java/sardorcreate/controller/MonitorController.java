package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sardorcreate.dto.monitor.MonitorCreateDto;
import sardorcreate.service.MonitorService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/monitor")
public class MonitorController {

    private final MonitorService monitorService;

    @PostMapping("/create")
    public ResponseEntity<?> createMonitor(@RequestBody MonitorCreateDto dto) {

        return monitorService.createMonitor(dto);
    }
}
