package app.buf.buf.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name = "users")
public class User implements Serializable {
}
