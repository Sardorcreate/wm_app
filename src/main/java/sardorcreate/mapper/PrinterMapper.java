package sardorcreate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sardorcreate.dto.printer.PrinterCreateDto;
import sardorcreate.dto.printer.PrinterDto;
import sardorcreate.entity.Employee;
import sardorcreate.entity.Printer;

@Component
@RequiredArgsConstructor
public class PrinterMapper {

    public Printer dtoToEntity(PrinterCreateDto dto, Employee owner) {

        Printer printer = new Printer();

        printer.setOwner(owner);
        printer.setInventoryId(dto.getInventoryId());
        printer.setModel(dto.getModel());
        printer.setType(dto.getType());
        printer.setColored(dto.isColored());
        printer.setColorCount(dto.getColorCount());

        return printer;
    }

    public PrinterDto entityToDto(Printer printer) {

        PrinterDto dto = new PrinterDto();

        dto.setId(printer.getId());
        dto.setOwner(printer.getOwner().getId());
        dto.setInventoryId(printer.getInventoryId());
        dto.setModel(printer.getModel());
        dto.setType(printer.getType());
        dto.setColored(printer.isColored());
        dto.setColorCount(printer.getColorCount());

        return dto;
    }
}
