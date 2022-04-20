package app.buf.buf.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "phone"),
                @UniqueConstraint(columnNames = "email")
        })
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @Column(name = "uuid")
    private String userId;

    @Column(name = "phone")
    @NotEmpty
    private String username;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty
    private String password;
    private boolean isActive;
    private boolean isNotLocked;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate;

    @Column(name = "last_login_date")
    private Date lastLoginDate;

    @Column(name = "last_login_date_display")
    private Date lastLoginDateDisplay;
}
