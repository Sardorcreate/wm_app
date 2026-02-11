package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sardorcreate.dto.computer.ComputerCreateDto;
import sardorcreate.dto.GetCompProcType;
import sardorcreate.enums.ROMType;
import sardorcreate.service.ComputerService;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/computer")
public class ComputerController {

    private final ComputerService computerService;

    @PostMapping("/get/proc_variants")
    public ResponseEntity<?> getProcType(@RequestBody GetCompProcType dto) {

        return computerService.getProcType(dto);
    }

    @GetMapping("/get/rom_variants/{type}")
    public ResponseEntity<?> getRomType(@PathVariable ROMType type) {

        return computerService.getRomType(type);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createComputer(@RequestBody ComputerCreateDto dto) {

        return computerService.createComputer(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getComp(@PathVariable long id) {

        return computerService.getComputerByInventoryId(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCompByInventoryId(@PathVariable long id) {

        return computerService.deleteCompByInventoryId(id);
    }
}
