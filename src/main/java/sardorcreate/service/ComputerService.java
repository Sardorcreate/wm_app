package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.GetCompProcType;
import sardorcreate.enums.ProcessorVariant;
import sardorcreate.enums.ROMType;
import sardorcreate.enums.ROMVariant;
import sardorcreate.util.ComputerTypeUtils;
import sardorcreate.util.RomTypeUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComputerService {

    public ResponseEntity<?> getProcType(GetCompProcType dto) {

        List<ProcessorVariant> procs =
                ComputerTypeUtils
                        .getAllowedProcVariants(
                                dto.getCompType(),
                                dto.getProcType()
                        );

        return ResponseEntity.ok(procs);
    }

    public ResponseEntity<?> getRomType(ROMType type) {

        List<ROMVariant> roms = RomTypeUtils.getAllowedRomVariants(type);

        return ResponseEntity.ok(roms);
    }
}
