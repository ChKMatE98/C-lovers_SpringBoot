package com.clovers.controllers;

import java.io.File;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.clovers.commons.EncryptionUtils;
import com.clovers.dto.MemberDTO;
import com.clovers.dto.WorkConditionDTO;
import com.clovers.services.HumanResourcesService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/humanResources")
public class HumanResourcesController {
	
	//인사 컨트롤러
	@Autowired
	private HttpSession session;
	
	@Autowired 
	private HumanResourcesService hrservice;
	
	@RequestMapping("")
	public String main() {
		String title="인사";
		String currentMenu = "휴가/근무";
		session.setAttribute("title", title);
		session.setAttribute("currentMenu", currentMenu);
		return "humanresources/hrMain";
	}
	
	
	// view로 mypage에 필요한 정보 가져오기
	@RequestMapping("/mypage")
	public String select(Model model) {
		
		String id = (String)session.getAttribute("loginID");
		
//		String name = hrService.selectByIdGetName(id);
//		model.addAttribute("name", name);
		
		MemberDTO list = hrservice.selectById(id);
		model.addAttribute("list",list);
		
		return "humanresources/myPage";
	}
	
//	비밀번호 변경안했으면 변경하게..
	@ResponseBody
	@RequestMapping("/recommendChangPw")
	public String reChangePw(String id,String name) {
		System.out.println(id+" : "+name);
		String pw = hrservice.reChangePw(id);
		
		String enPw = EncryptionUtils.getSHA512(EncryptionUtils.kR_EnKeyboardConversion(name));
		
		if(pw.equals(enPw)) {
			return "변경추천";
		}
		return "변경완료";
	}
	
//	비밀번호 변경하는 페이지로 이동
	@RequestMapping("/goChangePw")
	public String goChangePw() {
		return "member/changePW";
	}
	
	
//	프로필 이미지,사내전화,휴대전화,개인이메일 정보 업데이트
	@RequestMapping("/update")
	public String update(MultipartFile profile_img,String company_phone,String phone,String email) throws Exception {
		
		String id = (String)session.getAttribute("loginID");
		
		System.out.println(profile_img);
		
		String path = "C:/C-lovers";
		
		File uploadPath = new File(path);
		
		if(!uploadPath.exists()) {
			uploadPath.mkdir();
		}
		
		String oriName = profile_img.getOriginalFilename();
		String sysName = UUID.randomUUID()+"_"+oriName;
		
		profile_img.transferTo(new File(uploadPath+"/"+sysName));
		
		hrservice.update(id,sysName,company_phone,phone,email);
		
		return "redirect:/humanResources/mypage";
	}
	
	// 사용자 지각 정보 불러오기
	@ResponseBody
	@RequestMapping("/selectLateInfo")
	public int selectLateInfo() {
		String id = (String)session.getAttribute("loginID");
		return hrservice.selectLateInfo(id);
	}
	
	// 사용자 조기퇴근 정보 불러오기
	@ResponseBody
	@RequestMapping("/selectEarlyLeaveInfo")
	public int selectEarlyLeaveInfo() {
		String id = (String)session.getAttribute("loginID");
		return hrservice.selectEarlyLeaveInfo(id);
	}
	
	// 사용자 퇴근 미체크 정보 불러오기
	@ResponseBody
	@RequestMapping("/selectNotCheckedLeaveInfo")
	public int selectNotCheckedLeaveInfo() {
		String id = (String)session.getAttribute("loginID");
		return hrservice.selectNotCheckedLeaveInfo(id);
	}
	
	// 사용자 결근 정보 불러오기
	@ResponseBody
	@RequestMapping("/selectAbsenteeismInfo")
	public int selectAbsenteeismInfo() {
		String id = (String)session.getAttribute("loginID");
		return hrservice.selectAbsenteeismInfo(id);
	}
	
	// 사용자 근무 시간 정보 불러오기
	@ResponseBody
	@RequestMapping("/selectWorkingDaysThisMonth")
	public List<Map<String, Timestamp>> selectWorkingDaysThisMonth(){
		String id = (String)session.getAttribute("loginID");
		return hrservice.selectWorkingDaysThisMonth(id);
	}
	
	// 사용자 근무 규칙 정보 불러오기
	@ResponseBody
	@RequestMapping("/selectEmployeeWorkRule")
	public Map<String, Object> selectEmployeeWorkRule() {
		String id = (String)session.getAttribute("loginID");
		return hrservice.selectEmployeeWorkRule(id);
	}
	
	// 출근 전인지 확인
	@ResponseBody
	@RequestMapping("/selectAttendStatus")
	public Map<String, Object> selectAttendStatus() {
		String id = (String)session.getAttribute("loginID");
		Map<String, Object> result = hrservice.selectAttendStatus(id);
		if(result != null) {
			return result;
		}else {
			return new HashMap<>();
		}
	}
	
	// 출근 기록 남기기
	@ResponseBody
	@RequestMapping("/insertAttendingWork")
	public int insertAttendingWork() {
		String id = (String)session.getAttribute("loginID");
		return hrservice.insertAttendingWork(id);
	}
	
	// 퇴근 기록 남기기
	@ResponseBody
	@RequestMapping("/updateLeavingWork")
	public int updateLeavingWork() {
		String id = (String)session.getAttribute("loginID");
		return hrservice.updateLeavingWork(id);
	}
	
	// 업무 상태 기록 남기기
	@ResponseBody
	@RequestMapping("/insertWorkCondition")
	public WorkConditionDTO insertWorkCondition(@RequestParam("status") String status) {
		String id = (String)session.getAttribute("loginID");
		return hrservice.insertWorkCondition(id, status);
	}
	
	// 업무 상태 리스트 불러오기
	@ResponseBody
	@RequestMapping("/selectWorkConditionsList")
	public List<WorkConditionDTO> selectWorkConditionsList(){
		String id = (String)session.getAttribute("loginID");
		return hrservice.selectWorkConditionsList(id);
	}
	
	// 휴가 신청 페이지로 이동
	@RequestMapping("/showVacationApp")
	public String showVacationApp() {
		String currentMenu = "";
		session.setAttribute("currentMenu", currentMenu);
		return "humanresources/vacationApplication";
	}
	
	// 휴가 사유 구분 불러오기
	@ResponseBody
	@RequestMapping("/selectRestReasonType")
	public List<String> selectRestReasonType() {
		return hrservice.selectRestReasonType();
	}
}
