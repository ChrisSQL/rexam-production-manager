package com.rexam.par.model;

import java.util.Date;

public class ParModel {

	int Form;

	String Crew, Code, Machine, ShiftManager, Technician, Operator, Engineer;
	Boolean LinerHeadCheck1, LinerHeadCheck2, LinerHeadCheck3, LinerHeadCheck4, LinerHeadCheck5, LinerHeadCheck6,
			LinerHeadCheck7, LinerHeadCheck8, ShellPressCheck1, ShellPressCheck2, ShellPressCheck3, ShellPressCheck4,
			ShellPressCheck5, ShellPressCheck6, ShellPressCheck7, ShellPressCheck8, ShellPressCheck9, ShellPressCheck10,
			ShellPressCheck11, ShellPressCheck12, ShellPressCheck13, ShellPressCheck14, ShellPressCheck15,
			ShellPressCheck16, ShellPressCheck17, ShellPressCheck18, ShellPressCheck19, ShellPressCheck20,
			ShellPressCheck21, ShellPressCheck22, ShellPressCheck23, ShellPressCheck24, ShellPressCheck25,
			ShellPressCheck26, StolleCheck1, StolleCheck2, StolleCheck3, StolleCheck4, Status;
	Double Score1A, Score1B, Score1C, Score1D, Score3A, Score3B, Score3C, Score3D, Score6A, Score6B, Score6C, Score6D,
			Score9A, Score9B, Score9C, Score9D;

