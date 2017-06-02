package com.fsmflying.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsmflying.http.HttpJsonResult;
import com.fsmflying.sys.service.ISequenceService;

@RestController
@RequestMapping("/sequence")
public class SequenceController {

	@Resource
	ISequenceService sequenceService;

	@JsonView
	@RequestMapping("/getOne")
	public HttpJsonResult getSequence(@RequestParam("keyName") String keyName) {
		HttpJsonResult httpJsonResult = new HttpJsonResult();

		httpJsonResult.setData(new HashMap<String, Object>());
		Object data = sequenceService.generateNextId(keyName);

		if (data != null) {
			httpJsonResult.setResult(1);
			httpJsonResult.getData().put("sequence", data);
		}

		return httpJsonResult;

	}

	@JsonView
	@RequestMapping("/getOne/{generateCount}")
	public HttpJsonResult getSequence(@RequestParam("keyName") String keyName,
			@PathVariable("generateCount") int generateCount) {
		HttpJsonResult httpJsonResult = new HttpJsonResult();
		httpJsonResult.setMessage("/getOne/multiple/" + generateCount);
		httpJsonResult.setData(new HashMap<String, Object>());
		Object data = sequenceService.generateNextId(keyName);
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (generateCount > 0) {
			for (int i = 0; i < generateCount; i++) {
				list.add(sequenceService.generateNextId(keyName));
			}
		}

		if (data != null) {
			httpJsonResult.setResult(1);
			httpJsonResult.getData().put("sequences", list);
		}

		return httpJsonResult;

	}

	@JsonView
	@RequestMapping("/getMultiple/{generateCount}")
	public HttpJsonResult getMultipleSequence(@RequestParam("keyName") String keyName,
			@PathVariable("generateCount") int generateCount) {
		HttpJsonResult httpJsonResult = new HttpJsonResult();
		httpJsonResult.setData(new HashMap<String, Object>());
		httpJsonResult.setMessage("/getMultiple/" + generateCount);
		Object data = sequenceService.generateNextId(keyName);
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (generateCount > 0) {
			int[] array = sequenceService.generateNextIds(generateCount, keyName);
			for (int i = 0; i < array.length; i++) {
				list.add(array[i]);
			}
		}
		if (data != null) {
			httpJsonResult.setResult(1);
			httpJsonResult.getData().put("sequences", list);
		}
		return httpJsonResult;
	}
}
