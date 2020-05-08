package by.ipps.ippsclients.controller;

import by.ipps.ippsclients.controller.base.BaseEntityAbstractController;
import by.ipps.ippsclients.controller.base.BaseEntityController;
import by.ipps.ippsclients.entity.Project;
import by.ipps.ippsclients.resttemplate.ProjectRestTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/project")
@CrossOrigin
public class ProjectController extends BaseEntityAbstractController<Project, ProjectRestTemplate>
    implements BaseEntityController<Project> {

  protected ProjectController(ProjectRestTemplate projectRestTemplate) {
    super(projectRestTemplate, "/project", "id");
  }
}
