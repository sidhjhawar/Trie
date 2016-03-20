package com.mapquest.interview.encoding;

import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EncodingController {

	@RequestMapping(value = "/terms", method = RequestMethod.GET)
	public List<Object> getAll() {
		return Lists.newArrayList();
	}

}
