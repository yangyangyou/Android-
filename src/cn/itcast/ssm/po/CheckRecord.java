package cn.itcast.ssm.po;

public class CheckRecord {
  private int record_id;
  private int check_id; //点检号
  private String check_content; //点检内容
  private String results_1; //点检结果
  private String time_1; //点检时间
  private String remarks_1; //点检备注
  private String results_2; //点检结果
  private String time_2; //点检时间
  private String remarks_2; //点检备注
  private String results_3; //点检结果
  private String time_3; //点检时间
  private String remarks_3; //点检备注
  private String results_4; //点检结果
  private String time_4; //点检时间
  private String remarks_4; //点检备注
  private String results_5; //点检结果
  private String time_5; //点检时间
  private String remarks_5; //点检备注
  private String results_6; //点检结果
  private String time_6; //点检时间
  private String remarks_6; //点检备注
  private String results_7; //点检结果
  private String time_7; //点检时间
  private String remarks_7; //点检备注
  private String results_8; //点检结果
  private String time_8; //点检时间
  private String remarks_8; //点检备注
  
public int getRecord_id() {
	return record_id;
}
public void setRecord_id(int record_id) {
	this.record_id = record_id;
}
public int getCheck_id() {
	return check_id;
}
public void setCheck_id(int check_id) {
	this.check_id = check_id;
}
public String getCheck_content() {
	return check_content;
}
public void setCheck_content(String check_content) {
	this.check_content = check_content;
}
public String getResults_1() {
	return results_1;
}
public void setResults_1(String results_1) {
	this.results_1 = results_1;
}
public String getTime_1() {
	return time_1;
}
public void setTime_1(String time_1) {
	this.time_1 = time_1;
}
public String getRemarks_1() {
	return remarks_1;
}
public void setRemarks_1(String remarks_1) {
	this.remarks_1 = remarks_1;
}
public String getResults_2() {
	return results_2;
}
public void setResults_2(String results_2) {
	this.results_2 = results_2;
}
public String getTime_2() {
	return time_2;
}
public void setTime_2(String time_2) {
	this.time_2 = time_2;
}
public String getRemarks_2() {
	return remarks_2;
}
public void setRemarks_2(String remarks_2) {
	this.remarks_2 = remarks_2;
}
public String getResults_3() {
	return results_3;
}
public void setResults_3(String results_3) {
	this.results_3 = results_3;
}
public String getTime_3() {
	return time_3;
}
public void setTime_3(String time_3) {
	this.time_3 = time_3;
}
public String getRemarks_3() {
	return remarks_3;
}
public void setRemarks_3(String remarks_3) {
	this.remarks_3 = remarks_3;
}
public String getResults_4() {
	return results_4;
}
public void setResults_4(String results_4) {
	this.results_4 = results_4;
}
public String getTime_4() {
	return time_4;
}
public void setTime_4(String time_4) {
	this.time_4 = time_4;
}
public String getRemarks_4() {
	return remarks_4;
}
public void setRemarks_4(String remarks_4) {
	this.remarks_4 = remarks_4;
}
public String getResults_5() {
	return results_5;
}
public void setResults_5(String results_5) {
	this.results_5 = results_5;
}
public String getTime_5() {
	return time_5;
}
public void setTime_5(String time_5) {
	this.time_5 = time_5;
}
public String getRemarks_5() {
	return remarks_5;
}
public void setRemarks_5(String remarks_5) {
	this.remarks_5 = remarks_5;
}
public String getResults_6() {
	return results_6;
}
public void setResults_6(String results_6) {
	this.results_6 = results_6;
}
public String getTime_6() {
	return time_6;
}
public void setTime_6(String time_6) {
	this.time_6 = time_6;
}
public String getRemarks_6() {
	return remarks_6;
}
public void setRemarks_6(String remarks_6) {
	this.remarks_6 = remarks_6;
}
public String getResults_7() {
	return results_7;
}
public void setResults_7(String results_7) {
	this.results_7 = results_7;
}
public String getTime_7() {
	return time_7;
}
public void setTime_7(String time_7) {
	this.time_7 = time_7;
}
public String getRemarks_7() {
	return remarks_7;
}
public void setRemarks_7(String remarks_7) {
	this.remarks_7 = remarks_7;
}
public String getResults_8() {
	return results_8;
}
public void setResults_8(String results_8) {
	this.results_8 = results_8;
}
public String getTime_8() {
	return time_8;
}
public void setTime_8(String time_8) {
	this.time_8 = time_8;
}
public String getRemarks_8() {
	return remarks_8;
}
public void setRemarks_8(String remarks_8) {
	this.remarks_8 = remarks_8;
}
public int getWhereResults() {
	int  a=0;
	Result:for(int i=0;i<8;i++) {
		switch (i) {
		case 0:
		    if(this.getResults_1()==null) {
		    	a=1;
		    	break Result;
		    }
			break;
		case 1:
			if(this.getResults_2()==null) {
		    	a=2;
		    	break Result;
		    }
			break;
		case 2:
			if(this.getResults_3()==null) {
		    	a=3;
		    	break Result;
		    }
			break;
		case 3:
			if(this.getResults_4()==null) {
		    	a=4;
		    	break Result;
		    }
			break;
		case 4:
			if(this.getResults_5()==null) {
		    	a=5;
		    	break Result;
		    }
			break;
		case 5:
			if(this.getResults_6()==null) {
		    	a=6;
		    	break Result;
		    }
			break;
		case 6:
			if(this.getResults_7()==null) {
		    	a=7;
		    	break Result;
		    }
			break;
		case 7:
			if(this.getResults_8()==null) {
		    	a=8;
		    	break Result;
		    }
			break;
		default:
			break;
		}
	}
	return a;
}
}
