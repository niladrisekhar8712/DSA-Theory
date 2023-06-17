import java.util.Scanner;

public class Main {
    public static void options() {
        System.out.println("1. Insert a Vertex");
        System.out.println("2. Insert an Edge");
        System.out.println("3. Delete a Vertex");
        System.out.println("4. Delete an Edge");
        System.out.println("5. Display");
        System.out.println("6. Show Options");
        System.out.println("7. Exit");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph<Integer> graph = new Graph<>();
        int choice, u, origin, destin;
        options();
        while (true) {
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter a vertex to be inserted: ");
                    u = scanner.nextInt();
                    graph.insertVertex(u);
                }
                case 2 -> {
                    System.out.println("Enter an edge to be inserted: ");
                    origin = scanner.nextInt();
                    destin = scanner.nextInt();
                    graph.insertEdge(origin, destin);
                }
                case 3 -> {
                    System.out.println("Enter a vertex to be deleted: ");
                    u = scanner.nextInt();
                    graph.deleteVertex(u);
                }
                case 4 -> {
                    System.out.println("Enter an edge to be deleted: ");
                    origin = scanner.nextInt();
                    destin = scanner.nextInt();
                    graph.deleteEdge(origin, destin);
                }
                case 5 -> graph.display();
                case 6 -> options();
                case 7 -> System.exit(1);
                default -> System.out.println("Invalid Input");
            }
        }
    }
}