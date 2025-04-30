/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author zhon12345
 */
@Entity
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
	@NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
	@NamedQuery(name = "Users.findByAvatar", query = "SELECT u FROM Users u WHERE u.avatar = :avatar"),
	@NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
	@NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
	@NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
	@NamedQuery(name = "Users.findByContact", query = "SELECT u FROM Users u WHERE u.contact = :contact"),
	@NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
	@NamedQuery(name = "Users.findByRole", query = "SELECT u FROM Users u WHERE u.role = :role"),
	@NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt"),
	@NamedQuery(name = "Users.findByIsArchived", query = "SELECT u FROM Users u WHERE u.isArchived = :isArchived")})
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID")
	private Integer id;
	@Column(name = "AVATAR")
	private String avatar;
	@Basic(optional = false)
  @Column(name = "USERNAME")
	private String username;
	@Column(name = "NAME")
	private String name;
	@Basic(optional = false)
  @Column(name = "EMAIL")
	private String email;
	@Column(name = "CONTACT")
	private String contact;
	@Basic(optional = false)
  @Column(name = "PASSWORD")
	private String password;
	@Basic(optional = false)
  @Column(name = "ROLE")
	private String role;
	@Column(name = "CREATED_AT", insertable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "IS_ARCHIVED", insertable = false)
	private Boolean isArchived;
	@OneToMany(mappedBy = "userId")
	private List<Reviews> reviewsList;
	@OneToMany(mappedBy = "generatedById")
	private List<Reports> reportsList;
	@OneToMany(mappedBy = "userId")
	private List<Orders> ordersList;
	@OneToMany(mappedBy = "userId")
	private List<Addresses> addressesList;
	@OneToMany(mappedBy = "userId")
	private List<Cardinfo> cardinfoList;
	@OneToMany(mappedBy = "userId")
	private List<Cart> cartList;

	public Users() {
	}

	public Users(Integer id) {
		this.id = id;
	}

	public Users(String username, String email, String password, String role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}

	@XmlTransient
	public List<Reviews> getReviewsList() {
		return reviewsList;
	}

	public void setReviewsList(List<Reviews> reviewsList) {
		this.reviewsList = reviewsList;
	}

	@XmlTransient
	public List<Reports> getReportsList() {
		return reportsList;
	}

	public void setReportsList(List<Reports> reportsList) {
		this.reportsList = reportsList;
	}

	@XmlTransient
	public List<Orders> getOrdersList() {
		return ordersList;
	}

	public void setOrdersList(List<Orders> ordersList) {
		this.ordersList = ordersList;
	}

	@XmlTransient
	public List<Addresses> getAddressesList() {
		return addressesList;
	}

	public void setAddressesList(List<Addresses> addressesList) {
		this.addressesList = addressesList;
	}

	@XmlTransient
	public List<Cardinfo> getCardinfoList() {
		return cardinfoList;
	}

	public void setCardinfoList(List<Cardinfo> cardinfoList) {
		this.cardinfoList = cardinfoList;
	}

	@XmlTransient
	public List<Cart> getCartList() {
		return cartList;
	}

	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Users)) {
			return false;
		}
		Users other = (Users) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Model.Users[ id=" + id + " ]";
	}

}
