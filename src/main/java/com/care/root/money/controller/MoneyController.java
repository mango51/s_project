package com.care.root.money.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.root.board.dto.BoardRepDTO;
import com.care.root.board.service.BoardFileService;
import com.care.root.board.service.BoardService;
import com.care.root.member.service.MemberService;
import com.care.root.money.service.MoneyService;
import com.care.root.session_name.MemberSessionName;


@Controller 
@RequestMapping("money")
public class MoneyController implements MemberSessionName{
	@Autowired MoneyService ms;
	
	@GetMapping("sendMoney") 
	public String sendMoney(Model model,HttpServletRequest req) { 
		HttpSession session = req.getSession();
		System.out.println(session.getAttribute(LOGIN));
		String a = (String)session.getAttribute(LOGIN);
		ms.sendMoney(model, session,a);
		return "money/sendMoney"; 
	}
	@PostMapping("sendingMoney")
	//public String sendingMoney(HttpServletResponse resp,@RequestParam("personToSend") String personToSend, @RequestParam("amount") String amount,@RequestParam("loginUser") String loginUser, Model model) throws Exception,NumberFormatException {
	public String sendingMoney(HttpServletResponse resp,@RequestParam("amount") String amount,@RequestParam("loginName") String loginName,@RequestParam("loginUser") String loginUser,@RequestParam("personToSendConfirm") String personToSendConfirm, 
			@RequestParam("personToSend") String personToSend, Model model) throws Exception,NumberFormatException {
		//이체하기 -- 여기서부터 시작!
		if(amount.isEmpty()) {
			resp.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = resp.getWriter(); 
			out.println("<script>alert('보낼 금액를 적어주세요!'); history.back();</script>"); 
			out.flush();
		}
		/*if(personToSend.isEmpty()) {
			resp.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = resp.getWriter(); 
			out.println("<script>alert('보낼 계좌를 적어주세요!'); history.back();</script>"); 
			out.flush();
		}*/
		//ms.sendingMoney(personToSend,amount, loginUser, model);
		if(personToSendConfirm.isEmpty()) {
			resp.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = resp.getWriter(); 
			out.println("<script>alert('보낼 사람 확인 필요!'); history.back();</script>"); 
			out.flush();
		}
		else{
			resp.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = resp.getWriter(); 
			out.println("<script>var res = confirm('"+personToSendConfirm
				+ "에게 " + amount
				+ "원 보내겠습니까?');"
				+ "if(res==false){ history.back();} else{alert('이체되었습니다.'); window.location.replace('../money/sendMoney');} </script>"); 
			ms.sendingMoney(amount, loginName,loginUser, personToSendConfirm,personToSend,model);
			out.flush();
		}
		
		return "money/sendMoney";
	}
	
	/*@GetMapping("confirm")
	public String confirm(HttpServletResponse resp,@RequestParam("id") String personToSend,Model model)throws Exception {
		int result = ms.confirm(personToSend, model); 
		if(result==0) {
			resp.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = resp.getWriter(); 
			out.println("<script>alert('계좌번호가 존재하지 않습니다!'); history.back();</script>"); 
			out.flush();
		}
		// return "redirect:sendMoney"; 다시 뒤로가기 처럼 된다 >> 예금주 확인 불가
		return "money/sendMoney";
	}*/
	
	@ResponseBody
	@RequestMapping(value="confirm.do",method=RequestMethod.GET,produces ="application/text; charset=utf8")
	public String confirmGet(String personToSend,Model model)throws Exception {
		String result = ms.confirm(personToSend, model); 
		/*if(result==0) {
			resp.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = resp.getWriter(); 
			out.println("<script>alert('계좌번호가 존재하지 않습니다!'); history.back();</script>"); 
			out.flush();
		}*/
		System.out.println(result);
		// return "redirect:sendMoney"; 다시 뒤로가기 처럼 된다 >> 예금주 확인 불가
		return result;
	}
	

	
	@GetMapping("showMoney") 
	public String showMoney(Model model,HttpServletRequest req) { 
		HttpSession session = req.getSession();
		System.out.println(session.getAttribute(LOGIN));
		String a = (String)session.getAttribute(LOGIN);
		ms.showMoney(model, session,a);
		return "money/showMoney"; 
	}
	
	
}
