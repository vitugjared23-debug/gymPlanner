package com.mycompany.gymorig;


public class ExerciseBST {

    private Node root;

    private class Node {

        Exercise exercise;
        Node left;
        Node right;

        Node(Exercise exercise) {
            this.exercise = exercise;
            this.left = null;
            this.right = null;
        }
    }

    public void insert(Exercise exercise) {

        root = insertRecursive(root, exercise);
    }

    private Node insertRecursive(Node current, Exercise exercise) {

        if (current == null) {
            return new Node(exercise);
        }

        if (exercise.getExerciseId()
                < current.exercise.getExerciseId()) {

            current.left =
                    insertRecursive(current.left, exercise);

        } else if (exercise.getExerciseId()
                > current.exercise.getExerciseId()) {

            current.right =
                    insertRecursive(current.right, exercise);
        }

        return current;
    }

    public Exercise search(int exerciseId) {

        Node current = root;

        while (current != null) {

            if (exerciseId == current.exercise.getExerciseId()) {

                return current.exercise;
            }

            if (exerciseId < current.exercise.getExerciseId()) {

                current = current.left;

            } else {

                current = current.right;
            }
        }

        return null;
    }

    public void inorder() {

        inorderRecursive(root);
    }

    private void inorderRecursive(Node current) {

        if (current == null) {
            return;
        }

        inorderRecursive(current.left);

        System.out.println(current.exercise);

        inorderRecursive(current.right);
    }

    public boolean isEmpty() {

        return root == null;
    }
}