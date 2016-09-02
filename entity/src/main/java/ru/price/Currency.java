package ru.price;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter
@Entity @Table(name = "currency")
public class Currency extends BaseEntity {
    @Column private String itemCurr = "5";
}