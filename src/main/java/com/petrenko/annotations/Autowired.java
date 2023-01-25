package com.petrenko.annotations;

import com.petrenko.repository.TypeRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
public @interface Autowired {
    TypeRepository typeOfRepository();         //TODO enum for all rep. and services (looking for by  new Reflections("com....")?)
}
