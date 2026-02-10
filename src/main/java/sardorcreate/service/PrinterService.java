package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.printer.PrinterCreateDto;
import sardorcreate.dto.printer.PrinterDto;
import sardorcreate.entity.Inventory;
import sardorcreate.entity.Printer;
import sardorcreate.exception.AlreadyExistsException;
import sardorcreate.exception.NotExistsException;
import sardorcreate.mapper.PrinterMapper;
import sardorcreate.repository.InventoryRepository;
import sardorcreate.repository.PrinterRepository;
import sardorcreate.util.ZXingUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrinterService {

    private final PrinterRepository printerRepository;
    private final PrinterMapper printerMapper;
    private final InventoryRepository inventoryRepository;

    public ResponseEntity<?> createPrinter(PrinterCreateDto dto) {

        Inventory inventory = new Inventory();

        inventory.setInventoryId(dto.getInventoryId());

        try {
            inventoryRepository.save(inventory);
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException("The tool with this inventory_id already exists");
        }

        Printer printer = printerMapper.dtoToEntity(dto, inventory);

        Printer save = printerRepository.save(printer);

        byte[] pdf;

        try {
            pdf = ZXingUtil.generatePdf(String.valueOf(save.getInventoryId().getInventoryId()), "printer");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + save.getInventoryId().getInventoryId() + "_codes.pdf\"")
                .contentType(MediaType.parseMediaType("application/pdf"))
                .contentLength(pdf.length)
                .body(pdf);
    }

    public ResponseEntity<?> getPrinterByInventoryId(long id) {

        Optional<Printer> byInventoryId = printerRepository.findByInventoryId_InventoryId(id);

        if (byInventoryId.isEmpty()) {
            throw new NotExistsException("The tool with this inventory_id does not exist");
        }

        Printer printer = byInventoryId.get();

        PrinterDto dto = printerMapper.entityToDto(printer);

        return ResponseEntity.ok(dto);
    }
}