	String TimeStarted, TimeInToolRoom, TimeFinished, Signed, Before, ActionTaken, After;
	Date date, DateSigned;
	public ParModel(int form, String crew, String code, String machine, String shiftManager, String technician,
			String operator, String engineer, Boolean linerHeadCheck1, Boolean linerHeadCheck2, Boolean linerHeadCheck3,
			Boolean linerHeadCheck4, Boolean linerHeadCheck5, Boolean linerHeadCheck6, Boolean linerHeadCheck7,
			Boolean linerHeadCheck8, Boolean shellPressCheck1, Boolean shellPressCheck2, Boolean shellPressCheck3,
			Boolean shellPressCheck4, Boolean shellPressCheck5, Boolean shellPressCheck6, Boolean shellPressCheck7,
			Boolean shellPressCheck8, Boolean shellPressCheck9, Boolean shellPressCheck10, Boolean shellPressCheck11,
			Boolean shellPressCheck12, Boolean shellPressCheck13, Boolean shellPressCheck14, Boolean shellPressCheck15,
			Boolean shellPressCheck16, Boolean shellPressCheck17, Boolean shellPressCheck18, Boolean shellPressCheck19,
			Boolean shellPressCheck20, Boolean shellPressCheck21, Boolean shellPressCheck22, Boolean shellPressCheck23,
			Boolean shellPressCheck24, Boolean shellPressCheck25, Boolean shellPressCheck26, Boolean stolleCheck1,
			Boolean stolleCheck2, Boolean stolleCheck3, Boolean stolleCheck4, Boolean status, Double score1a,
			Double score1b, Double score1c, Double score1d, Double score3a, Double score3b, Double score3c,
			Double score3d, Double score6a, Double score6b, Double score6c, Double score6d, Double score9a,
			Double score9b, Double score9c, Double score9d, String timeStarted, String timeInToolRoom,
			String timeFinished, String signed, String before, String actionTaken, String after, Date date,
			Date dateSigned) {
		super();
		Form = form;
		Crew = crew;
		Code = code;
		Machine = machine;
		ShiftManager = shiftManager;
		Technician = technician;
		Operator = operator;
		Engineer = engineer;
		LinerHeadCheck1 = linerHeadCheck1;
		LinerHeadCheck2 = linerHeadCheck2;
		LinerHeadCheck3 = linerHeadCheck3;
		LinerHeadCheck4 = linerHeadCheck4;
		LinerHeadCheck5 = linerHeadCheck5;
		LinerHeadCheck6 = linerHeadCheck6;
		LinerHeadCheck7 = linerHeadCheck7;
		LinerHeadCheck8 = linerHeadCheck8;
		ShellPressCheck1 = shellPressCheck1;
		ShellPressCheck2 = shellPressCheck2;
		ShellPressCheck3 = shellPressCheck3;
		ShellPressCheck4 = shellPressCheck4;
		ShellPressCheck5 = shellPressCheck5;
		ShellPressCheck6 = shellPressCheck6;
		ShellPressCheck7 = shellPressCheck7;
		ShellPressCheck8 = shellPressCheck8;
		ShellPressCheck9 = shellPressCheck9;
		ShellPressCheck10 = shellPressCheck10;
		ShellPressCheck11 = shellPressCheck11;
		ShellPressCheck12 = shellPressCheck12;
		ShellPressCheck13 = shellPressCheck13;
		ShellPressCheck14 = shellPressCheck14;
		ShellPressCheck15 = shellPressCheck15;
		ShellPressCheck16 = shellPressCheck16;
		ShellPressCheck17 = shellPressCheck17;
		ShellPressCheck18 = shellPressCheck18;
		ShellPressCheck19 = shellPressCheck19;
		ShellPressCheck20 = shellPressCheck20;
		ShellPressCheck21 = shellPressCheck21;
		ShellPressCheck22 = shellPressCheck22;
		ShellPressCheck23 = shellPressCheck23;
		ShellPressCheck24 = shellPressCheck24;
		ShellPressCheck25 = shellPressCheck25;
		ShellPressCheck26 = shellPressCheck26;
		StolleCheck1 = stolleCheck1;
		StolleCheck2 = stolleCheck2;
		StolleCheck3 = stolleCheck3;
		StolleCheck4 = stolleCheck4;
		Status = status;
		Score1A = score1a;
		Score1B = score1b;
		Score1C = score1c;
		Score1D = score1d;
		Score3A = score3a;
		Score3B = score3b;
		Score3C = score3c;
		Score3D = score3d;
		Score6A = score6a;
		Score6B = score6b;
		Score6C = score6c;
		Score6D = score6d;
		Score9A = score9a;
		Score9B = score9b;
		Score9C = score9c;
		Score9D = score9d;
		TimeStarted = timeStarted;
		TimeInToolRoom = timeInToolRoom;
		TimeFinished = timeFinished;
		Signed = signed;
		Before = before;
		ActionTaken = actionTaken;
		After = after;
		this.date = date;
		DateSigned = dateSigned;
	}
	public ParModel() {
		super();
	}
	public int getForm() {
		return Form;
	}
	public void setForm(int form) {
		Form = form;
	}
	public String getCrew() {
		return Crew;
	}
	public void setCrew(String crew) {
		Crew = crew;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getMachine() {
		return Machine;
	}
	public void setMachine(String machine) {
		Machine = machine;
	}
	public String getShiftManager() {
		return ShiftManager;
	}
	public void setShiftManager(String shiftManager) {
		ShiftManager = shiftManager;
	}
	public String getTechnician() {
		return Technician;
	}
	public void setTechnician(String technician) {
		Technician = technician;
	}
	public String getOperator() {
		return Operator;
	}
	public void setOperator(String operator) {
		Operator = operator;
	}
	public String getEngineer() {
		return Engineer;
	}
	public void setEngineer(String engineer) {
		Engineer = engineer;
	}
	public Boolean getLinerHeadCheck1() {
		return LinerHeadCheck1;
	}
	public void setLinerHeadCheck1(Boolean linerHeadCheck1) {
		LinerHeadCheck1 = linerHeadCheck1;
	}
	public Boolean getLinerHeadCheck2() {
		return LinerHeadCheck2;
	}
	public void setLinerHeadCheck2(Boolean linerHeadCheck2) {
		LinerHeadCheck2 = linerHeadCheck2;
	}
	public Boolean getLinerHeadCheck3() {
		return LinerHeadCheck3;
	}
	public void setLinerHeadCheck3(Boolean linerHeadCheck3) {
		LinerHeadCheck3 = linerHeadCheck3;
	}
	public Boolean getLinerHeadCheck4() {
		return LinerHeadCheck4;
	}
	public void setLinerHeadCheck4(Boolean linerHeadCheck4) {
		LinerHeadCheck4 = linerHeadCheck4;
	}
	public Boolean getLinerHeadCheck5() {
		return LinerHeadCheck5;
	}
	public void setLinerHeadCheck5(Boolean linerHeadCheck5) {
		LinerHeadCheck5 = linerHeadCheck5;
	}
	public Boolean getLinerHeadCheck6() {
		return LinerHeadCheck6;
	}
	public void setLinerHeadCheck6(Boolean linerHeadCheck6) {
		LinerHeadCheck6 = linerHeadCheck6;
	}
	public Boolean getLinerHeadCheck7() {
		return LinerHeadCheck7;
	}
	public void setLinerHeadCheck7(Boolean linerHeadCheck7) {
		LinerHeadCheck7 = linerHeadCheck7;
	}
	public Boolean getLinerHeadCheck8() {
		return LinerHeadCheck8;
	}
	public void setLinerHeadCheck8(Boolean linerHeadCheck8) {
		LinerHeadCheck8 = linerHeadCheck8;
	}
	public Boolean getShellPressCheck1() {
		return ShellPressCheck1;
	}
	public void setShellPressCheck1(Boolean shellPressCheck1) {
		ShellPressCheck1 = shellPressCheck1;
	}
	public Boolean getShellPressCheck2() {
		return ShellPressCheck2;
	}
	public void setShellPressCheck2(Boolean shellPressCheck2) {
		ShellPressCheck2 = shellPressCheck2;
	}
	public Boolean getShellPressCheck3() {
		return ShellPressCheck3;
	}
	public void setShellPressCheck3(Boolean shellPressCheck3) {
		ShellPressCheck3 = shellPressCheck3;
	}
	public Boolean getShellPressCheck4() {
		return ShellPressCheck4;
	}
	public void setShellPressCheck4(Boolean shellPressCheck4) {
		ShellPressCheck4 = shellPressCheck4;
	}
	public Boolean getShellPressCheck5() {
		return ShellPressCheck5;
	}
	public void setShellPressCheck5(Boolean shellPressCheck5) {
		ShellPressCheck5 = shellPressCheck5;
	}
	public Boolean getShellPressCheck6() {
		return ShellPressCheck6;
	}
	public void setShellPressCheck6(Boolean shellPressCheck6) {
		ShellPressCheck6 = shellPressCheck6;
	}
	public Boolean getShellPressCheck7() {
		return ShellPressCheck7;
	}
	public void setShellPressCheck7(Boolean shellPressCheck7) {
		ShellPressCheck7 = shellPressCheck7;
	}
	public Boolean getShellPressCheck8() {
		return ShellPressCheck8;
	}
	public void setShellPressCheck8(Boolean shellPressCheck8) {
		ShellPressCheck8 = shellPressCheck8;
	}
	public Boolean getShellPressCheck9() {
		return ShellPressCheck9;
	}
	public void setShellPressCheck9(Boolean shellPressCheck9) {
		ShellPressCheck9 = shellPressCheck9;
	}
	public Boolean getShellPressCheck10() {
		return ShellPressCheck10;
	}
	public void setShellPressCheck10(Boolean shellPressCheck10) {
		ShellPressCheck10 = shellPressCheck10;
	}
	public Boolean getShellPressCheck11() {
		return ShellPressCheck11;
	}
	public void setShellPressCheck11(Boolean shellPressCheck11) {
		ShellPressCheck11 = shellPressCheck11;
	}
	public Boolean getShellPressCheck12() {
		return ShellPressCheck12;
	}
	public void setShellPressCheck12(Boolean shellPressCheck12) {
		ShellPressCheck12 = shellPressCheck12;
	}
	public Boolean getShellPressCheck13() {
		return ShellPressCheck13;
	}
	public void setShellPressCheck13(Boolean shellPressCheck13) {
		ShellPressCheck13 = shellPressCheck13;
	}
	public Boolean getShellPressCheck14() {
		return ShellPressCheck14;
	}
	public void setShellPressCheck14(Boolean shellPressCheck14) {
		ShellPressCheck14 = shellPressCheck14;
	}
	public Boolean getShellPressCheck15() {
		return ShellPressCheck15;
	}
	public void setShellPressCheck15(Boolean shellPressCheck15) {
		ShellPressCheck15 = shellPressCheck15;
	}
	public Boolean getShellPressCheck16() {
		return ShellPressCheck16;
	}
	public void setShellPressCheck16(Boolean shellPressCheck16) {
		ShellPressCheck16 = shellPressCheck16;
	}
	public Boolean getShellPressCheck17() {
		return ShellPressCheck17;
	}
	public void setShellPressCheck17(Boolean shellPressCheck17) {
		ShellPressCheck17 = shellPressCheck17;
	}
	public Boolean getShellPressCheck18() {
		return ShellPressCheck18;
	}
	public void setShellPressCheck18(Boolean shellPressCheck18) {
		ShellPressCheck18 = shellPressCheck18;
	}
	public Boolean getShellPressCheck19() {
		return ShellPressCheck19;
	}
	public void setShellPressCheck19(Boolean shellPressCheck19) {
		ShellPressCheck19 = shellPressCheck19;
	}
	public Boolean getShellPressCheck20() {
		return ShellPressCheck20;
	}
	public void setShellPressCheck20(Boolean shellPressCheck20) {
		ShellPressCheck20 = shellPressCheck20;
	}
	public Boolean getShellPressCheck21() {
		return ShellPressCheck21;
	}
	public void setShellPressCheck21(Boolean shellPressCheck21) {
		ShellPressCheck21 = shellPressCheck21;
	}
	public Boolean getShellPressCheck22() {
		return ShellPressCheck22;
	}
	public void setShellPressCheck22(Boolean shellPressCheck22) {
		ShellPressCheck22 = shellPressCheck22;
	}
	public Boolean getShellPressCheck23() {
		return ShellPressCheck23;
	}
	public void setShellPressCheck23(Boolean shellPressCheck23) {
		ShellPressCheck23 = shellPressCheck23;
	}
	public Boolean getShellPressCheck24() {
		return ShellPressCheck24;
	}
	public void setShellPressCheck24(Boolean shellPressCheck24) {
		ShellPressCheck24 = shellPressCheck24;
	}
	public Boolean getShellPressCheck25() {
		return ShellPressCheck25;
	}
	public void setShellPressCheck25(Boolean shellPressCheck25) {
		ShellPressCheck25 = shellPressCheck25;
	}
	public Boolean getShellPressCheck26() {
		return ShellPressCheck26;
	}
	public void setShellPressCheck26(Boolean shellPressCheck26) {
		ShellPressCheck26 = shellPressCheck26;
	}
	public Boolean getStolleCheck1() {
		return StolleCheck1;
	}
	public void setStolleCheck1(Boolean stolleCheck1) {
		StolleCheck1 = stolleCheck1;
	}
	public Boolean getStolleCheck2() {
		return StolleCheck2;
	}
	public void setStolleCheck2(Boolean stolleCheck2) {
		StolleCheck2 = stolleCheck2;
	}
	public Boolean getStolleCheck3() {
		return StolleCheck3;
	}
	public void setStolleCheck3(Boolean stolleCheck3) {
		StolleCheck3 = stolleCheck3;
	}
	public Boolean getStolleCheck4() {
		return StolleCheck4;
	}
	public void setStolleCheck4(Boolean stolleCheck4) {
		StolleCheck4 = stolleCheck4;
	}
	public Boolean getStatus() {
		return Status;
	}
	public void setStatus(Boolean status) {
		Status = status;
	}
	public Double getScore1A() {
		return Score1A;
	}
	public void setScore1A(Double score1a) {
		Score1A = score1a;
	}
	public Double getScore1B() {
		return Score1B;
	}
	public void setScore1B(Double score1b) {
		Score1B = score1b;
	}
	public Double getScore1C() {
		return Score1C;
	}
	public void setScore1C(Double score1c) {
		Score1C = score1c;
	}
	public Double getScore1D() {
		return Score1D;
	}
	public void setScore1D(Double score1d) {
		Score1D = score1d;
	}
	public Double getScore3A() {
		return Score3A;
	}
	public void setScore3A(Double score3a) {
		Score3A = score3a;
	}
	public Double getScore3B() {
		return Score3B;
	}
	public void setScore3B(Double score3b) {
		Score3B = score3b;
	}
	public Double getScore3C() {
		return Score3C;
	}
	public void setScore3C(Double score3c) {
		Score3C = score3c;
	}
	public Double getScore3D() {
		return Score3D;
	}
	public void setScore3D(Double score3d) {
		Score3D = score3d;
	}
	public Double getScore6A() {
		return Score6A;
	}
	public void setScore6A(Double score6a) {
		Score6A = score6a;
	}
	public Double getScore6B() {
		return Score6B;
	}
	public void setScore6B(Double score6b) {
		Score6B = score6b;
	}
	public Double getScore6C() {
		return Score6C;
	}
	public void setScore6C(Double score6c) {
		Score6C = score6c;
	}
	public Double getScore6D() {
		return Score6D;
	}
	public void setScore6D(Double score6d) {
		Score6D = score6d;
	}
	public Double getScore9A() {
		return Score9A;
	}
	public void setScore9A(Double score9a) {
		Score9A = score9a;
	}
	public Double getScore9B() {
		return Score9B;
	}
	public void setScore9B(Double score9b) {
		Score9B = score9b;
	}
	public Double getScore9C() {
		return Score9C;
	}
	public void setScore9C(Double score9c) {
		Score9C = score9c;
	}
	public Double getScore9D() {
		return Score9D;
	}
	public void setScore9D(Double score9d) {
		Score9D = score9d;
	}
	public String getTimeStarted() {
		return TimeStarted;
	}
	public void setTimeStarted(String timeStarted) {
		TimeStarted = timeStarted;
	}
	public String getTimeInToolRoom() {
		return TimeInToolRoom;
	}
	public void setTimeInToolRoom(String timeInToolRoom) {
		TimeInToolRoom = timeInToolRoom;
	}
	public String getTimeFinished() {
		return TimeFinished;
	}
	public void setTimeFinished(String timeFinished) {
		TimeFinished = timeFinished;
	}
	public String getSigned() {
		return Signed;
	}
	public void setSigned(String signed) {
		Signed = signed;
	}
	public String getBefore() {
		return Before;
	}
	public void setBefore(String before) {
		Before = before;
	}
	public String getActionTaken() {
		return ActionTaken;
	}
	public void setActionTaken(String actionTaken) {
		ActionTaken = actionTaken;
	}
	public String getAfter() {
		return After;
	}
	public void setAfter(String after) {
		After = after;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDateSigned() {
		return DateSigned;
	}
	public void setDateSigned(Date dateSigned) {
		DateSigned = dateSigned;
	}
	
	

}
