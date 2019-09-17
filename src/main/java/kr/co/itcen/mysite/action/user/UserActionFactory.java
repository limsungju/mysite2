package kr.co.itcen.mysite.action.user;

import kr.co.itcen.mysite.action.main.MainAction;
import kr.co.itcen.web.mvc.Action;
import kr.co.itcen.web.mvc.ActionFactory;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		//
		// actionName "JoinForm"
		// actionName + "Action" -> 객체를 생성하는 코드
		// String으로 된 클래스이름으로 동적객체 생성
		// 
		Action action = null;
		
		if("joinform".equals(actionName)) {
			action = new JoinFormAction();
			
		} else if ("joinsuccess".equals(action)) {
			action = new JoinsuccessAction();
			
		} else {
			action = new MainAction();
		}
		
		return action;
	}

}
