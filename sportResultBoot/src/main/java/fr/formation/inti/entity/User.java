package fr.formation.inti.entity;
// Generated 18 févr. 2022 é 08:19:10 by Hibernate Tools 3.6.0.Final

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "football")
public class User implements java.io.Serializable {

	private int idUser;
	private Role role;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).*$") 
	private String password;
	@NotNull
	@Email
	private String email;
	@NotBlank
	@Size (min=2 , max=30)
	private String prenom;
	@NotBlank
	@Size (min=2 , max=30)
	private String nom;
	private Date dateNaissance;

	public User() {
	}

	public User(int idUser, Role role) {
		this.idUser = idUser;
		this.role = role;
	}

	public User(int idUser, Role role, String password, String email, String prenom, String nom, Date dateNaissance) {
       this.idUser = idUser;
       this.role = role;
       this.password = password;
       this.email = email;
       this.prenom = prenom;
       this.nom = nom;
       this.dateNaissance = dateNaissance;
    }

	@Id

	@Column(name = "idUser", unique = true, nullable = false)
	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Role_idRole", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column(name = "password", length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="prénom", length=45)
    public String getPrenom() {
        return this.prenom;
    }

	public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

	@Column(name = "nom", length = 45)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DateNaissance", length = 10)
	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

}