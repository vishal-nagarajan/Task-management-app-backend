package com.example.TaskApp.controller;

import com.example.TaskApp.dto.WorkDTO;
import com.example.TaskApp.entity.Work;
import com.example.TaskApp.model.FileModel;
import com.example.TaskApp.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


@RestController
@RequestMapping("/work")
@CrossOrigin
public class WorkController {


	@Autowired
	WorkService workService;

	@PostMapping
	public WorkDTO save(@RequestBody WorkDTO workDTO) {
		return workService.save(workDTO);
	}


	@GetMapping("/generate")
	public ResponseEntity<byte[]> generate(@RequestHeader("userId") Long userId,@RequestHeader("fileName") String fileName){
		FileModel responseObject = workService.generateFile(userId, fileName);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(
			MediaType.parseMediaType(responseObject.getMimeType()));
		httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION,
			"inline; filename=" + responseObject.getName());
		httpHeaders
			.setAccessControlExposeHeaders(Collections.singletonList(HttpHeaders.CONTENT_DISPOSITION));
		return new ResponseEntity<>(responseObject.getData(),httpHeaders,HttpStatus.ACCEPTED);
	}
}
