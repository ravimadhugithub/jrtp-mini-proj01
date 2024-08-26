package counsellorapp.jrtp.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Enquiry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int enqId;
	private String studentName;
	private String studentPhno;
	private String courseName;
	private String classMode;
	private String enqStatus;
	private Date createdDate;
	private Date updatedDate;
	@ManyToOne
	private Counsellor counsellor;
	
}
