package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.bid.BidCreateDto;
import sardorcreate.entity.Bid;
import sardorcreate.entity.User;
import sardorcreate.mapper.BidMapper;
import sardorcreate.repository.BidRepository;

@Service
@RequiredArgsConstructor
public class BidService {

    private final BidMapper bidMapper;
    private final UserService userService;
    private final BidRepository bidRepository;
    private final NotificationService notificationService;

    public ResponseEntity<?> createBid(BidCreateDto dto) {

        User user = userService.getUser(dto.getSender());

        Bid bid = bidMapper.dtoToEntity(dto, user);

        bidRepository.save(bid);

        String message = user.getFullName() + " tomonidan " + bid.getTitle() + " uchun ariza";

        notificationService.sendAdminNotification(message);

        return ResponseEntity.ok("Success");
    }
}
