package com.risosuit.DGomezTagle.TicketsService.DTO;

public class LoginResponse {

    private String token;
    private String username;
    private String rol;
    private Long idUsuario;

    public LoginResponse() {
    }

    public LoginResponse(String token) {
        this.token = token;
    }

    public LoginResponse(String token, String username, String rol) {
        this.token = token;
        this.username = username;
        this.rol = rol;
    }

    public LoginResponse(String token, String username, String rol, Long idUsuario) {
        this.token = token;
        this.username = username;
        this.rol = rol;
        this.idUsuario = idUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Long getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

}