package com.APIsMocker.mockserver.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "APIsMocker")
public class HttpRoundTrip {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    
    @Column(columnDefinition = "TEXT")
    String request;
    @Column(columnDefinition = "TEXT")
    String response;

    String path;
}
