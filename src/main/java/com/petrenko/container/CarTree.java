package com.petrenko.container;

import com.petrenko.model.Car;
import lombok.Getter;
import lombok.Setter;

public class CarTree {
    private ElementCarTree firstElementCarTree;
    private static int countLeftBrunch;
    private static int countRightBrunch;
    private CarComparator carComparator = new CarComparator();

    public void add(Car element) {
        if (element == null) {
            return;
        }
        if (firstElementCarTree == null) {
            firstElementCarTree = new ElementCarTree(element);
            return;
        }
        countCars(element);

        ElementCarTree elementBeforeNew = findElementBeforeNew(element, firstElementCarTree);

        if (elementBeforeNew == null) {
            return;
        }

        int compare = carComparator.compare(element, elementBeforeNew.getElement());
        boolean moreThenPrevious = compare > 0;

        addNewElement(elementBeforeNew, element, moreThenPrevious);
    }

    private ElementCarTree findElementBeforeNew(Car element, ElementCarTree elementBeforeNew) {
        int compare = carComparator.compare(element, elementBeforeNew.getElement());
        if (compare == 0) {
            return null;
        }

        if (compare > 0) {
            if (elementBeforeNew.getNextRightElement() == null) {
                return elementBeforeNew;
            }
            elementBeforeNew = elementBeforeNew.getNextRightElement();
        } else {
            if (elementBeforeNew.getNextLeftElement() == null) {
                return elementBeforeNew;
            }
            elementBeforeNew = elementBeforeNew.getNextLeftElement();
        }
        return findElementBeforeNew(element, elementBeforeNew);
    }

    private ElementCarTree addNewElement(ElementCarTree elementBeforeNew,
                                         Car element, boolean moreThenPrevious) {
        ElementCarTree newElement = new ElementCarTree(element);

        if (moreThenPrevious) {
            elementBeforeNew.setNextRightElement(newElement);
        } else {
            elementBeforeNew.setNextLeftElement(newElement);
        }

        newElement.setPreviousElement(elementBeforeNew);

        return newElement;
    }
    private void countCars(Car element) {
        int compare = carComparator.compare(element, firstElementCarTree.getElement());
        if (compare > 0) {
            countRightBrunch += element.getCount();
        } else if (compare < 0) {
            countLeftBrunch += element.getCount();
        } else {
            countRightBrunch += element.getCount();
            countLeftBrunch += element.getCount();
        }
        System.out.printf("Added car: %s; count: %d%n", element.getUuidOfCar(), element.getCount());
        System.out.println("Count of cars from left brunch: " + countLeftBrunch);
        System.out.println("Count of cars from right brunch: " + countRightBrunch);

    }


    public void printElementCarTree(Car element) {
        ElementCarTree elementCarTree = findElementByCar(element, firstElementCarTree);

        if (elementCarTree == null) {
            System.out.println("Car not exist: " + element);
            return;
        }

        printElementCarTree(elementCarTree.getPreviousElement(),
                elementCarTree,
                elementCarTree.getNextLeftElement(),
                elementCarTree.getNextRightElement());
    }

    private ElementCarTree findElementByCar(Car element, ElementCarTree elementCarTree) {
        int compare = carComparator.compare(element, elementCarTree.getElement());
        if (compare == 0) {
            return elementCarTree;
        }

        if (compare > 0) {
            if (elementCarTree.getNextRightElement() == null) {
                return null;
            }
            elementCarTree = elementCarTree.getNextRightElement();
        } else {
            if (elementCarTree.getNextLeftElement() == null) {
                return null;
            }
            elementCarTree = elementCarTree.getNextLeftElement();
        }
        return findElementByCar(element, elementCarTree);
    }

    private void printElementCarTree(ElementCarTree elementBefore,
                                     ElementCarTree elementCarTree,
                                     ElementCarTree elementNextLeft,
                                     ElementCarTree elementNextRight) {
        System.out.println("Previous car: ");
        if (elementBefore != null) {
            System.out.println(elementBefore.getElement().toString());
        } else {
            System.out.println("not exist");
        }
        System.out.println("Car: ");
        System.out.println(elementCarTree.getElement().toString());
        System.out.println("Next less (left) car: ");
        if (elementNextLeft != null) {
            System.out.println(elementNextLeft.getElement().toString());
        } else {
            System.out.println("not exist");
        }
        System.out.println("Next more (right) car: ");
        if (elementNextRight != null) {
            System.out.println(elementNextRight.getElement().toString());
        } else {
            System.out.println("not exist");
        }
    }

    @Getter
    @Setter
    private class ElementCarTree {
        private Car element;
        private ElementCarTree previousElement;
        private ElementCarTree nextRightElement;
        private ElementCarTree nextLeftElement;

        private ElementCarTree(Car element) {
            this.element = element;
        }

    }
}
