package com.mama1emon.simpleinventorymanagmentapi.models;

import com.google.gson.annotations.Expose;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "product_sequence"),
                    @Parameter(name = "initial_value", value = "5"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    @Expose
    @Column(name = "name")
    private String name;
    @Expose
    @Column(name = "brand")
    private String brand;
    @Expose
    @Column(name = "price")
    @Min(1)
    private Long price;
    @Expose
    @Column(name = "quantity")
    @Min(1)
    private Long quantity;
}
