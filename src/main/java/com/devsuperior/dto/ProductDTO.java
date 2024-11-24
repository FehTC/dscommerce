package com.devsuperior.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.devsuperior.Entitie.Category;
import com.devsuperior.Entitie.Product;

public class ProductDTO {
	
	// Atributos copiados do objeto Product
	private Long id;
	
	@NotBlank(message ="Campo requerido")
	@Size(min = 3,max=80,message="Nome precisa ter de 3 a 80 caracteres")
	private String name;
	
	@NotBlank(message ="Campo requerido")
	@Size(min = 10,message="Descrição precisa ter no mínimo 10 caracteres")
	private String description;
	
	@NotNull(message = "Campo requerido")
	@Positive(message="O preço deve ser positivo")
	private Double price;
	
	private String imgUrl;
	
	
	@NotEmpty(message = "Deve ter pelo menos uma categoria") // Essa anotation indica que o campo de categoria não
	private List<CategoryDTO> categories = new ArrayList<>();// pode ser vazio.
	
	

	public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}
	
	public ProductDTO(Product entity) {
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		price = entity.getPrice();
		imgUrl = entity.getImgUrl();
		for(Category cat: entity.getCategories()) {
			categories.add(new CategoryDTO(cat));
		}
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}
	
	public List<CategoryDTO> getCategories() {
		return categories;
	}
	
	
	
}