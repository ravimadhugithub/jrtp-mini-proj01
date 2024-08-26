package counsellorapp.jrtp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import counsellorapp.jrtp.entity.Counsellor;

@Repository
public interface CounsellorRepo extends JpaRepository<Counsellor, Integer>{

	Optional<Counsellor> findByEmail(String email);
	
	Optional<Counsellor> findByEmailAndPwd(String email,String pwd);

}
