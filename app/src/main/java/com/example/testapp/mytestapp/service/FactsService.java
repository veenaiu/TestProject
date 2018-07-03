package com.example.testapp.mytestapp.service;

import com.example.testapp.mytestapp.domain.Facts;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FactsService {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<Facts> listFacts();
}

