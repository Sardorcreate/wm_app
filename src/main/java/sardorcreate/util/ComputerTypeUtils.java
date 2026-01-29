package sardorcreate.util;

import sardorcreate.enums.ComputerType;
import sardorcreate.enums.ProcessorType;
import sardorcreate.enums.ProcessorVariant;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ComputerTypeUtils {
    private static final Map<ComputerType, Map<ProcessorType, List<ProcessorVariant>>> allowedProcVariants =
            Map.of(
                ComputerType.SERVER,
                    Map.of(
                        ProcessorType.INTEL,
                            List.of(ProcessorVariant.INTEL_XEON),
                        ProcessorType.AMD,
                            List.of(ProcessorVariant.AMD_EPYC),
                        ProcessorType.RISC,
                            List.of(ProcessorVariant.RISC_NEOVERSE)
                    ),
                ComputerType.DESKTOP,
                    Map.of(
                        ProcessorType.INTEL,
                            List.of(ProcessorVariant.INTEL_CORE),
                        ProcessorType.AMD,
                            List.of(ProcessorVariant.AMD_RYZEN),
                        ProcessorType.RISC,
                            List.of(ProcessorVariant.RISC_CORTEX)
                    ),
                ComputerType.LAPTOP,
                    Map.of(
                        ProcessorType.INTEL,
                            List.of(ProcessorVariant.INTEL_CORE),
                        ProcessorType.AMD,
                            List.of(ProcessorVariant.AMD_RYZEN),
                        ProcessorType.RISC,
                            List.of(ProcessorVariant.RISC_CORTEX)
                    ),
                ComputerType.MONOBLOCK,
                    Map.of(
                        ProcessorType.INTEL,
                            List.of(ProcessorVariant.INTEL_CORE),
                        ProcessorType.AMD,
                            List.of(ProcessorVariant.AMD_RYZEN),
                        ProcessorType.RISC,
                            List.of(ProcessorVariant.RISC_CORTEX)
                    )
            );

    public static List<ProcessorVariant> getAllowedProcVariants(ComputerType compType,
                                                            ProcessorType procType) {

        return allowedProcVariants
                .getOrDefault(compType, Collections.emptyMap())
                .getOrDefault(procType, Collections.emptyList());
    }
}
