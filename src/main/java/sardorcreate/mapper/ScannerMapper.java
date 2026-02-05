package sardorcreate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sardorcreate.dto.scanner.ScannerCreateDto;
import sardorcreate.dto.scanner.ScannerDto;
import sardorcreate.entity.Scanner;
import sardorcreate.enums.ToolsStatus;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class ScannerMapper {

    public Scanner dtoToEntity(ScannerCreateDto dto) {
        
        Scanner scan = new Scanner();
        
        scan.setInventoryId(dto.getInventoryId());
        scan.setType(dto.getType());
        scan.setModel(dto.getModel());
        scan.setDate(Instant.now());
        scan.setWhereFrom(dto.getWhereFrom());
        scan.setPrice(dto.getPrice());
        scan.setStatus(ToolsStatus.RESERVE);
        scan.setDpi(dto.getDpi());
        
        return scan;
    }

    public ScannerDto entityToDto(Scanner scan) {

        ScannerDto dto = new ScannerDto();
        
        dto.setId(scan.getId());

        if (scan.getStatus().equals(ToolsStatus.GIVEN)) {
            dto.setOwner(scan.getOwner().getId());
        }
        dto.setInventoryId(scan.getInventoryId());
        dto.setModel(scan.getModel());
        dto.setDate(scan.getDate());
        dto.setWhereFrom(scan.getWhereFrom());
        dto.setPrice(scan.getPrice());
        dto.setStatus(scan.getStatus());
        dto.setType(scan.getType());
        dto.setDpi(scan.getDpi());
        
        return dto;
    }
}
