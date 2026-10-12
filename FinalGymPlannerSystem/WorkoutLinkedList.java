package com.mycompany.gymorig;


public class WorkoutLinkedList {

    private Node head;

    private class Node {

        WorkoutSet workout;
        String day;
        String status;
        Node next;

        Node(WorkoutSet workout, String day, String status) {
            this.workout = workout;
            this.day = day;
            this.status = status;
            this.next = null;
        }
    }

    public void addLast(WorkoutSet workout, String day, String status) {

        Node newNode = new Node(workout, day, status);

        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    public void addFirst(WorkoutSet workout, String day, String status) {

        Node newNode = new Node(workout, day, status);

        newNode.next = head;
        head = newNode;
    }

    public void updateStatus(String workoutType, String day, String status) {

        Node current = head;

        while (current != null) {

            if (current.workout.getWorkoutType()
                    .equalsIgnoreCase(workoutType)
                    && current.day.equalsIgnoreCase(day)) {

                current.status = status;
                return;
            }

            current = current.next;
        }
    }

    public void updateDay(String workoutType, String oldDay, String newDay) {

        Node current = head;

        while (current != null) {

            if (current.workout.getWorkoutType()
                    .equalsIgnoreCase(workoutType)
                    && current.day.equalsIgnoreCase(oldDay)) {

                current.day = newDay;
                current.status = "Rescheduled";
                return;
            }

            current = current.next;
        }
    }

    public void deleteFirst() {

        if (head == null) {
            return;
        }

        head = head.next;
    }

    public void traverse() {

        Node current = head;

        while (current != null) {

            System.out.println(
                current.day
                + " | "
                + current.workout.getWorkoutType()
                + " | "
                + current.status
            );

            current = current.next;
        }
    }

    public boolean isEmpty() {

        return head == null;
    }
}