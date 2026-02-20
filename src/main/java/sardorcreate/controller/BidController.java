package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sardorcreate.dto.bid.BidCreateDto;
import sardorcreate.service.BidService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bid")
public class BidController {

    private final BidService bidService;

    @PostMapping("/create")
    public ResponseEntity<?> createBid(@RequestBody BidCreateDto dto) {

        return bidService.createBid(dto);
    }
}
