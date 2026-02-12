package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sardorcreate.dto.FilterDto;
import sardorcreate.dto.printer.PrinterCreateDto;
import sardorcreate.service.PrinterService;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/printer")
public class PrinterController {

    private final PrinterService printerService;

    @PostMapping("/create")
    public ResponseEntity<?> createPrinter(@RequestBody PrinterCreateDto dto) {

        return printerService.createPrinter(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPrinterByInventoryId(@PathVariable long id) {

        return printerService.getPrinterByInventoryId(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePrinterByInventoryId(@PathVariable long id) {

        return printerService.deletePrinterByInventoryId(id);
    }

    @PostMapping()
    public ResponseEntity<?> getPrinterByFilter(@RequestBody FilterDto dto) {

        return printerService.getByFilter(dto);
    }

    @GetMapping("/get_by_id/{id}")
    public ResponseEntity<?> getPrintById(@PathVariable long id) {

        return printerService.getPrintById(id);
    }
}
