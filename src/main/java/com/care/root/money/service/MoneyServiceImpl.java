package com.care.root.money.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.care.root.member.dto.MemberDTO;
import com.care.root.mybatis.member.MemberMapper;

@Service
public class MoneyServiceImpl implements MoneyService{
	@Autowired MemberMapper mapper;
	public void showMoney(Model model,HttpSession session,String a) {
		
		model.addAttribute("loginUser",a);
		
		String b= mapper.showName(a).getName();
		model.addAttribute("loginName",b);
		
		model.addAttribute("money",mapper.showMoney(a).getMoney());
		
		model.addAttribute("showMoneyList", mapper.memberInfo());
	}
	
public void sendMoney(Model model,HttpSession session,String a) {
		
		model.addAttribute("loginUser",a);
		
		String b= mapper.showName(a).getName();
		model.addAttribute("loginName",b);
		
		model.addAttribute("money",mapper.showMoney(a).getMoney());
		
		model.addAttribute("showMoneyList", mapper.memberInfo());
	}



//public void sendingMoney(String personToSend, String amount,String id, Model model) {
public void sendingMoney(String amount,String name,String id,String personToSendConfirm,String personToSend, Model model) {
	Map<String, Object> map = new HashMap<String, Object>(); // 돈 보내는 사람용 (보내는 사람 이름, 잔액) >> 쿼리 업데이트용
	// mybatis (mapper)에서는 따로 따로 인수 2개 이상 받아올 수 없으니 Map을 통해 묶어서 가져오기!
	
	//model.addAttribute("id",id);
	
	//보내는 사람 (발송인) 보낼 돈 빼서 잔액 업데이트 시키기
	int money = mapper.showMoney(id).getMoney();
	money -= Integer.parseInt(amount);
	//model.addAttribute("money",money);
	System.out.println(money);
	//model.addAttribute("sendingMoney",mapper.sendingMoney(id,money));
	//mapper.sendingMoney(money,id);
	
	//map 안에 넣어서 mapper로 가지고 가기 > 쿼리문 실행
	map.put("money", money);
	map.put("id", id);
	mapper.sendingMoney(map); //발신인 (돈 보내는 사람) 잔액 업데이트
	
	
	
	
	Map<String, Object> map2 = new HashMap<String, Object>(); //돈 받는 사람용 (받는 사람 이름, 잔액) >> 쿼리 업데이트용
	
	//받는 사람 (수신인) 받은 돈 넣어서 잔액 업데이트 시키기
	int getMoney = mapper.showMoney(personToSend).getMoney();
	getMoney += Integer.parseInt(amount); // 돈 받아서 총 금액 (받은 돈 이체 후 총 잔액)
	System.out.println(getMoney);

	map2.put("money", getMoney);
	map2.put("id",personToSend);
	mapper.sendingMoney(map2);
	
	
	
	
	//model.addAttribute("money2",mapper.afterSendingMoney(id));
	model.addAttribute("loginUser",id);
	model.addAttribute("loginName",name);
	model.addAttribute("money",mapper.showMoney(id).getMoney());
	model.addAttribute("personToSendConfirm",personToSendConfirm);
	//model.addAttribute("personToSend",personToSend);
	//model.addAttribute("personToSendConfirm",mapper.personToSendConfirm(personToSend));
}


public String confirm(String id, Model model) {
	//model.addAttribute("loginUser",);
	//model.addAttribute("loginName",); 위에꺼 바뀌지 않게 해보기
	if((mapper.personToSendConfirm(id))==null) { 
		return "0";
	}
	
	//model.addAttribute("personToSendConfirm",mapper.personToSendConfirm(id).getName());
	String answer= mapper.personToSendConfirm(id).getName();
	System.out.println(answer);
	return answer; //실행 ㄱ
	
}

//이제 해야할 것!
// 1. confirm으로 페이지 보내기 때문에 내용 다 사라짐... ㅠ (인수로 값들 다 가져올까?)
// 2. 이제 confirm으로 받아온 사용자 (sendMoney.jsp에서는 ${personToSendConfirm })로 계좌이체하기




}
