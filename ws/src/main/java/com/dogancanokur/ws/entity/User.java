package com.dogancanokur.ws.entity;

import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.Hibernate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank
    private String username;
    @NotBlank
    private String displayName;
    @NotBlank
    private String password;

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
	    return false;
	User user = (User) o;
	return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
	return getClass().hashCode();
    }
}
