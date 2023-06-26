package com.masai.Model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
	@NotNull
	private String title;
	@Length(min = 3)
	@NotNull
	private String author;
	private String ISBN;
	private String description;
	@Min(100)
	private Integer price;
	private LocalDate publicationDate;
	private Integer availabilityStatus = 1;
}

/*
Each book should have attributes such as title, author, ISBN,
description, price, publication date, and availability status.
*/