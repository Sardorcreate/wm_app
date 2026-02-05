package sardorcreate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sardorcreate.dto.printer.PrinterCreateDto;
import sardorcreate.dto.printer.PrinterDto;
import sardorcreate.entity.Printer;
import sardorcreate.enums.ToolsStatus;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class PrinterMapper {

    public Printer dtoToEntity(PrinterCreateDto dto) {

        Printer printer = new Printer();

        printer.setInventoryId(dto.getInventoryId());
        printer.setModel(dto.getModel());
        printer.setDate(Instant.now());
        printer.setWhereFrom(dto.getWhereFrom());
        printer.setPrice(dto.getPrice());
        printer.setStatus(ToolsStatus.RESERVE);
        printer.setType(dto.getType());
        printer.setColored(dto.isColored());
        printer.setColorCount(dto.getColorCount());

        return printer;
    }

    public PrinterDto entityToDto(Printer printer) {

        PrinterDto dto = new PrinterDto();

        dto.setId(printer.getId());

        if (printer.getStatus().equals(ToolsStatus.GIVEN)) {
            dto.setOwner(printer.getOwner().getId());
        }
        dto.setInventoryId(printer.getInventoryId());
        dto.setModel(printer.getModel());
        dto.setDate(printer.getDate());
        dto.setWhereFrom(printer.getWhereFrom());
        dto.setPrice(printer.getPrice());
        dto.setStatus(printer.getStatus());
        dto.setType(printer.getType());
        dto.setColored(printer.isColored());
        dto.setColorCount(printer.getColorCount());

        return dto;
    }
}
