package by.ipps.ippsclients.controller.base;

import by.ipps.ippsclients.custom.CustomPage;
import by.ipps.ippsclients.entity.BaseEntity;
import by.ipps.ippsclients.resttemplate.base.BaseEntityRestTemplate;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

public abstract class BaseEntityAbstractController<
        T extends BaseEntity, S extends BaseEntityRestTemplate<T>> extends BaseInfoForController
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
  public ResponseEntity<T> getById(
      Long id,
      String language,
      String section,
      String department,
      HttpServletRequest httpServletRequest) {
    return baseEntityRestTemplate.findById(
        id, url, language, section, department, getInfoFromToken(httpServletRequest));
  }

  @Override
  public ResponseEntity<CustomPage<T>> getAllByPage(
      int page,
      int size,
      String sort,
      String language,
      String section,
      String department,
      HttpServletRequest httpServletRequest) {
    return baseEntityRestTemplate.findPagingRecords(
        page,
        size,
        sort == null ? this.sortDefault : sort,
        language,
        url,
        section,
        department,
        getInfoFromToken(httpServletRequest));
  }

  @Override
  public ResponseEntity<List<T>> getAll(
      String language, String section, String department, HttpServletRequest httpServletRequest) {
    return baseEntityRestTemplate.findAll(
        language, url, section, department, getInfoFromToken(httpServletRequest));
  }
}
