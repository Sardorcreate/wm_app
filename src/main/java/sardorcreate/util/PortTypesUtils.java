package sardorcreate.util;

import sardorcreate.enums.CommutatorType;
import sardorcreate.enums.PortType;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PortTypesUtils {

    private static final Map<CommutatorType, List<PortType>> allowedPortTypes =
            Map.ofEntries(
                    Map.entry(CommutatorType.HUB, List.of(PortType.NONE)),
                    Map.entry(CommutatorType.HUB_POE, List.of(PortType.NONE)),
                    Map.entry(CommutatorType.SMART_SWITCH, List.of(PortType.NONE)),
                    Map.entry(CommutatorType.SMART_SWITCH_POE, List.of(PortType.NONE)),
                    Map.entry(CommutatorType.L2, List.of(PortType.RJ45, PortType.SFP, PortType.SFP_PLUS, PortType.SFP28, PortType.QSFP)),
                    Map.entry(CommutatorType.L2_POE, List.of(PortType.RJ45, PortType.SFP, PortType.SFP_PLUS, PortType.SFP28, PortType.QSFP)),
                    Map.entry(CommutatorType.L2_PLUS, List.of(PortType.RJ45, PortType.SFP, PortType.SFP_PLUS, PortType.SFP28, PortType.QSFP)),
                    Map.entry(CommutatorType.L2_PLUS_POE, List.of(PortType.RJ45, PortType.SFP, PortType.SFP_PLUS, PortType.SFP28, PortType.QSFP)),
                    Map.entry(CommutatorType.L3, List.of(PortType.RJ45, PortType.SFP, PortType.SFP_PLUS, PortType.SFP28, PortType.QSFP)),
                    Map.entry(CommutatorType.L3_POE, List.of(PortType.RJ45, PortType.SFP, PortType.SFP_PLUS, PortType.SFP28, PortType.QSFP)),
                    Map.entry(CommutatorType.SAN_SWITCH, List.of(PortType.FIBRE_CHANNEL))
            );

    public static List<PortType> getAllowedPortTypes(CommutatorType cType) {

        return allowedPortTypes
                .getOrDefault(cType, Collections.emptyList());
    }
}
