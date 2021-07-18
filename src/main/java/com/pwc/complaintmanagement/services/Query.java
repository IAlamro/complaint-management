package com.pwc.complaintmanagement.services;

public interface Query <T, R>{

    R execute(T t);
}
