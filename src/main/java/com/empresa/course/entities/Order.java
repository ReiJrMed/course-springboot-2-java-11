package com.empresa.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.empresa.course.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_order") //seta o nome da tabela, tendo em vista a necessidade de mudar por order ser palavra reservada de sql
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Indica que o instant será salvo no padrão UTC longitude 0, no caso timezone GMT
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	private Integer orderStatus;
	
	//@JsonIgnore com o JsonIgnore aqui, o cliente é que vai instanciar todos os pedidos dele no spingboot
	@ManyToOne//indica a relação de 1 para muitos(1 cliente para muitos pedidos) no banco de dados
	@JoinColumn(name = "client_id")//indica o nome da coluna que vai receber a chave estrangeira
	private User client;
	
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL) //nome do atributo Order em payment no mappedBy
	private Payment payment;    //CascadeType.ALL indica que ambos serão indicicados com o mesmo Id (ou seja, id igual para ambos) 
	
	public Order() {
		
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);	
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null)
		  this.orderStatus = orderStatus.getCode();
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public Set<OrderItem> getItems(){
		return items;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Double getTotal() {
		//usa-se get antes do nome do método porque o SpringBoot reconhece o método quando tem get no nome
		if(items != null)
			return items.stream().map(x -> x.getSubTotal()).reduce(0.0, (x, y) -> x + y);
		else
			return null;		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
