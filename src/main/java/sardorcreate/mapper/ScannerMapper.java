package sardorcreate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sardorcreate.dto.scanner.ScannerCreateDto;
import sardorcreate.dto.scanner.ScannerDto;
import sardorcreate.entity.Employee;
import sardorcreate.entity.Scanner;

@Component
@RequiredArgsConstructor
public class ScannerMapper {

    public Scanner dtoToEntity(ScannerCreateDto dto, Employee owner) {
        
        Scanner scan = new Scanner();
        
        scan.setOwner(owner);
        scan.setInventoryId(dto.getInventoryId());
        scan.setType(dto.getType());
        scan.setModel(dto.getModel());
        scan.setDpi(dto.getDpi());
        
        return scan;
    }

    public ScannerDto entityToDto(Scanner scan) {

        ScannerDto dto = new ScannerDto();
        
        dto.setId(scan.getId());
        dto.setOwner(scan.getOwner().getId());
        dto.setInventoryId(scan.getInventoryId());
        dto.setModel(scan.getModel());
        dto.setType(scan.getType());
        dto.setDpi(scan.getDpi());
        
        return dto;
    }
}
