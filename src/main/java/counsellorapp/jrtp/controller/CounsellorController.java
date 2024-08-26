package counsellorapp.jrtp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import counsellorapp.jrtp.dto.DashboardResponse;
import counsellorapp.jrtp.entity.Counsellor;
import counsellorapp.jrtp.service.CounsellorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	
	@Autowired
	private CounsellorService cServ;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("counsellor", new Counsellor());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(Counsellor counsellor,HttpServletRequest req,Model model) {
		Counsellor conslr = cServ.getCounsellorByEmailPwd(counsellor.getEmail(), counsellor.getPwd());
		if(conslr == null) {
			model.addAttribute("eMsg", "Invalid Login");
			return "login";
		}
			model.addAttribute("sMsg", "Login Success");
			HttpSession session = req.getSession(true);
			session.setAttribute("counsellorId", conslr.getCounsellorId());
			
			DashboardResponse dashboard = cServ.getDashBoardRes(conslr.getCounsellorId());
			model.addAttribute("dashboard", dashboard);
			return "dashboard";
			
	}
	
	@GetMapping("/dashboard")
	public String dashboard(HttpServletRequest req,Model model) {
		HttpSession session = req.getSession(false);
		int cId = (int) session.getAttribute("counsellorId");
		DashboardResponse dashboard = cServ.getDashBoardRes(cId);
		model.addAttribute("dashboard", dashboard);
		return "dashboard";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		Counsellor c=new Counsellor();
		model.addAttribute("counsellor", c);
		return "register";
	}
	
	@PostMapping("/register")
	public String registerCounsellor(Counsellor counsellor,Model model) {
		Counsellor conslr = cServ.getCounsellorByEmail(counsellor.getEmail());
		if(null!=conslr) {
			model.addAttribute("eMsg", "Email already exists");
			return "register";
		}
		Counsellor counslr = cServ.addCounsellor(counsellor);
		model.addAttribute("sMsg", "Counsellor is added successfully");
		model.addAttribute("counsellor", new Counsellor());
		return "register";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req,Model model) {
		HttpSession session = req.getSession(true);
		session.invalidate();
		model.addAttribute("counsellor", new Counsellor());
		return "login";
	}
}
