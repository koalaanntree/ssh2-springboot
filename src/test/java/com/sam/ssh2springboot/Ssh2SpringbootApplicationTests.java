package com.sam.ssh2springboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Ssh2SpringbootApplicationTests {

	//    获取上下文对象
	@Autowired
	private WebApplicationContext wac;

	//    拿一个模拟http发射器
	private MockMvc mockMvc;

	@Before
	public void init() {
		//构建
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void monitorTest() throws Exception {
		String result = mockMvc.perform(post("/monitor")
				.param("query", "top -bn1|grep -w java")
//				.param("query", "ifconfig")
				.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		log.info(result);
	}

}
