public class Main {
    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(7);
        ds.unionBySize(1,2);
        ds.unionBySize(2,3);
        ds.unionBySize(4,5);
        ds.unionBySize(6,7);
        ds.unionBySize(5,6);
        System.out.println(ds.findUPar(3) == ds.findUPar(7));
        ds.unionBySize(3,7);
        System.out.println(ds.findUPar(7) == ds.findUPar(7));

    }
}