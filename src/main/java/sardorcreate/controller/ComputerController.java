package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sardorcreate.dto.computer.ComputerCreateDto;
import sardorcreate.dto.GetCompProcType;
import sardorcreate.enums.ROMType;
import sardorcreate.service.ComputerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/computer")
public class ComputerController {

    private final ComputerService computerService;

    @GetMapping("/get_proc_types")
    public ResponseEntity<?> getProcType(@RequestBody GetCompProcType dto) {

        return computerService.getProcType(dto);
    }

    @GetMapping("get_rom_types/{type}")
    public ResponseEntity<?> getRomType(@PathVariable ROMType type) {

        return computerService.getRomType(type);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createComputer(@RequestBody ComputerCreateDto dto) {

        return computerService.createComputer(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getComp(@PathVariable String id) {

        return computerService.getComputerByInventoryId(id);
    }
}
