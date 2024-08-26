package counsellorapp.jrtp.service;

import java.util.List;

import counsellorapp.jrtp.dto.ViewEnqFilterRequest;
import counsellorapp.jrtp.entity.Enquiry;

public interface EnquiryService {

	public Enquiry addEnquiry(Enquiry enquiry,int counsellorId);
	
	public List<Enquiry> getEnquiriesbyfilter(ViewEnqFilterRequest engFilReq,int counsellorId);
	
	public List<Enquiry> getAllEnquiries(int counsellorId);
	
	public Enquiry getAllEnquiryById(int enqId);
	
}
