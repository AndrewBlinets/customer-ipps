package by.ipps.ippsclients.controller;

import by.ipps.ippsclients.controller.base.BaseInfoForController;
import by.ipps.ippsclients.entity.BaseEntity;
import by.ipps.ippsclients.entity.FavoriteProject;
import by.ipps.ippsclients.entity.Project;
import by.ipps.ippsclients.resttemplate.FavoritesProjectRestTemplate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/favorites")
@CrossOrigin
public class FavoritesProjectController extends BaseInfoForController {

  @Autowired
  private FavoritesProjectRestTemplate restTemplate;

  @GetMapping
  @ResponseBody
  public ResponseEntity<List<Project>> getFavoritProject(HttpServletRequest request){
    return restTemplate.getFavoritProjectByIdCustomer(this.getInfoFromToken(request));
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<Project> addProjectToFavorite(HttpServletRequest request, @RequestBody
      FavoriteProject favoriteProject){
    favoriteProject.setCustomer(this.getInfoFromToken(request));
    return restTemplate.addFavoriteProject(favoriteProject);
  }

  @DeleteMapping("/{id}")
  public void removeFavoriteProject(HttpServletRequest request, @PathVariable long id){
    restTemplate.removeFavoriteProject(this.getInfoFromToken(request), id);
  }

}
