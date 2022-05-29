package com.example.TaskApp.service.impl;

import com.example.TaskApp.dto.WorkDTO;
import com.example.TaskApp.entity.Task;
import com.example.TaskApp.entity.Work;
import com.example.TaskApp.model.FileModel;
import com.example.TaskApp.repository.WorkRepository;
import com.example.TaskApp.service.TaskService;
import com.example.TaskApp.service.WorkService;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    WorkRepository workRepository;

    @Autowired
		TaskService taskService;

    @Override
    public Work get(Long id) {
        return workRepository.findById(id).get();
    }

    @Override
    public WorkDTO save(WorkDTO workDto) {
    	Work work = new Work();
			WorkDTO workResponse = new WorkDTO();
			BeanUtils.copyProperties(workDto,work);
    	work.setTask(taskService.get(workDto.getTask()));
    	work = workRepository.save(work);
    	BeanUtils.copyProperties(work,workResponse);
    	workResponse.setTask(work.getTask().getId());
    	return workResponse;
    }

    @Override
    public Work update(Work Work) {
        return workRepository.save(Work);
    }

    @Override
    public void delete(Long id) {
        workRepository.deleteById(id);
    }


    public FileModel generateFile(Long userId,String fileName){
			FileModel fileModel = new FileModel();
    	List<Task> tasks = taskService.allTasksForAUserForFile(userId);
			XSSFWorkbook workbook  = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("tasks");
			sheet.createRow(0).createCell(0).setCellValue("Task");
			sheet.createRow(1).createCell(0).setCellValue("Project");
			sheet.createRow(2).createCell(0).setCellValue("Client");
			sheet.createRow(3).createCell(0).setCellValue("Description");
			sheet.createRow(4).createCell(0).setCellValue("Start date");
			sheet.createRow(5).createCell(0).setCellValue("End date");
			sheet.createRow(6).createCell(0).setCellValue("Status");
			int i=1;
			int j=1;
			for(Task task: tasks){
				i=0;
				sheet.getRow(i++).createCell(j).setCellValue(task.getTitle());
				sheet.getRow(i++).createCell(j).setCellValue(task.getDescription());
				sheet.getRow(i++).createCell(j).setCellValue(task.getProject().getProjectName());
				sheet.getRow(i++).createCell(j).setCellValue(task.getClient().getClientName());
				sheet.getRow(i++).createCell(j).setCellValue(task.getStartDate().toString().split("\\s")[0]);
				sheet.getRow(i++).createCell(j).setCellValue(task.getEndDate().toString().split("\\s")[0]);
				sheet.getRow(i).createCell(j).setCellValue(task.getTaskStatus()?"Not Completed":"Completed");
				j++;
			}

			sheet = workbook.createSheet("workLog");
			int workRow = 0;
			while (workRow<tasks.size()*7){
				sheet.createRow(workRow+0).createCell(0).setCellValue("Task title");
				sheet.createRow(workRow+1).createCell(0).setCellValue("From time");
				sheet.createRow(workRow+2).createCell(0).setCellValue("To time");
				sheet.createRow(workRow+3).createCell(0).setCellValue("People");
				sheet.createRow(workRow+4).createCell(0).setCellValue("Place");
				sheet.createRow(workRow+5).createCell(0).setCellValue("Nature of work");
				workRow+=7;
			}
			i=0;
			j=1;

			for(Task task:tasks){
				j=1;
				sheet.getRow(i).createCell(j).setCellValue(task.getTitle());
				if (null != task.getWorks()) {
					for (Work work : task.getWorks()) {
						sheet.getRow(i + 1).createCell(j).setCellValue(work.getWorkStartTime());
						sheet.getRow(i + 2).createCell(j).setCellValue(work.getWorkEndTime());
						sheet.getRow(i + 3).createCell(j).setCellValue(work.getPeopleAtWork());
						sheet.getRow(i + 4).createCell(j).setCellValue(work.getPlaceOfWork());
						sheet.getRow(i + 5).createCell(j).setCellValue(work.getNatureOfWork());
						j++;
					}
					i+=7;
				}

			}
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				workbook.write(baos);
				baos.close();
				fileModel.setData(baos.toByteArray());
				fileModel.setName(fileName+".xlsx");
				fileModel.setMimeType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			}catch (Exception e){
				e.printStackTrace();
			}
			return fileModel;

		}
}
