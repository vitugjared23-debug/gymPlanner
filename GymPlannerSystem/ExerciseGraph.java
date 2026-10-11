package com.mycompany.gymorig;


import java.util.ArrayList;
import java.util.HashMap;

public class ExerciseGraph {

    private HashMap<String, ArrayList<String>> graph;

    public ExerciseGraph() {

        graph = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(String vertex) {

        if (!graph.containsKey(vertex)) {

            graph.put(vertex, new ArrayList<>());
        }
    }

    // Connect an exercise to a muscle group
    public void addEdge(String exercise, String muscle) {

        addVertex(exercise);
        addVertex(muscle);

        graph.get(exercise).add(muscle);
    }

    // Get the muscles connected to an exercise
    public ArrayList<String> getConnections(String exercise) {

        return graph.get(exercise);
    }

    // Check if an exercise is connected to a muscle
    public boolean hasConnection(
            String exercise,
            String muscle) {

        if (!graph.containsKey(exercise)) {
            return false;
        }

        return graph.get(exercise)
                .contains(muscle);
    }

    // Display the entire graph
    public void displayGraph() {

        System.out.println("\n===== EXERCISE GRAPH =====");

        for (String vertex : graph.keySet()) {

            System.out.println(
                vertex + " -> " + graph.get(vertex)
            );
        }
    }

    // Display connections of one exercise
    public void showConnections(String exercise) {

        if (!graph.containsKey(exercise)) {

            System.out.println(
                "Exercise not found in graph."
            );

            return;
        }

        System.out.println(
            exercise + " -> " + graph.get(exercise)
        );
    }
}