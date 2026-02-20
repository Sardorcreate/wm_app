package sardorcreate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sardorcreate.dto.bid.BidCreateDto;
import sardorcreate.entity.Bid;
import sardorcreate.entity.User;
import sardorcreate.enums.BidStatus;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BidMapper {

    public Bid dtoToEntity(BidCreateDto dto, User user) {

        Bid bid = new Bid();

        bid.setSender(user);
        bid.setTitle(dto.getTitle());
        bid.setBody(dto.getBody());
        bid.setCount(dto.getCount());
        bid.setCreatedAt(LocalDateTime.now());
        bid.setStatus(BidStatus.SENT);

        return bid;
    }
}
