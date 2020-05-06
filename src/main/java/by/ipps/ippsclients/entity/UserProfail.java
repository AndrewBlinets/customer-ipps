package by.ipps.ippsclients.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfail extends BaseEntity implements Serializable {
  private String username;
  private String email;
  private String password;
  private String newPassword;
}
