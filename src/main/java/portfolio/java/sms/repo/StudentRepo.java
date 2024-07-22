package portfolio.java.sms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import portfolio.java.sms.entity.Student;

public interface StudentRepo extends JpaRepository<Student,Long>{
    
}
