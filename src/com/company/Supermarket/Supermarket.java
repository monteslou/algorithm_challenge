package com.company.Supermarket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Supermarket {

    private List<List<Integer>> dataSet = new ArrayList<>();

    public Integer[] Supermarket(Integer[] items, int c) {
        sumUp(new ArrayList<>(Arrays.asList(items)), c);
        Integer [] selectedItems = getMaxLength(items.clone());
        print(items, selectedItems, c);
        return selectedItems;
    }

    private void sumUpRecursive(ArrayList<Integer> itemsInList, int c, ArrayList<Integer> partialSum) {
        int s = 0;
        for (int x : partialSum) s += x;
        if (s == c) {
            dataSet.add(partialSum);
        }
        if (s >= c)
            return;
        for (int i = 0; i < itemsInList.size(); i++) {
            ArrayList<Integer> remaining = new ArrayList<>();
            int n = itemsInList.get(i);
            for (int j = i + 1; j < itemsInList.size(); j++) remaining.add(itemsInList.get(j));
            ArrayList<Integer> partialRec = new ArrayList<>(partialSum);
            partialRec.add(n);
            sumUpRecursive(remaining, c, partialRec);
        }
    }

    private void sumUp(ArrayList<Integer> numbers, int target) {
        sumUpRecursive(numbers, target, new ArrayList<>());
    }


    private void print(Integer[] items, Integer[] selectedItems, int c) {
        System.out.println("-------------------------------------------");
        System.out.print("Shopping List: ");
        for (int i = 1; i <= items.length; i++)
            System.out.print(i + " ");
        System.out.println(" ");
        System.out.print("         Size: ");
        Arrays.stream(items).forEach(x->System.out.print(x + " "));
        System.out.println(" ");
        System.out.println("            C: " + c);
        System.out.print("       Result: ");
        Arrays.stream(selectedItems).forEach(x->System.out.print(x + " "));
        System.out.println(" ");
    }

    private Integer[] getMaxLength(Integer[] selectedItems) {
        int position = -1;
        int maxLength = -1;
        for (int i = 0; i < dataSet.size(); i++) {
            if (maxLength < dataSet.get(i).size()) {
                maxLength = dataSet.get(i).size();
                position = i;
            }
        }
        return getSelectedItems(position, selectedItems);
    }

    private Integer[] getSelectedItems(int position, Integer[] selectedItems) {
        for (int i = 0; i < dataSet.get(position).size(); i++) {
            for (int j = 0; j < selectedItems.length; j++) {
                if (dataSet.get(position).get(i).equals(selectedItems[j])) {
                    selectedItems[j] = 1;
                    break;
                }
            }
        }

        for (int i = 0; i < selectedItems.length; i++) {
            if (!selectedItems[i].equals(1)) {
                selectedItems[i] = 0;
            }
        }

        return selectedItems;
    }
}
