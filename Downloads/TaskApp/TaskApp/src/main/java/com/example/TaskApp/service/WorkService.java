package com.example.TaskApp.service;

import com.example.TaskApp.dto.WorkDTO;
import com.example.TaskApp.entity.Work;
import com.example.TaskApp.model.FileModel;

public interface WorkService {
    public Work get(Long id);
		WorkDTO save(WorkDTO workDto);
    public Work update(Work Work);
    public void delete(Long id);
	FileModel generateFile(Long userId,String fileName);
}
