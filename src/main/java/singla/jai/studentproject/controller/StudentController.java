package singla.jai.studentproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import singla.jai.studentproject.entity.Student;
import singla.jai.studentproject.service.StudentService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/student")
public class StudentController {
@Autowired StudentService service;

@RequestMapping(value = "",method = RequestMethod.GET)
  public List<Student> getAllStudents(){
    return service.findAllStudent();
}

  @RequestMapping(value="/{rollNum}",method=RequestMethod.GET)
  public ResponseEntity<?> getStudentByRollNum(@PathVariable("rollNum") int rollNum){
    Student studentFound= service.getStudentByRollNum(rollNum);
    return ResponseEntity.ok(studentFound);
  }


  @RequestMapping(value="/email/{email}",method=RequestMethod.GET)
  public ResponseEntity<Student> getStudentByEmail(@PathVariable("email") String email){
    Student studentFound= service.getStudentByEmail(email);
    return ResponseEntity.ok(studentFound);
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<Student> addNewStudent(@Valid @RequestBody Student student) {
  Student createdStudent= service.addStudent(student);
  URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{rollNum}").buildAndExpand(createdStudent.getRollNum()).toUri();
  return ResponseEntity.created(location).build();
}

  @RequestMapping(value = "/{rollNum}", method = RequestMethod.PUT)
  public ResponseEntity<Student> updateStudent(
      @PathVariable("rollNum") int rollNUm, @Valid @RequestBody Student student) {
  Student updateStudent = service.updateStudent(student,rollNUm);
  return ResponseEntity.ok(updateStudent);
}

@RequestMapping(value="/{rollNum}",method=RequestMethod.DELETE)
  public void deleteStudent(@PathVariable("rollNum") int rollNum){
  service.deleteStudent(rollNum);
}

@RequestMapping(value="",method=RequestMethod.DELETE)
  public void deleteAllStudent(){
  service.deleteAllStudent();
}


}
