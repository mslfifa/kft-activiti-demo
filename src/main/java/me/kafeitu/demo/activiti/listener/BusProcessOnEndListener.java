package me.kafeitu.demo.activiti.listener;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;

/**
 * @author 蒙少立  作者 E-mail:mslfifa@163.com
 * @version 创建时间：2014年8月12日 下午4:32:14
 * 
 */

public class BusProcessOnEndListener implements org.activiti.engine.delegate.ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("@@@@@@@ activtiId:"+execution.getCurrentActivityId()
				+"|getCurrentActivityName:"+execution.getCurrentActivityName()
				+"|getProcessDefinitionId:"+execution.getProcessDefinitionId()
				+"|getProcessInstanceId:"+execution.getProcessInstanceId());
		
		Map<String, Object> map = execution.getVariables();
		if(map!=null && !map.isEmpty()){
			Set<String> keys = map.keySet();
			for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
				String varName = (String) iterator.next();
				System.out.println("###### varName["+varName+"]->value:"+map.get(varName));
			}		
		}
	}

	/*@Override
	public void notify(DelegateTask delegateTask) {
		String pid = delegateTask.getProcessInstanceId();
		delegateTask.getVariable("var_drafter_id");
		
		Map<String, Object> map = delegateTask.getVariables();
		if(map!=null && !map.isEmpty()){
			Set<String> keys = map.keySet();
			for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
				String varName = (String) iterator.next();
				System.out.println("@@@@@@ varName["+varName+"]->value:"+map.get(varName));
			}		
		}
		
		
		
	}*/
	
	

}
