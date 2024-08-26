package counsellorapp.jrtp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import counsellorapp.jrtp.dto.ViewEnqFilterRequest;
import counsellorapp.jrtp.entity.Counsellor;
import counsellorapp.jrtp.entity.Enquiry;
import counsellorapp.jrtp.repo.CounsellorRepo;
import counsellorapp.jrtp.repo.EnquiryRepo;

@Service
public class EnquiryServiceImpl implements EnquiryService{

	@Autowired
	private EnquiryRepo enquiryRepo;
	@Autowired
	private CounsellorRepo counsellorRepo;
	
	@Override
	public Enquiry addEnquiry(Enquiry enquiry,int counsellorId) {
		try {
			Counsellor counsellor = counsellorRepo.findById(counsellorId).get();
			enquiry.setCounsellor(counsellor);
			
			Date currentDate = new Date();
			enquiry.setCreatedDate(currentDate);
			enquiry.setUpdatedDate(currentDate);
			
			Enquiry enq = enquiryRepo.save(enquiry);
			return enq;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<Enquiry> getEnquiriesbyfilter(ViewEnqFilterRequest engFilReq,int counsellorId) {
		
		Enquiry enq=new Enquiry();
		
		if(!ObjectUtils.isEmpty(engFilReq.getCourse())) {
			enq.setCourseName(engFilReq.getCourse());
		}
		
		if(!ObjectUtils.isEmpty(engFilReq.getClassMode())) {
			enq.setClassMode(engFilReq.getClassMode());
		}
		
		if(!ObjectUtils.isEmpty(engFilReq.getStatus())) {
			enq.setEnqStatus(engFilReq.getStatus());
		}
		
		Counsellor counsellor = counsellorRepo.findById(counsellorId).get();
		enq.setCounsellor(counsellor);
		
		ExampleMatcher matcher = ExampleMatcher.matching()     
				  .withIgnorePaths("enqId")
				  .withIgnorePaths("studentName")
				  .withIgnorePaths("studentPhno")
				  .withIgnorePaths("createdDate")
				  .withIgnorePaths("updatedDate");
		
		Example<Enquiry> ex=Example.of(enq,matcher);
//		Example.of(enq, null);

		List<Enquiry> enquiries = enquiryRepo.findAll(ex);
		return enquiries;
	}

	@Override
	public List<Enquiry> getAllEnquiries(int counsellorId) {
		List<Enquiry> allEnqs = enquiryRepo.findByCounsellorCounsellorId(counsellorId);
		return allEnqs;
	}

	@Override
	public Enquiry getAllEnquiryById(int enqId) {
		Enquiry enquiry = enquiryRepo.findById(enqId).get();
		return enquiry;
	}

}
