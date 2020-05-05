package by.ipps.ippsclients.controller;

import by.ipps.ippsclients.controller.base.BaseEntityAbstractController;
import by.ipps.ippsclients.controller.base.BaseEntityController;
import by.ipps.ippsclients.custom.CustomPage;
import by.ipps.ippsclients.entity.News;
import by.ipps.ippsclients.resttemplate.NewsRestTemplate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
@CrossOrigin
public class NewsController extends BaseEntityAbstractController<News, NewsRestTemplate>
    implements BaseEntityController<News> {

  protected NewsController(
      NewsRestTemplate newsRestTemplate) {
    super(newsRestTemplate, "/news", "id");
  }

  @GetMapping("/byIdProjectPage/{project}")
  @ResponseBody
  public ResponseEntity<CustomPage<News>> getNewsByProiject(
      @RequestParam(value = "page", required = false, defaultValue = "0") int page,
      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
      @RequestParam(value = "sort", required = false) String sort,
      @PathVariable long project,
      HttpServletRequest request) {
    return baseEntityRestTemplate.findByIdProject(project, page, size, sort, this.getInfoFromToken(request));
  }

  @GetMapping("/byIdProject/{project}")
  @ResponseBody
  public ResponseEntity<List<News>> getNewsByProiject(@PathVariable long project, HttpServletRequest request) {
    return baseEntityRestTemplate.findByIdProject(project, this.getInfoFromToken(request));
  }
}
