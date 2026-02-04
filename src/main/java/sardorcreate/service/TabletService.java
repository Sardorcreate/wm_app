package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.tablet.TabletCreateDto;
import sardorcreate.dto.tablet.TabletDto;
import sardorcreate.entity.Tablet;
import sardorcreate.mapper.TabletMapper;
import sardorcreate.repository.TabletRepository;
import sardorcreate.util.ZXingUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TabletService {

    private final TabletRepository tabletRepository;
    private final TabletMapper tabletMapper;

    public ResponseEntity<?> createTablet(TabletCreateDto dto) {

        Optional<Tablet> byInventoryId = tabletRepository.findByInventoryId(dto.getInventoryId());

        if (byInventoryId.isPresent()) {
            throw new RuntimeException("The tool with this inventory_id already exists");
        }

        Tablet tablet = tabletMapper.dtoToEntity(dto);

        Tablet save = tabletRepository.save(tablet);

        byte[] zip;

        try {
            zip = ZXingUtil.generateZip(String.valueOf(save.getInventoryId()), "tablet");
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

    public ResponseEntity<?> getTabletByInventoryId(long id) {

        Optional<Tablet> byInventoryId = tabletRepository.findByInventoryId(id);

        if (byInventoryId.isEmpty()) {
            throw new RuntimeException("The tool with this inventory_id does not exist");
        }

        Tablet tablet = byInventoryId.get();

        TabletDto dto = tabletMapper.entityToDto(tablet);

        return ResponseEntity.ok(dto);
    }
}
