package by.ipps.ippsclients.controller.base;

import by.ipps.ippsclients.custom.CustomPage;
import by.ipps.ippsclients.entity.BaseEntity;
import by.ipps.ippsclients.resttemplate.base.BaseEntityRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;


public abstract class BaseEntityAbstractController<T extends BaseEntity, S extends BaseEntityRestTemplate<T>>
        implements BaseEntityController<T> {

    protected final S baseEntityRestTemplate;
    protected String url;
    protected String sortDefault;

    protected BaseEntityAbstractController(S s, String url, String sortDefault) {
        this.baseEntityRestTemplate = s;
        this.url = url;
        this.sortDefault = sortDefault;
    }

    @Override
    public ResponseEntity<T> getById(Long id, String language, String section, String department) {
        return baseEntityRestTemplate.findById(id, url, language, section, department);
    }


    @Override
    public ResponseEntity<CustomPage<T>> getAllByPage(int page, int size, String sort, String language, String section,
                                                      String department) {
        return baseEntityRestTemplate.findPagingRecords(page, size, sort == null ? this.sortDefault : sort, language, url, section, department);
    }

    @Override
    public ResponseEntity<List<T>> getAll(String language, String section, String department) {
        return baseEntityRestTemplate.findAll(language, url, section, department);
    }

}
