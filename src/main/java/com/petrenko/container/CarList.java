package com.petrenko.container;

import com.petrenko.model.Car;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class CarList {
    private ElementCarList firstElementCarList;
    private ElementCarList lastElementCarList;

    public void addFirst(Car element) {
        Optional.ofNullable(element).ifPresent((e) ->
                Optional.ofNullable(firstElementCarList).ifPresentOrElse(f ->
                                uplinkFirstElement(null, e, f),
                        () -> firstAndLastElementEqualsNewElement(e))
        );
    }

    public void addLast(Car element) {
        Optional.ofNullable(element).ifPresent((e) ->
                Optional.ofNullable(lastElementCarList).ifPresentOrElse(l ->
                                uplinkLastElement(l, e, null),
                        () -> firstAndLastElementEqualsNewElement(e))
        );
    }

    private void uplinkFirstElement(ElementCarList elementBeforeNew, Car element, ElementCarList elementAfterNew) {
        ElementCarList newElementCarList = addNewElement(elementBeforeNew, element, elementAfterNew);
        firstElementCarList.setPreviousElement(newElementCarList);
        firstElementCarList = newElementCarList;
    }

    private void uplinkLastElement(ElementCarList elementBeforeNew, Car element, ElementCarList elementAfterNew) {
        ElementCarList newElementCarList = addNewElement(elementBeforeNew, element, elementAfterNew);
        lastElementCarList.setNextElement(newElementCarList);
        lastElementCarList = newElementCarList;
    }

    private void firstAndLastElementEqualsNewElement(Car element) {
        firstElementCarList = new ElementCarList(element);
        lastElementCarList = firstElementCarList;
    }

    public void addByPosition(Car element, int position) {
        if (position < 0 || position >= size()) {
            return;
        }
        if (position == 0) {
            addFirst(element);
        }
        if (position == size() - 1) {
            addLast(element);
        }
        Optional.ofNullable(element)
                        .ifPresent(e -> addNewElement(getElementCarListByPosition(position - 1),
                                e,
                                getElementCarListByPosition(position)));
    }

    private ElementCarList addNewElement(ElementCarList elementBeforeNew,
                                         Car element,
                                         ElementCarList elementAfterNew) {
        ElementCarList newElementCarList = new ElementCarList(element);

        if (elementBeforeNew != null) {
            elementBeforeNew.setNextElement(newElementCarList);
        }
        if (elementAfterNew != null) {
            elementAfterNew.setPreviousElement(newElementCarList);
        }

        newElementCarList.setPreviousElement(elementBeforeNew);
        newElementCarList.setNextElement(elementAfterNew);

        return newElementCarList;
    }

    public void deleteByPosition(int position) {
        if (position < 0 || position >= size()) {
            System.out.println("Position not exist");
            return;
        }

        ElementCarList elementBeforeDeleted = (position == 0) ?
                null : getElementCarListByPosition(position - 1);
        ElementCarList elementForDeleted = getElementCarListByPosition(position);
        ElementCarList elementAfterDeleted = (position == size() - 1) ?
                null : getElementCarListByPosition(position + 1);

        deleteElement(elementBeforeDeleted,
                elementForDeleted,
                elementAfterDeleted);
    }

    private void deleteElement(ElementCarList elementBeforeDeleted,
                               ElementCarList elementForDeleted,
                               ElementCarList elementAfterDeleted) {

        if (elementBeforeDeleted == null) {
            firstElementCarList = elementAfterDeleted;
        }
        else {
            elementBeforeDeleted.setNextElement(elementAfterDeleted);
        }

        elementForDeleted.setPreviousElement(null);
        elementForDeleted.setElement(null);
        elementForDeleted.setNextElement(null);

        if (elementAfterDeleted == null) {
            lastElementCarList = elementBeforeDeleted;
        } else {
            elementAfterDeleted.setPreviousElement(elementBeforeDeleted);
        }

        ElementCarList.count--;
    }

    public Car getCarByPosition(int position) {
        if (position < 0 || position >= size()) {
            return null;
        }
        if (position == 0) {
            return firstElementCarList.getElement();
        }
        if (position == size() - 1) {
            return lastElementCarList.getElement();
        }
        return getElementCarListByPosition(position).getElement();
    }

    private ElementCarList getElementCarListByPosition(int position) {
        ElementCarList elementCarList = firstElementCarList;
        for (int i = 0; i < position; i++) {
            elementCarList = elementCarList.getNextElement();
        }
        return elementCarList;
    }

    public int positionOf(String carId) {
        if (carId == null || firstElementCarList == null) {
            System.out.println("CarID not exist or CarList is empty");
            return -1;
        }
        ElementCarList elementCarList = firstElementCarList;
        int position = 0;
        while (carId != elementCarList.getElement().getUuidOfCar()) {
            elementCarList = elementCarList.getNextElement();
            position++;
            if (elementCarList == null) {
                System.out.println("CarID not exist or CarList is empty");
                return -1;
            }
        }
        return position;
    }

    public void printList() {
        ElementCarList elementCarList = firstElementCarList;
        while (elementCarList != null) {
            printElementCarList(elementCarList);
            elementCarList = elementCarList.getNextElement();
        }
    }

    public void printElementCarList(ElementCarList elementCarList) {
        System.out.println(elementCarList.getElement().toString());
    }

    public int size() {
        return ElementCarList.count;
    }

    public Car[] carlistToArray() {
        Car[] cars = new Car[size()];
        ElementCarList elementCarList = firstElementCarList;
        for (int i = 0; i < size(); i++) {
            cars[i] = elementCarList.getElement();
            elementCarList = elementCarList.getNextElement();
        }
        return cars;
    }

    @Getter
    @Setter
    public class ElementCarList {
        private Car element;
        private ElementCarList previousElement;
        private ElementCarList nextElement;
        private static int count;

        public ElementCarList(Car element) {
            this.element = element;
            count++;
        }

    }
}

