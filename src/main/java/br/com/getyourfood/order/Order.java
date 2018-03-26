package br.com.getyourfood.order;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.getyourfood.customer.Customer;
import br.com.getyourfood.store.Store;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "TB_ORDER")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    private @NonNull Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    @JsonIgnore
    private Customer customer;

    @Column(insertable = false, updatable = false)
    private @NonNull Long customerId;

    private @NonNull String deliveryAddress;

    private @NonNull String contact;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "storeId")
    @JsonIgnore
    private @NonNull Store store;

    @Column(insertable = false, updatable = false)
    private @NonNull Long storeId;

    @OneToMany(mappedBy = "theOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private @NonNull List<OrderItem> orderItems;

    private Double total;

    private @NonNull String status;

    private LocalDateTime lastUpdate;
}
