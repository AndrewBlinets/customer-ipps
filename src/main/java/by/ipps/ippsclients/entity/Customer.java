package by.ipps.ippsclients.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity implements Serializable {
    private String login;
    private String hashPassword;
    private String name;
    private List<String> roles;
    private String email;
    private boolean enabled;
    private boolean block;
    private Date dateLastChangePassword;

    public Customer(String name, List<String> roles) {
        this.name = name;
        this.roles = roles;
    }
}
