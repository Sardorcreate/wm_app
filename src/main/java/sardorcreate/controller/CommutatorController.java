package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sardorcreate.dto.GetCommPortCount;
import sardorcreate.dto.commutator.CommCreateDto;
import sardorcreate.enums.CommutatorType;
import sardorcreate.enums.PortType;
import sardorcreate.service.CommService;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/commutator")
public class CommutatorController {

    private final CommService commService;

    @GetMapping("/get/types")
    public CommutatorType[] getCommTypes() {

        return CommutatorType.values();
    }

    @GetMapping("/get/port_types/{cType}")
    public ResponseEntity<?> getPortTypes(@PathVariable CommutatorType cType) {

        return commService.getPortType(cType);
    }

    @GetMapping("/get/port_speeds/{pType}")
    public ResponseEntity<?> getPortSpeeds(@PathVariable PortType pType) {

        return commService.getPortSpeed(pType);
    }

    @PostMapping("/get/port_counts")
    public ResponseEntity<?> getPortCounts(@RequestBody GetCommPortCount dto) {

        return commService.getPortCount(dto);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCommutator(@RequestBody CommCreateDto dto) {

        return commService.createComm(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCommByInventoryId(@PathVariable long id) {

        return commService.getCommByInventoryId(id);
    }
}
