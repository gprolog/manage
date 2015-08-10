package com.oneapm.web.count;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.oneapm.dto.Count.CountDto;
import com.oneapm.service.count.CountService;
import com.oneapm.web.SupportAction;

public class CountShowAction extends SupportAction{
	private static final long serialVersionUID = 1L;

    protected static final Logger LOG = LoggerFactory.getLogger(CountShowAction.class);
    private List<CountDto> countDto;
    private String name;
    private String event;
    private String email;
    private String labelid;
    private int number;
    
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLabelid() {
		return labelid;
	}
	public void setLabelid(String labelid) {
		this.labelid = labelid;
	}
	public List<CountDto> getCountDto() {
		return countDto;
	}
	public void setCountDto(List<CountDto> countDto) {
		this.countDto = countDto;
	}
	public String index(){
		return "countindex";
	}
	 public String insert() {
         if (!isLogin()) {
                 return "login";
         }
         try {
        	long l = 0l;
        	CountDto dto = new CountDto(this.email, this.number, l, this.labelid, this.event);
        	CountService.insertCountDto(dto);
                
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
         }
         return "success";
 }
    
}
