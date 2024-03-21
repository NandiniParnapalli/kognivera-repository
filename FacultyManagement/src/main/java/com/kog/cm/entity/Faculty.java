package com.kog.cm.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="faculty")
@Data
public class Faculty {

	       @Id
	       @GeneratedValue(strategy=GenerationType.IDENTITY)
           private Integer facultyId;
           private String facultyName;
           private String subject;
           
           

}
