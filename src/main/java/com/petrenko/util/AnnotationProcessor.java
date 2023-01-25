package com.petrenko.util;

import com.petrenko.annotations.Autowired;
import com.petrenko.annotations.Singleton;
import com.petrenko.repository.CarListRepository;
import com.petrenko.repository.CarMapRepository;
import com.petrenko.repository.CarRepository;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class AnnotationProcessor {
    public static final Map<Class<?>, Object> CHASE_SINGLETON = new HashMap<>();
    private final Reflections reflections = new Reflections("com.petrenko");
    private final Set<Class<?>> classSetSingleton = reflections.getTypesAnnotatedWith(Singleton.class);

    public AnnotationProcessor() {
        classSetSingleton.stream()
                .filter(c -> CHASE_SINGLETON.get(c) == null)
                .forEach(c -> {
                    Optional<Object> objectOfClass = createObjectOfClass(c);
                    objectOfClass.ifPresent(o -> {
                        CHASE_SINGLETON.put(c, o);
                        setObjectToInstanceField(c, o);
                    });
                });
    }

    private Optional<Object> createObjectOfClass(Class<?> aclass) {
        Constructor[] constructors = aclass.getDeclaredConstructors();
        return Arrays.stream(constructors)
                .peek(constructor -> constructor.setAccessible(true))
                .map(constructor -> constructor.getParameterCount() == 0 ?
                        getRepositoryObject(constructor) : getServiceObject(constructor))
                .findAny();
    }

    private Object getRepositoryObject(Constructor constructor) {
        Object object;
        try {
            object = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    private Object getServiceObject(Constructor serviceConstructor) {
        Class<?> repositoryClass = getRepositoryClassFromAutowired(serviceConstructor);
        Object object;
        try {
            Constructor repositoryConstructor = repositoryClass.getDeclaredConstructor();
            repositoryConstructor.setAccessible(true);
            object = serviceConstructor.newInstance(getRepositoryObject(repositoryConstructor));
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    private Class<?> getRepositoryClassFromAutowired(Constructor serviceConstructor) {
        Autowired annotation = serviceConstructor.getDeclaredAnnotation(Autowired.class);
        Class<?> repositoryClass = null;
        switch (annotation.typeOfRepository()) {
            case LIST -> repositoryClass = CarListRepository.class;
            case ARRAY -> repositoryClass = CarRepository.class;
            case MAP -> repositoryClass = CarMapRepository.class;
        }
        return repositoryClass;
    }

    private void setObjectToInstanceField(Class<?> aclass, Object object) {
        try {
            Field instance = aclass.getDeclaredField("instance");
            instance.setAccessible(true);
            instance.set(object, object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}