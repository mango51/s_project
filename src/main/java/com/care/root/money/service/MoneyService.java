package com.care.root.money.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;

public interface MoneyService {
	public void showMoney(Model model,HttpSession session,String a);
	public void sendMoney(Model model,HttpSession session,String a);
	//public void sendingMoney(String personToSend,String amount,String loginUser, Model model);
	public void sendingMoney(String amount,String name,String id,String personToSendConfirm,String personToSend, Model model);
	public String confirm(String personToSend, Model model);
}
