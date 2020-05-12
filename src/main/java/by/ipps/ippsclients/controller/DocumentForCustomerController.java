package by.ipps.ippsclients.controller;

import by.ipps.ippsclients.controller.base.BaseEntityAbstractController;
import by.ipps.ippsclients.controller.base.BaseEntityController;
import by.ipps.ippsclients.custom.CustomPage;
import by.ipps.ippsclients.entity.DocumentForCustomer;
import by.ipps.ippsclients.resttemplate.DocumentForCustomerRestTemplate;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/document")
@CrossOrigin
@RestController
public class DocumentForCustomerController
    extends BaseEntityAbstractController<DocumentForCustomer, DocumentForCustomerRestTemplate>
    implements BaseEntityController<DocumentForCustomer> {

  protected DocumentForCustomerController(
      DocumentForCustomerRestTemplate documentForCustomerRestTemplate) {
    super(documentForCustomerRestTemplate, "/documentForCustomer", "id");
  }

  @Override
  public ResponseEntity<DocumentForCustomer> getById(
      Long id, HttpServletRequest httpServletRequest) {
    return new ResponseEntity<>(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
  }

  @GetMapping(value = "/download/{id}")
  public void getFile(
      @PathVariable long id, HttpServletResponse response, HttpServletRequest httpServletRequest)
      throws IOException {
    ResponseEntity<DocumentForCustomer> documentForCustomerR =
        this.baseEntityRestTemplate
            .findById(id, this.url, this.getInfoFromToken(httpServletRequest));

    DocumentForCustomer documentForCustomer = documentForCustomerR.getBody();
    response.setContentType(Objects.requireNonNull(documentForCustomer).getMimeType());
    response.setHeader(
        "Content-Disposition",
        "attachment; filename=" + Objects.requireNonNull(documentForCustomer).getFileName());
    response.getOutputStream().write(Objects.requireNonNull(documentForCustomer).getFile());
  }

  @GetMapping("/byProject/{project}")
  @ResponseBody
  public ResponseEntity<CustomPage<DocumentForCustomer>> getPageByIdProject(
      @RequestParam(value = "page", required = false, defaultValue = "0") int page,
      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
      @RequestParam(value = "sort", required = false) String sort,
      HttpServletRequest httpServletRequest,
      @PathVariable long project) {
    return baseEntityRestTemplate.findPagingRecordsByProject(
        page,
        size,
        sort == null ? this.sortDefault : sort,
        url + "/byProjectPage/",
        getInfoFromToken(httpServletRequest),
        project);
  }

  @GetMapping("/byProject/{project}/all")
  @ResponseBody
  public ResponseEntity<List<DocumentForCustomer>> getAllByIdProject(
      HttpServletRequest httpServletRequest, @PathVariable long project) {
    return baseEntityRestTemplate.findAllByProject(
        url + "/byProject/", getInfoFromToken(httpServletRequest), project);
  }

  @GetMapping("/bySheet/{sheet}")
  @ResponseBody
  public ResponseEntity<CustomPage<DocumentForCustomer>> getPageByIdSheet(
      @RequestParam(value = "page", required = false, defaultValue = "0") int page,
      @RequestParam(value = "size", required = false, defaultValue = "10") int size,
      @RequestParam(value = "sort", required = false) String sort,
      HttpServletRequest httpServletRequest,
      @PathVariable long sheet) {
    return baseEntityRestTemplate.findPagingRecordsBySheet(
        page,
        size,
        sort == null ? this.sortDefault : sort,
        url + "/bySheetPage/",
        getInfoFromToken(httpServletRequest),
        sheet);
  }

  @GetMapping("/bySheet/{sheet}/all")
  @ResponseBody
  public ResponseEntity<List<DocumentForCustomer>> getAllByIdSheet(
      HttpServletRequest httpServletRequest, @PathVariable long sheet) {
    return baseEntityRestTemplate.findAllBySheet(
        url + "/bySheet/", getInfoFromToken(httpServletRequest), sheet);
  }
}
