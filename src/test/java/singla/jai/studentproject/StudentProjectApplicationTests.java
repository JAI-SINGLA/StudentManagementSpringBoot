package singla.jai.studentproject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpClientErrorException;
import singla.jai.studentproject.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentProjectApplicationTests {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int port;

  private String getRootUrl() {
    return "http://localhost:" + port;
  }
  @Test
  public void contextLoads() {
  }

  @Test
  public void testGetAllStudents() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity = new HttpEntity<String>(null, headers);

    ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/student",
      HttpMethod.GET, entity, String.class);

    Assert.assertEquals(200,response.getStatusCodeValue());
    //    logger.info("All Students -> {}",response.getBody());

    Assert.assertNotNull(response.getBody());
  }

  @Test
  public void testGetStudentById() {
    ResponseEntity<Student> student = restTemplate.getForEntity(getRootUrl() + "/student/48", Student.class);
    Assert.assertNotNull(student);
    Assert.assertEquals(student.getStatusCode(),HttpStatus.OK);
  }
  @Test
  public void testStudentNotFoundById() {
    ResponseEntity<Student> student = restTemplate.getForEntity(getRootUrl() + "/student/1" , Student.class);
    Assert.assertEquals(student.getStatusCode(),HttpStatus.NOT_FOUND);
  }
  @Test
  public void testCreateStudent() {
    Student student = new Student();
    student.setEmail("1wers@5693.com");
    student.setFirstName("asdmin");
    student.setLastName("asmin");

    ResponseEntity<Student> postResponse = restTemplate.postForEntity(getRootUrl() + "/student", student, Student.class);
    Assert.assertNotNull(postResponse);
    Assert.assertEquals(postResponse.getStatusCode(),HttpStatus.CREATED);
  }

  @Test
  public void testCreateInvalidStudent() {
    Student student = new Student();
    student.setFirstName("asdmin");
    student.setLastName("asmin");
      logger.info("In try");
    ResponseEntity<Student> postResponse = restTemplate.postForEntity(getRootUrl() + "/student", student, Student.class);
    logger.info("Here");
    Assert.assertEquals(postResponse.getStatusCode(),HttpStatus.BAD_REQUEST);
  }

  @Test
  public void testDeleteStudentById(){
    ResponseEntity<Student> student =
        restTemplate.getForEntity(getRootUrl() + "/student/60", Student.class);
    Assert.assertEquals(student.getStatusCode(),HttpStatus.OK);
    restTemplate.delete(getRootUrl() + "/student/60", Student.class);

    ResponseEntity<Student> response =
        restTemplate.getForEntity(getRootUrl() + "/student/60", Student.class);

    Assert.assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
  }

  @Test
  public void testUpdateStudentById(){
    Student student = new Student();
    student.setEmail("JaiSingla@023.com");
    student.setFirstName("Jai");
    student.setLastName("Singla");
    restTemplate.put(getRootUrl() + "/student/48" ,student);

    ResponseEntity<Student> response = restTemplate.getForEntity(getRootUrl() + "/student/48" , Student.class);
    Assert.assertEquals(response.getBody().getEmail(),"JaiSingla@023.com");

    Assert.assertEquals(response.getStatusCode(),HttpStatus.OK);
  }






}
