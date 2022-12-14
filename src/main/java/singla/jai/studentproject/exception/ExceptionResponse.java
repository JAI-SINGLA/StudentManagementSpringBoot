package singla.jai.studentproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
  private Date timestamp;
  private String status;
  private String message;
  private String details;
}
