package com.scc.model;

public record User(int id, String full_name,String username, String password, String role, String active, String created_at) {
    @Override
    public String toString(){
        return  String.format("[%d] %s (%s) - Status: %s", id, username ,password , role, active, created_at);
    }
}
