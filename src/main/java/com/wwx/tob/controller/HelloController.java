package com.wwx.tob.controller;

import com.wwx.tob.dao.ClientMapper;
import com.wwx.tob.dto.ResponseDto;
import com.wwx.tob.pojo.ClientPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController extends AbstractController {
	
	@Autowired
	private ClientMapper clientMapper;
	
	@GetMapping("/testGet")
	public ResponseDto testGet() {
		return new ResponseDto().setData(this.clientMapper.findByClientId(1));
	}
	
	@GetMapping("/testSave")
	// 因为是insert操作，所以每次执行前看一下数据库是否存在这样的数据，存在则删除
	public ResponseDto testSave() {
		return new ResponseDto().setData(this.clientMapper.saveClient(
			new ClientPojo().setClientID(1).setChannelID(2).setStatus("3").setScore(0)
		));
	}
}
