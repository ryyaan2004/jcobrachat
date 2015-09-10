package org.ryyaan2004.chat.model;

public class User {

  public User() {};

  public User( String email, String alias ) {
    setEmail( email );
    setAlias( alias );
    setFirstname( "" );
    setLastname( "" );
  }

  public User( String email, String alias, String firstname, String lastname ) {
    setEmail( email );
    setAlias( alias );
    setFirstname( ( firstname == null ) ? "" : firstname );
    setLastname( ( lastname == null ) ? "" : lastname );
  }

  private String email;
  private String alias;
  private String firstname;
  private String lastname;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

}
