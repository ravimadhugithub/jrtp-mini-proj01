package counsellorapp.jrtp.service;

import counsellorapp.jrtp.dto.DashboardResponse;
import counsellorapp.jrtp.entity.Counsellor;

public interface CounsellorService {

	public Counsellor addCounsellor(Counsellor c);
	public Counsellor getCounsellorByEmail(String email);
	public Counsellor getCounsellorByEmailPwd(String email,String pwd);
	public DashboardResponse getDashBoardRes(int counsellorId);
}
