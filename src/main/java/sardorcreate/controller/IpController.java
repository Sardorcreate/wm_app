package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sardorcreate.dto.ip.IpCreateDto;
import sardorcreate.service.IpService;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ip")
public class IpController {

    private final IpService ipService;

    @PostMapping("/create")
    public ResponseEntity<?> createIp(@RequestBody IpCreateDto dto) {

        return ipService.createIp(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getIp(@PathVariable long id) {

        return ipService.getIp(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteIpByInventoryId(@PathVariable long id) {

        return ipService.deleteIpByInventoryId(id);
    }
}
