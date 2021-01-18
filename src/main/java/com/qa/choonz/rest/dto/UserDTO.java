package com.qa.choonz.rest.dto;

public class UserDTO {

    private long id;
    private String username;
    private String password;

    public UserDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDTO(long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

}
