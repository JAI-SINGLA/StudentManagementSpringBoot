package singla.jai.studentproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import singla.jai.studentproject.entity.Student;
import singla.jai.studentproject.exception.ResourceNotFoundException;
import singla.jai.studentproject.repository.StudentRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired StudentRepository studentRepository;
   public List<Student> findAllStudent(){
     return studentRepository.findAll();

   }

   public Student addStudent(Student student)
   {
     logger.info("In addStudent");
     Student createdStudent=studentRepository.save(student);
     logger.info("After saving in addStudent");

     return createdStudent;
   }

   public Student getStudentByRollNum(int rollNum) {
     Student student= studentRepository.findById(rollNum).orElseThrow(() -> new ResourceNotFoundException("Student not found on :: " + rollNum));


     return student;

   }

   public Student updateStudent(Student student, int rollNum){
     Student studentToBeUpdated= getStudentByRollNum(rollNum);

     studentToBeUpdated.setFirstName(student.getFirstName());
     studentToBeUpdated.setLastName(student.getLastName());
     studentToBeUpdated.setEmail(student.getEmail());
    return studentRepository.save(studentToBeUpdated);
   }
   public void deleteStudent(int rollNum){
     Student studentToBeDeleted = getStudentByRollNum(rollNum);
      studentRepository.delete(studentToBeDeleted);
   }


  public void deleteAllStudent() {
     studentRepository.deleteAll();
  }

  public Student getStudentByEmail(String email) {
    Student student= studentRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Student not found on email:: " + email));


    logger.info("Exception found code breaks");
    return student;
  }
}
