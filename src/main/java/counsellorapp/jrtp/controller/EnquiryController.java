package counsellorapp.jrtp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import counsellorapp.jrtp.dto.ViewEnqFilterRequest;
import counsellorapp.jrtp.entity.Enquiry;
import counsellorapp.jrtp.service.EnquiryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	
	@Autowired
	private EnquiryService enqServ;
	
	@GetMapping("/addEnequiry")
	public String addEnquiryform(Model model) {
		Enquiry e=new Enquiry();
		model.addAttribute("enquiry", e);
		return "enquiryform";
	}
	
	@PostMapping("/addEnequiry")
	public String addEnequiry(Enquiry enquiry,HttpServletRequest req,Model model) {
		HttpSession session = req.getSession(false);
		int cId = (int) session.getAttribute("counsellorId");
		
		
		Enquiry enq = enqServ.addEnquiry(enquiry, cId);
		if(null!=enq) {
			model.addAttribute("sMsg", "Enquiry is added");
			model.addAttribute("enquiry", new Enquiry());
		}
		return "enquiryform";
	}
	
	@GetMapping("/viewAllEnquiries")
	public String viewEnquiries(HttpServletRequest req,Model model) {
		
		HttpSession session = req.getSession(false);
		int cId = (int) session.getAttribute("counsellorId");
		List<Enquiry> allEnquiries = enqServ.getAllEnquiries(cId);
		model.addAttribute("enqList", allEnquiries);
		
		ViewEnqFilterRequest filterReq=new ViewEnqFilterRequest();
		model.addAttribute("viewEnqFilterRequest", filterReq);
		
		return "allEnquiries";
	}
	
	//filter-enquiries
	@PostMapping("/filter-enquiries")
	public String filterEnquiries(ViewEnqFilterRequest viewEnqFilterRequest,HttpServletRequest req,Model model) {
		
		HttpSession session = req.getSession(false);
		int cId = (int) session.getAttribute("counsellorId");
		List<Enquiry> allEnquiries = enqServ.getEnquiriesbyfilter(viewEnqFilterRequest, cId);
		model.addAttribute("enqList", allEnquiries);
		return "allEnquiries";
	}
	
	//editEnq
	@GetMapping("/editEnq")
	public String editEnq(@RequestParam int enqId,Model model) {
		
		Enquiry enquiry = enqServ.getAllEnquiryById(enqId);
		model.addAttribute("enquiry", enquiry);
		return "enquiryform";
	}
}
