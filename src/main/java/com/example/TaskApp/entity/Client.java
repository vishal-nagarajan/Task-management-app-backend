package com.example.TaskApp.entity;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String clientName;

    @OneToMany(mappedBy = "clientId")
    private List<Project> projects;

    @OneToMany(mappedBy = "client")
    private List<Task> tasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id" ,name = "user_id")
    private User user;
}
