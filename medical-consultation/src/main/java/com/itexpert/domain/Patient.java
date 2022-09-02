package com.itexpert.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@PrimaryKeyJoinColumn(name = "user_account_id")
@Entity
public class Patient extends UserAccount{

}
