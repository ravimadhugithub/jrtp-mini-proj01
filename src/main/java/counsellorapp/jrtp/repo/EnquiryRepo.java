package counsellorapp.jrtp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import counsellorapp.jrtp.entity.Enquiry;

@Repository
public interface EnquiryRepo extends JpaRepository<Enquiry, Integer>{

	List<Enquiry> findByClassModeAndCourseNameAndEnqStatusAndCounsellorCounsellorId(String string, String string2, String string3, int i);

	List<Enquiry> findByCounsellorCounsellorId(int counsellorId);

}
