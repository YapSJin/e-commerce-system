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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zhon12345
 */
@Entity
@Table(name = "REVIEWS")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Reviews.findAll", query = "SELECT r FROM Reviews r"),
	@NamedQuery(name = "Reviews.findById", query = "SELECT r FROM Reviews r WHERE r.id = :id"),
	@NamedQuery(name = "Reviews.findByRating", query = "SELECT r FROM Reviews r WHERE r.rating = :rating"),
	@NamedQuery(name = "Reviews.findByReview", query = "SELECT r FROM Reviews r WHERE r.review = :review"),
	@NamedQuery(name = "Reviews.findByReviewDate", query = "SELECT r FROM Reviews r WHERE r.reviewDate = :reviewDate"),
	@NamedQuery(name = "Reviews.findByIsArchived", query = "SELECT r FROM Reviews r WHERE r.isArchived = :isArchived")})
public class Reviews implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID")
	private Integer id;
	@Column(name = "RATING")
	private Integer rating;
	@Column(name = "REVIEW")
	private String review;
	@Column(name = "REVIEW_DATE")
  @Temporal(TemporalType.TIMESTAMP)
	private Date reviewDate;
	@Column(name = "IS_ARCHIVED", insertable = false)
	private Boolean isArchived;
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
  @ManyToOne
	private Products productId;
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")
  @ManyToOne
	private Users userId;

	public Reviews() {
	}

	public Reviews(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public Boolean getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}

	public Products getProductId() {
		return productId;
	}

	public void setProductId(Products productId) {
		this.productId = productId;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
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
		if (!(object instanceof Reviews)) {
			return false;
		}
		Reviews other = (Reviews) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Model.Reviews[ id=" + id + " ]";
	}

}
