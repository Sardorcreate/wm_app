package sardorcreate.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import sardorcreate.entity.Commutator;
import sardorcreate.enums.ToolsStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommSpecification {

    public static Specification<Commutator> filter(
            Long inventoryId,
            LocalDate date,
            ToolsStatus status
    ) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (inventoryId != null) {
                predicates.add(
                        cb.equal(
                                root.get("inventoryId").get("inventoryId"),
                                inventoryId
                        )
                );
            }

            if (date != null) {
                predicates.add(cb.equal(root.get("date"), date));
            }

            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
