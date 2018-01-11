package ibm.wjx.osserver.web.controller;

import ibm.wjx.osserver.operation.s2i.S2IOperation;
import ibm.wjx.osserver.web.dto.S2IScripts;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create Date: 1/11/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
@RestController
@RequestMapping("/s2i")
public class S2IController {

    @RequestMapping(value = "build", method = RequestMethod.POST)
    public boolean build(@RequestBody S2IScripts scripts) {
        S2IOperation operation = new S2IOperation(scripts.getAssembleScript(), scripts.getRunScript(), scripts.getImageName());
        operation.setBuilderImageName(scripts.getBuilderImageName());
        operation.setDockerFileScript(scripts.getDockerFileScript());
        operation.setSaveArtifactScript(scripts.getSaveArtifactScript());
        operation.setTestRunScript(scripts.getTestRunScript());
        operation.setUsageScript(scripts.getUsageScript());
        return operation.operate();
    }
}
