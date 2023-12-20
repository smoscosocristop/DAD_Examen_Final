package com.SaavedraMoscoso.denunciaservice.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "denuncias")
@EntityListeners(AuditingEntityListener.class)
public class Denuncia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "dni", length = 8, nullable = false)
	private String dni;
	
	@Column(name = "fecha", nullable = false)
	private Date fecha;
	
	@Column(name = "titulo", length = 3, nullable = false)
	private String titulo;
	
	@Column(name = "direccion", length = 200, nullable = false)
	private String direccion;
	
	@Column(name = "descripcion", length = 255, nullable = true)
	private String descripcion;
	
	@Column(name = "created_at", nullable = false, updatable = false )
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	@Column(name = "activo", nullable = false)
	private Boolean activo;
	
}
