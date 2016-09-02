package ru.price;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "item")
public class Item extends BaseEntity{
    @Column private String itemName;
    @Column private String itemPrice;
    @Column private String itemKind;
    @Column private String itemCurr = "5";
}
