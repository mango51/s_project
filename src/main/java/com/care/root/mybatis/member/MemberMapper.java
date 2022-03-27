package com.care.root.mybatis.member;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.ui.Model;

import com.care.root.member.dto.MemberDTO;

public interface MemberMapper {
	public MemberDTO getMember(String id);
	public ArrayList<MemberDTO> memberInfo();
	public int register(MemberDTO dto);
	public void keepLogin(Map<String, Object> map);
	public MemberDTO getUserSession(String sessionID);
	public MemberDTO showMoney(String id);
	public MemberDTO showName(String id);
	public MemberDTO personToSendConfirm(String id);
	public void sendingMoney(Map<String, Object> map);
	public void sendMoneyToPerson(String id, Model model);
}
