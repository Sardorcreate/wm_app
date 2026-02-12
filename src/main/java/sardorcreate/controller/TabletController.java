package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sardorcreate.dto.FilterDto;
import sardorcreate.dto.tablet.TabletCreateDto;
import sardorcreate.service.TabletService;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tablet")
public class TabletController {

    private final TabletService tabletService;

    @PostMapping("/create")
    public ResponseEntity<?> createTablet(@RequestBody TabletCreateDto dto) {

        return tabletService.createTablet(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTabletByInventoryId(@PathVariable long id) {

        return tabletService.getTabletByInventoryId(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTabletByInventoryId(@PathVariable long id) {

        return tabletService.deleteTabletByInventoryId(id);
    }

    @PostMapping()
    public ResponseEntity<?> getTabletByFilter(@RequestBody FilterDto dto) {

        return tabletService.getTabletByFilter(dto);
    }

    @GetMapping("/get_by_id/{id}")
    public ResponseEntity<?> getTabletById(@PathVariable long id) {

        return tabletService.getTabletById(id);
    }
}
