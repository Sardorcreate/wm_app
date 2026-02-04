package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.printer.PrinterCreateDto;
import sardorcreate.dto.printer.PrinterDto;
import sardorcreate.entity.Printer;
import sardorcreate.mapper.PrinterMapper;
import sardorcreate.repository.PrinterRepository;
import sardorcreate.util.ZXingUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrinterService {

    private final PrinterRepository printerRepository;
    private final PrinterMapper printerMapper;

    public ResponseEntity<?> createPrinter(PrinterCreateDto dto) {

        Optional<Printer> byInventoryId = printerRepository.findByInventoryId(dto.getInventoryId());

        if (byInventoryId.isPresent()) {
            throw new RuntimeException("The tool with this inventory_id already exists");
        }

        Printer printer = printerMapper.dtoToEntity(dto);

        Printer save = printerRepository.save(printer);

        byte[] zip;

        try {
            zip = ZXingUtil.generateZip(String.valueOf(save.getInventoryId()), "printer");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + save.getInventoryId() + "_codes.zip\"")
                .contentType(MediaType.parseMediaType("application/zip"))
                .contentLength(zip.length)
                .body(zip);
    }

    public ResponseEntity<?> getPrinterByInventoryId(long id) {

        Optional<Printer> byInventoryId = printerRepository.findByInventoryId(id);

        if (byInventoryId.isEmpty()) {
            throw new RuntimeException("The tool with this inventory_id does not exist");
        }

        Printer printer = byInventoryId.get();

        PrinterDto dto = printerMapper.entityToDto(printer);

        return ResponseEntity.ok(dto);
    }
}
