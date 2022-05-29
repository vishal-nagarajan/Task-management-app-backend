package com.example.TaskApp;

import java.util.Scanner;

public class test {

//  out
//  id,name,fees,add1,add2,city,pin
public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  String input[]=new String[7];
  String adr1= sc.nextLine().split("-")[0];
  String adr2= sc.nextLine().split("-")[0];
  String city= sc.nextLine().split("-")[0];
  int pin= Integer.parseInt(sc.nextLine().split("-")[0]);
  String custId= sc.nextLine().split("-")[0];
  String custName= sc.nextLine().split("-")[0];
  String fees= sc.nextLine().split("-")[0];

  Address address = new Address();
  address.setAddr1(adr1);
  address.setAddr2(adr2);
  address.setCity(city);
  RegisteredCustomer registeredCustomer = new RegisteredCustomer(custId,custName,address,Double.parseDouble(fees));

  System.out.println(registeredCustomer.getCustId());
  System.out.println(registeredCustomer.getCustName());
  System.out.println(registeredCustomer.getFees());
  System.out.println(registeredCustomer.getAddress().getAddr1());
  System.out.println(registeredCustomer.getAddress().getAddr2());
  System.out.println(registeredCustomer.getAddress().getCity());
  System.out.println(pin);
}


}


class RegisteredCustomer extends Customer{
  private double fees;

  public double getFees() {
    return fees;
  }

  public void setFees(double fees) {
    this.fees = fees;
  }

  RegisteredCustomer(String custId, String custName, Address address, double fees) {
    this.setAddr1(address.getAddr1());
    this.setAddr2(address.getAddr2());
    this.setCity(address.getCity());
    this.fees = fees;
  }
}


class Customer extends Address{
  private String custId;
  private String custName;
  private Address address;

  public String getCustId() {
    return custId;
  }

  public String getCustName() {
    return custName;
  }

  public Address getAddress() {
    return address;
  }
  public Customer() {
  }


  public Customer(String custId, String custName, Address address) {
    this.custId = custId;
    this.custName = custName;
    this.address = address;
  }
}


class Address {
  String addr1;
  String addr2;
  String city;
  int pin;

  public String getAddr1() {
    return addr1;
  }

  public void setAddr1(String addr1) {
    this.addr1 = addr1;
  }

  public String getAddr2() {
    return addr2;
  }

  public void setAddr2(String addr2) {
    this.addr2 = addr2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public int getPin() {
    return pin;
  }

  public void setPin(int pin) {
    this.pin = pin;
  }
}






