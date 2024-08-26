package counsellorapp.jrtp.dto;

import lombok.Data;

@Data
public class DashboardResponse {
	private int totEnquires;
	private int openEnquires;
	private int enrolledEnquires;
	private int lostEnquires;
}
