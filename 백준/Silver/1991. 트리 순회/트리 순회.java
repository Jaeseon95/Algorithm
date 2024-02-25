import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 트리 순회
 */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static final char START = 'A';
    static final char BLANK = '.';


    static Node[] nodeList;
    static class Node {

        char vertex;
        int left;
        int right;

        public Node(char vertex, int left, int right) {
            this.vertex = vertex;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int numNode = Integer.parseInt(br.readLine().trim());
        nodeList = new Node[numNode];
        for (int idx = 0; idx < numNode; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            char vertex = st.nextToken().toCharArray()[0];
            char leftChar = st.nextToken().toCharArray()[0];
            char rightChar = st.nextToken().toCharArray()[0];
            int left = 0;
            int right = 0;
            if (leftChar != BLANK){
                left = leftChar - START;
            }
            if (rightChar != BLANK){
                right = rightChar - START;
            }
            nodeList[vertex - START] = new Node(vertex, left, right);
        }
        //전위순회
        preorder(nodeList[0]);
        sb.append("\n");
        inorder(nodeList[0]);
        sb.append("\n");
        postorder(nodeList[0]);
        System.out.println(sb);
    }

    private static void postorder(Node node) {
        if (node.left != 0){
            postorder(nodeList[node.left]);
        }
        if (node.right!= 0){
            postorder(nodeList[node.right]);
        }
        sb.append(node.vertex);
    }

    private static void inorder(Node node) {
        if (node.left != 0){
            inorder(nodeList[node.left]);
        }
        sb.append(node.vertex);
        if (node.right!= 0){
            inorder(nodeList[node.right]);
        }
    }

    private static void preorder(Node node) {
        sb.append(node.vertex);
        if (node.left != 0){
            preorder(nodeList[node.left]);
        }
        if (node.right!= 0){
            preorder(nodeList[node.right]);
        }
    }
}
