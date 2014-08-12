package org.activiti.designer.test;

import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-test.xml")
public class ProcessTestMyProcess {

//	private String filename = "E:/msl_develp/work_spaces/kft-activiti-demo/src/main/resources/diagrams/ol_approve/online_complaint.bpmn";
	private String filename = "src/main/resources/diagrams/ol_approve/online_complaint.bpmn";
	
	@Autowired
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

	@Test
	public void deploy() throws FileNotFoundException{
		RepositoryService repositoryService = activitiRule.getRepositoryService();
		Deployment deploy = repositoryService.createDeployment().addInputStream("online_complaint.bpmn",
				new FileInputStream(filename)).deploy();
		Assert.assertNotNull(deploy);
		Assert.assertNotNull(deploy.getId());
	}
	
	@Test
	public void startProcess() {
		/*FormService formService = activitiRule.getFormService();
		Map<String, String> properties = null;
		String processDefinitionId = null;
		formService.submitStartFormData(processDefinitionId, properties);*/
		
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("var_name", "tom check");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("complain_process", variableMap);
		Assert.assertNotNull(processInstance.getId());
		System.out.println("id " + processInstance.getId() + " "
				+ processInstance.getProcessDefinitionId());
	}
	
	@Test
	public void completeTask(){
		TaskService taskService = activitiRule.getTaskService();
		List<Task> list = taskService.createTaskQuery().taskAssignee("deptLeader").list();
		Task task = list.get(0);
		Assert.assertEquals("resource_audit", task.getName());
		String taskId = task.getId();
		Map<String,Object> para = new HashMap<String,Object>();
		para.put("approve", false);
		taskService.complete(taskId,para);
	}
}