package counsellorapp.jrtp.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import counsellorapp.jrtp.dto.DashboardResponse;
import counsellorapp.jrtp.entity.Counsellor;
import counsellorapp.jrtp.entity.Enquiry;
import counsellorapp.jrtp.repo.CounsellorRepo;
import counsellorapp.jrtp.repo.EnquiryRepo;

@Service
public class CounsellorServiceImpl implements CounsellorService{

	@Autowired
	private CounsellorRepo counsellorRepo;
	
	@Autowired
	private EnquiryRepo enquiryRepo;
	
	@Override
	public Counsellor addCounsellor(Counsellor c) {
		try {
			Date currentDate = new Date();
			c.setCreated_date(currentDate);
			c.setUpdated_date(currentDate);
			Counsellor counslr = counsellorRepo.save(c);
			return counslr;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public DashboardResponse getDashBoardRes(int counsellorId) {
		List<Enquiry> enquires=enquiryRepo.findByCounsellorCounsellorId(counsellorId);
		
		List<Enquiry> openEquires = enquires.stream().filter(e->e.getEnqStatus().
				equalsIgnoreCase("Open")).collect(Collectors.toList());
		
		List<Enquiry> enrolledEquires = enquires.stream().filter(e->e.getEnqStatus().
				equalsIgnoreCase("Enrolled")).collect(Collectors.toList());
		
		List<Enquiry> lostEquires = enquires.stream().filter(e->e.getEnqStatus().
				equalsIgnoreCase("Lost")).collect(Collectors.toList());
		
		int tot = enquires.size();
		int open = openEquires.size();
		int enrolled = enrolledEquires.size();
		int lost=lostEquires.size();
		
		DashboardResponse dRes=new DashboardResponse();
		dRes.setTotEnquires(tot);
		dRes.setEnrolledEnquires(enrolled);
		dRes.setOpenEnquires(open);
		dRes.setLostEnquires(lost);
		
		return dRes;
	}
	
	@Override
	public Counsellor getCounsellorByEmail(String email) {
		Counsellor c=counsellorRepo.findByEmail(email).orElse(null);
		return c;
		
	}

	@Override
	public Counsellor getCounsellorByEmailPwd(String email, String pwd) {
		Counsellor counsellor = counsellorRepo.findByEmailAndPwd(email, pwd).orElse(null);
		return counsellor;
	}

}
