package com.pwc.complaintmanagement.services;

public interface Command <T>{

    void execute(T t);
}
