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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author zhon12345
 */
@Entity
@Table(name = "PRODUCTS")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
	@NamedQuery(name = "Products.findById", query = "SELECT p FROM Products p WHERE p.id = :id"),
	@NamedQuery(name = "Products.findByName", query = "SELECT p FROM Products p WHERE p.name = :name"),
	@NamedQuery(name = "Products.findByDescription", query = "SELECT p FROM Products p WHERE p.description = :description"),
	@NamedQuery(name = "Products.findByPrice", query = "SELECT p FROM Products p WHERE p.price = :price"),
	@NamedQuery(name = "Products.findByStock", query = "SELECT p FROM Products p WHERE p.stock = :stock"),
	@NamedQuery(name = "Products.findByCreatedAt", query = "SELECT p FROM Products p WHERE p.createdAt = :createdAt"),
	@NamedQuery(name = "Products.findByIsArchived", query = "SELECT p FROM Products p WHERE p.isArchived = :isArchived")})
public class Products implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
  @Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Basic(optional = false)
  @Column(name = "PRICE")
	private BigDecimal price;
	@Basic(optional = false)
  @Column(name = "STOCK")
	private int stock;
	@Column(name = "CREATED_AT", insertable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "IS_ARCHIVED", insertable = false)
	private Boolean isArchived;
	@OneToMany(mappedBy = "productId")
	private List<Reviews> reviewsList;
	@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
  @ManyToOne
	private Categories categoryId;
	@OneToMany(mappedBy = "productId")
	private List<Orderdetails> orderdetailsList;
	@OneToMany(mappedBy = "productId")
	private List<Cart> cartList;

	public Products() {
	}

	public Products(Integer id) {
		this.id = id;
	}

	public Products(Integer id, String name, BigDecimal price, int stock) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
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

	public Categories getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Categories categoryId) {
		this.categoryId = categoryId;
	}

	@XmlTransient
	public List<Orderdetails> getOrderdetailsList() {
		return orderdetailsList;
	}

	public void setOrderdetailsList(List<Orderdetails> orderdetailsList) {
		this.orderdetailsList = orderdetailsList;
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
		if (!(object instanceof Products)) {
			return false;
		}
		Products other = (Products) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Model.Products[ id=" + id + " ]";
	}

}
