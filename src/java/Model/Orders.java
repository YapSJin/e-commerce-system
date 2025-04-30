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
@Table(name = "ORDERS")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
	@NamedQuery(name = "Orders.findById", query = "SELECT o FROM Orders o WHERE o.id = :id"),
	@NamedQuery(name = "Orders.findByPaymentMethod", query = "SELECT o FROM Orders o WHERE o.paymentMethod = :paymentMethod"),
	@NamedQuery(name = "Orders.findByStatus", query = "SELECT o FROM Orders o WHERE o.status = :status"),
	@NamedQuery(name = "Orders.findByTotalPrice", query = "SELECT o FROM Orders o WHERE o.totalPrice = :totalPrice"),
	@NamedQuery(name = "Orders.findByDeliveryCost", query = "SELECT o FROM Orders o WHERE o.deliveryCost = :deliveryCost"),
	@NamedQuery(name = "Orders.findByDiscount", query = "SELECT o FROM Orders o WHERE o.discount = :discount"),
	@NamedQuery(name = "Orders.findByOrderDate", query = "SELECT o FROM Orders o WHERE o.orderDate = :orderDate")})
public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
  @Column(name = "PAYMENT_METHOD")
	private String paymentMethod;
	@Basic(optional = false)
  @Column(name = "STATUS")
	private String status;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Basic(optional = false)
  @Column(name = "TOTAL_PRICE")
	private BigDecimal totalPrice;
	@Column(name = "DELIVERY_COST")
	private BigDecimal deliveryCost;
	@Column(name = "DISCOUNT")
	private BigDecimal discount;
	@Column(name = "ORDER_DATE")
  @Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	@JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
  @ManyToOne
	private Addresses addressId;
	@JoinColumn(name = "CARD_ID", referencedColumnName = "ID")
  @ManyToOne
	private Cardinfo cardId;
	@JoinColumn(name = "PROMO_ID", referencedColumnName = "ID")
  @ManyToOne
	private Promotions promoId;
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")
  @ManyToOne
	private Users userId;
	@OneToMany(mappedBy = "orderId")
	private List<Orderdetails> orderdetailsList;

	public Orders() {
	}

	public Orders(Integer id) {
		this.id = id;
	}

	public Orders(Integer id, String paymentMethod, String status, BigDecimal totalPrice) {
		this.id = id;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.totalPrice = totalPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(BigDecimal deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Addresses getAddressId() {
		return addressId;
	}

	public void setAddressId(Addresses addressId) {
		this.addressId = addressId;
	}

	public Cardinfo getCardId() {
		return cardId;
	}

	public void setCardId(Cardinfo cardId) {
		this.cardId = cardId;
	}

	public Promotions getPromoId() {
		return promoId;
	}

	public void setPromoId(Promotions promoId) {
		this.promoId = promoId;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	@XmlTransient
	public List<Orderdetails> getOrderdetailsList() {
		return orderdetailsList;
	}

	public void setOrderdetailsList(List<Orderdetails> orderdetailsList) {
		this.orderdetailsList = orderdetailsList;
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
		if (!(object instanceof Orders)) {
			return false;
		}
		Orders other = (Orders) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Model.Orders[ id=" + id + " ]";
	}

}
