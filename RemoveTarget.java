/* Given a binary tree root and an integer target, delete all the leaf nodes with value target...
Note that once you delete a leaf node with value target, if its parent node becomes a leaf node and has the value target, it should also be deleted (you need to continue doing that until you cannot)...
 * //! NOTE: The Target value of the Nodes are given in parenthesis...
 * Eg 1: Tree = 20(1), 10(2), 5(2), 25(3), 22(2), 30(4)             target = 2            Output = 20(1), 25(3), 30(4)
 * Eg 2: Tree = 20(1), 10(2), 15(2), 5(2), 30(3), 25(2), 35(4)      target = 2            Output = 20(1), 30(3), 35(4) */
import java.util.*;
public class RemoveTarget
{
    public class Node
    {
        public Node left;
        public Node right;
        public int data;
        public int target;
        public Node(int value, int tar)   // Parametrized Constructor...
        {
            this.left = null;
            this.right = null;
            this.data = value;
            this.target = tar;
        }
        public Node(int value, int tar, Node leftChild, Node rightChild)   // Parametrized Constructor...
        {
            this.data = value;
            this.left = leftChild;
            this.right = rightChild;
            this.target = tar;
        }
    }
    Node root;   // Root Node pointer...
    public Node InsertNode(Node root, int value, int target)
    {
        if(root == null)
            return new Node(value, target);    // New Node created...
        else if(root.data >= value)
            root.left = InsertNode(root.left, value, target);
        else if(root.data < value)
            root.right = InsertNode(root.right, value, target);
        return root;
    }
    public Node RemoveTargetLeaves(Node root, int target)
    {
        if(root == null)
            return null;
        root.left = RemoveTargetLeaves(root.left, target);     // Traverse Left...
        root.right = RemoveTargetLeaves(root.right, target);   // Traverse Right...
        if(root.left == null && root.right == null && root.target == target)
            return null;    // That node is removed via recursive call, the call returns empty node to the parent call...
        return root;
    }
    public void ShowTree(Node root)
    {
        if(root == null)   // If Tree is empty nothing to show
            return;            // Control moves out of the function
        Node temp = root;
        ShowTree(root.left);     // Recursive Call
        System.out.println("Node: ");
        if(root.left != null)    // If left subtree is not empty
            System.out.println("\t"+temp.data+" ("+temp.target+") "+" ---> "+temp.left.data+" ("+temp.left.target+") "+"\t Left ( Occupied )");
        else      // If left subtree is empty
            System.out.println("\t"+temp.data+"\t\t\t Left ( Empty )");
        if(root.right != null)   // If right subtree is not empty
            System.out.println("\t"+temp.data+" ("+temp.target+") "+" ---> "+temp.right.data+" ("+temp.right.target+") "+"\t Right ( Occupied ) ");
        else     // If right subtree is empty
            System.out.println("\t"+temp.data+"\t\t\t Right ( Empty )");
        ShowTree(root.right);     // Recursive Call
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int x, node_val, target_val;
        System.out.print("Enter the number of nodes in the Binary Tree : ");
        x = sc.nextInt();
        Node root = null;
        RemoveTarget removetarget = new RemoveTarget();     // Object creation...
        for(int i = 0; i < x; i++)
        {
            System.out.print("Enter the "+(i+1)+" th Node data value : ");
            node_val = sc.nextInt();
            System.out.print("Enter the "+(i+1)+" th Node target value : ");
            target_val = sc.nextInt();
            root = removetarget.InsertNode(root, node_val, target_val);
        }
        System.out.print("Enter Target Value : ");
        x = sc.nextInt();
        removetarget.ShowTree(root);
        root = removetarget.RemoveTargetLeaves(root, x);
        System.out.println("The Target Nodes are Removed !!");
        removetarget.ShowTree(root);
        sc.close();
    }
}
