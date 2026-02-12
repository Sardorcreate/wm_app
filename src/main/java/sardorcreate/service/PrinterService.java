package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.FilterDto;
import sardorcreate.dto.printer.PrinterCreateDto;
import sardorcreate.dto.printer.PrinterDto;
import sardorcreate.entity.Inventory;
import sardorcreate.entity.Printer;
import sardorcreate.exception.AlreadyExistsException;
import sardorcreate.exception.NotExistsException;
import sardorcreate.mapper.PrinterMapper;
import sardorcreate.repository.InventoryRepository;
import sardorcreate.repository.PrinterRepository;
import sardorcreate.util.GenericSpecification;
import sardorcreate.util.ZXingUtil;

import java.util.ArrayList;
import java.util.List;

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

        Printer print = printerRepository
                .findByInventoryId_InventoryIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                        new NotExistsException("The tool with this inventory_id does not exist")
                );

        PrinterDto dto = printerMapper.entityToDto(print);

        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> deletePrinterByInventoryId(long id) {

        Printer print = printerRepository
                .findByInventoryId_InventoryIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                            new NotExistsException("The tool with this inventory_id does not exist")
                        );

        print.setDeleted(true);
        printerRepository.save(print);

        return ResponseEntity.ok("Successfully deleted");
    }

    public ResponseEntity<?> getByFilter(FilterDto dto) {

        List<Printer> all = printerRepository.findAll(GenericSpecification.filter(
                dto.getInventoryId(),
                dto.getDate(),
                dto.getStatus()
        ));

        List<PrinterDto> printDtos = new ArrayList<>();

        for (Printer print : all) {
            PrinterDto printDto = printerMapper.entityToDto(print);

            printDtos.add(printDto);
        }

        return ResponseEntity.ok(printDtos);
    }

    public ResponseEntity<?> getPrintById(long id) {

        Printer print = printerRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotExistsException("The tool with this id does not exist")
                );

        PrinterDto dto = printerMapper.entityToDto(print);

        return ResponseEntity.ok(dto);
    }
}
