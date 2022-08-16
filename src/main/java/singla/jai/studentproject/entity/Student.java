package singla.jai.studentproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
  @Id
  @GeneratedValue
  private int rollNum;
  @Column(name = "first_name", nullable = false)
  @NotEmpty
  @Size(min = 3, max = 10)
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "email", nullable = false,unique = true)
  @NotEmpty
  @Email
  private String email;


}
