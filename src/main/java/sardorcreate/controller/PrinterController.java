package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
}
