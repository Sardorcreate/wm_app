package sardorcreate.util;

import sardorcreate.enums.CommutatorType;
import sardorcreate.enums.PortCount;
import sardorcreate.enums.PortType;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PortTypeAndCountUtils {

    private static final Map<CommutatorType, Map<PortType,  List<PortCount>>> allowedPortCounts =
            Map.ofEntries(
                Map.entry(
                    CommutatorType.L2,
                        Map.of(
                            PortType.RJ45, List.of(PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.SFP, List.of(PortCount.C2, PortCount.C4, PortCount.C6 ,PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.SFP_PLUS, List.of(PortCount.C2, PortCount.C4, PortCount.C6 ,PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.SFP28, List.of(PortCount.C2, PortCount.C4, PortCount.C6 ,PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.QSFP, List.of(PortCount.C2, PortCount.C4, PortCount.C6, PortCount.C8)
                        )
                ),
                Map.entry(
                    CommutatorType.L2_POE,
                        Map.of(
                            PortType.RJ45,
                                List.of(PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.SFP,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6 ,PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.SFP_PLUS,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6 ,PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.SFP28,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6 ,PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.QSFP,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6, PortCount.C8)
                        )
                ),
                Map.entry(
                    CommutatorType.L2_PLUS,
                        Map.of(
                            PortType.RJ45,
                                List.of(PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.SFP,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6 ,PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.SFP_PLUS,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6 ,PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.SFP28,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6 ,PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.QSFP,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6, PortCount.C8)
                        )
                ),
                Map.entry(
                    CommutatorType.L2_PLUS_POE,
                        Map.of(
                            PortType.RJ45,
                                List.of(PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.SFP,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6 ,PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.SFP_PLUS,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6 ,PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.SFP28,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6 ,PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.QSFP,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6, PortCount.C8)
                        )
                ),
                Map.entry(
                    CommutatorType.L3,
                        Map.of(
                            PortType.RJ45,
                                List.of(PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.SFP,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6 ,PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.SFP_PLUS,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6 ,PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.SFP28,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6 ,PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.QSFP,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6, PortCount.C8)
                        )
                    ),
                Map.entry(
                    CommutatorType.L3_POE,
                        Map.of(
                            PortType.RJ45,
                                List.of(PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.SFP,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6 ,PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.SFP_PLUS,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6 ,PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.SFP28,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6 ,PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48),
                            PortType.QSFP,
                                List.of(PortCount.C2, PortCount.C4, PortCount.C6, PortCount.C8)
                        )
                ),
                Map.entry(
                    CommutatorType.SAN_SWITCH,
                        Map.of(
                            PortType.FIBRE_CHANNEL,
                                List.of(PortCount.C8, PortCount.C16, PortCount.C24, PortCount.C48)
                        )
                ),
                Map.entry(
                    CommutatorType.HUB,
                        Map.of(
                            PortType.NONE,
                                List.of(PortCount.C8, PortCount.C16, PortCount.C24)
                        )
                ),
                Map.entry(
                    CommutatorType.HUB_POE,
                        Map.of(
                            PortType.NONE,
                                List.of(PortCount.C8, PortCount.C16, PortCount.C24)
                        )
                ),
                Map.entry(
                    CommutatorType.SMART_SWITCH,
                        Map.of(
                            PortType.NONE,
                                List.of(PortCount.C8, PortCount.C16, PortCount.C24)
                        )
                ),
                Map.entry(
                    CommutatorType.SMART_SWITCH_POE,
                        Map.of(
                            PortType.NONE,
                                List.of(PortCount.C8, PortCount.C16, PortCount.C24)
                        )
                )
            );

    public static List<PortCount> getAllowedPortCounts(CommutatorType comType,
                                                       PortType pType) {

        return allowedPortCounts
                .getOrDefault(comType, Collections.emptyMap())
                .getOrDefault(pType, Collections.emptyList());
    }
}
