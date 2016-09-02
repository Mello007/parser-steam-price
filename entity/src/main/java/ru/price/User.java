package ru.price;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity @Table(name = "UserData")
public class User extends BaseEntity{
    @Column private String login;
    @Column private String email;
}
