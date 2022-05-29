package com.example.TaskApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="appuser")
public class User {
    @Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String userName;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Client> clients;
	@JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;
	@JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Project> projects;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
