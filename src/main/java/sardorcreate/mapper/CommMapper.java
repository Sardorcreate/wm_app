package sardorcreate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sardorcreate.dto.commutator.CommCreateDto;
import sardorcreate.dto.commutator.CommDto;
import sardorcreate.entity.Commutator;
import sardorcreate.entity.Inventory;
import sardorcreate.enums.ToolsStatus;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class CommMapper {

    public Commutator dtoToEntity(CommCreateDto dto, Inventory inventory) {
        
        Commutator comm = new Commutator();
        
        comm.setInventoryId(inventory);
        comm.setModel(dto.getModel());
        comm.setDate(LocalDate.now());
        comm.setWhereFrom(dto.getWhereFrom());
        comm.setPrice(dto.getPrice());
        comm.setStatus(ToolsStatus.RESERVE);
        comm.setType(dto.getType());
        comm.setPortType(dto.getPortType());
        comm.setPortCount(dto.getPortCount());
        comm.setPortSpeed(dto.getPortSpeed());
        
        return comm;
    }

    public CommDto entityToDto(Commutator comm) {

        CommDto dto = new CommDto();

        dto.setId(comm.getId());
        if (comm.getStatus().equals(ToolsStatus.GIVEN)) {
            dto.setOwner(comm.getOwner().getFullName());
        }
        dto.setInventoryId(comm.getInventoryId().getInventoryId());
        dto.setModel(comm.getModel());
        dto.setDate(comm.getDate());
        dto.setWhereFrom(comm.getWhereFrom());
        dto.setPrice(comm.getPrice());
        dto.setStatus(comm.getStatus());
        dto.setType(comm.getType());
        dto.setPortType(comm.getPortType());
        dto.setPortCount(comm.getPortCount());
        dto.setPortSpeed(comm.getPortSpeed());

        return dto;
    }
}
