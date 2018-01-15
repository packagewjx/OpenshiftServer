package ibm.wjx.osserver.web.controller;

import ibm.wjx.osserver.manager.PodNetworkManager;
import ibm.wjx.osserver.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Create Date: 1/5/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
@RestController
@RequestMapping("/pod-network")
public class PodNetworkController {
    @Autowired
    private PodNetworkManager podNetworkManager;

    @RequestMapping(value = "/join-project", params = {"to", "selector"})
    public Result<Boolean> joinProject(@RequestParam("to") String toProject, @RequestParam("selector") String selector) {
        return podNetworkManager.joinProjects(toProject, selector);
    }

    /**
     * @param toProject
     * @param projects  project names split using comma.
     * @return true indicate success, false otherwise
     */
    @RequestMapping(value = "/join-project", params = {"to", "projects"})
    public Result<Boolean> joinProjects(@RequestParam("to") String toProject, @RequestParam("projects") String projects) {
        return podNetworkManager.joinProjects(toProject, Arrays.stream(projects.split(",")).collect(Collectors.toList()));
    }


    @RequestMapping(value = "/isolate-project", params = "selector")
    public Result<Boolean> isolateProject(String selector) {
        return podNetworkManager.isolateProjects(selector);
    }

    /**
     * @param projects project names split using comma.
     * @return true indicate success, false otherwise
     */
    @RequestMapping(value = "/isolate-project", params = "projects")
    public Result<Boolean> isolateProjects(@RequestParam("projects") String projects) {
        return podNetworkManager.isolateProjects(Arrays.stream(projects.split(",")).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/make-project-global", params = "selector")
    public Result<Boolean> makeProjectGlobal(@RequestParam("selector") String selector) {
        return podNetworkManager.makeProjectsGlobal(selector);
    }

    /**
     * @param projects project names split using comma.
     * @return true indicate success, false otherwise
     */
    @RequestMapping(value = "/make-project-global", params = "projects")
    public Result<Boolean> makeProjectsGlobal(String projects) {
        return podNetworkManager.makeProjectsGlobal(Arrays.stream(projects.split(",")).collect(Collectors.toList()));
    }


}
