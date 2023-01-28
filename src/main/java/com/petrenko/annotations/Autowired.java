package com.petrenko.annotations;

import com.petrenko.repository.CarMapRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
public @interface Autowired {
    Class<?> classOfRepository() default CarMapRepository.class;
}
