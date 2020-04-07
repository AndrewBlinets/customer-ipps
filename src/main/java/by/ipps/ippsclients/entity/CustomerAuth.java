package by.ipps.ippsclients.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAuth {
  private long id;
  private String login;
  private String hashPassword;
  private String name;
  private List<String> roles;
  private String email;
  private boolean enabled;
  private boolean block;
  private Date dateLastChangePassword;
}
