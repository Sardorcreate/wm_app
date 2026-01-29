package sardorcreate.util;

import sardorcreate.enums.ROMType;
import sardorcreate.enums.ROMVariant;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RomTypeUtils {

    private static final Map<ROMType, List<ROMVariant>> allowedRomVariants =
            Map.of(
                    ROMType.SSD,
                        List.of(
                            ROMVariant.NVME,
                            ROMVariant.M_2,
                            ROMVariant.SATA
                        ),
                    ROMType.HDD,
                        List.of(
                            ROMVariant.SATA,
                            ROMVariant.SAS
                        )
            );

    public static List<ROMVariant> getAllowedRomVariants (ROMType romType) {

        return allowedRomVariants
                .getOrDefault(romType, Collections.emptyList());
    }
}
