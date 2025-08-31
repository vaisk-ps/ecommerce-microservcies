package com.marketplace.platform.user.core.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "platform_users")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"addresses", "credential"})
@Data
@Builder
public final class User extends AbstractMappedEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, updatable = false)
	private Integer id;
	
	@Column(name = "given_name")
	private String givenName;
	
	@Column(name = "family_name")
	private String familyName;
	
	@Column(name = "profile_picture_url")
	private String profilePictureUrl;
	
	@Email(message = "*Input must be in Email format!**")
	private String email;
	
	@Column(name = "contact_number")
	private String contactNumber;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "platformUser", fetch = FetchType.LAZY)
	private Set<UserAddress> userAddresses;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "platformUser")
	private UserCredential userCredential;
	
}










