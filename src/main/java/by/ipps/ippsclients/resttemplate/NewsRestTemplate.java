package by.ipps.ippsclients.resttemplate;

import by.ipps.ippsclients.custom.CustomPage;
import by.ipps.ippsclients.entity.News;
import by.ipps.ippsclients.resttemplate.base.BaseEntityRestTemplate;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface NewsRestTemplate extends BaseEntityRestTemplate<News> {

    ResponseEntity<CustomPage<News>> findByIdProject(long project, int page, int size, String sort);

    ResponseEntity<List<News>> findByIdProject(long project);
}