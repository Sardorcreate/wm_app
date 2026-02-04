package sardorcreate.util;

import sardorcreate.enums.PortSpeed;
import sardorcreate.enums.PortType;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PortTypeSpeedUtils {

    private static final Map<PortType, List<PortSpeed>> allowedPortSpeeds =
            Map.of(
                PortType.RJ45,
                    List.of(
                        PortSpeed.Mb10_100,
                        PortSpeed.Mb100_1000
                    ),
                PortType.SFP,
                    List.of(
                        PortSpeed.Gb1,
                        PortSpeed.Gb1_10
                    ),
                PortType.SFP_PLUS,
                    List.of(
                        PortSpeed.Gb1_10
                    ),
                PortType.SFP28,
                    List.of(
                        PortSpeed.Gb10_25
                    ),
                PortType.QSFP,
                    List.of(
                        PortSpeed.Gb40_100
                    ),
                PortType.FIBRE_CHANNEL,
                    List.of(
                        PortSpeed.Gb8_16,
                        PortSpeed.Gb16_32
                    ),
                PortType.NONE,
                    List.of(
                        PortSpeed.Mb10_100,
                        PortSpeed.Mb100_1000
                    )
            );

    public static List<PortSpeed> getAllowedPortSpeeds (PortType pType) {

        return allowedPortSpeeds
                .getOrDefault(pType, Collections.emptyList());
    }
}
