package sardorcreate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sardorcreate.enums.ToolsName;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tool")
public class ToolController {

    @GetMapping("/get_tools_names")
    public ToolsName[] getToolsNames() {

        return ToolsName.values();
    }
}
