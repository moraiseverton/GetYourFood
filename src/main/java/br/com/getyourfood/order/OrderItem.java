package br.com.getyourfood.order;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.getyourfood.product.Product;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "TB_ORDER_ITEM")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    @JsonIgnore
    private @NonNull Order theOrder;

    @Column(insertable = false, updatable = false)
    private @NonNull Long orderId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "productId", referencedColumnName = "ID")
    private @NonNull Product product;

    @Column(insertable = false, updatable = false)
    private Long productId;

    private @NonNull Double price;

    private @NonNull Long quantity;

    private Double total;
}
